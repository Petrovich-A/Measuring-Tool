package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolMapper;
import by.petrovich.tool.model.Tool;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.repository.ToolRepository;
import by.petrovich.tool.service.ToolService;
import by.petrovich.tool.service.ToolStatusDateModificationService;
import by.petrovich.tool.service.ToolStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolServiceImpl implements ToolService {
    public static final String TOOL_NOT_FOUND = "Tool not found with id: ";
    public static final String UNDER_PRECISION_CHECK = "under precision check";
    public static final String IN_THE_STORAGE_ROOM = "in the storage room";
    private final ToolRepository toolRepository;
    private final ToolStatusService toolStatusService;
    private final ToolStatusDateModificationService toolStatusDateModificationService;
    private final ToolMapper toolMapper;

    @Override
    public ToolResponseDto find(Long id) {
        return toolMapper.toResponseDto(toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TOOL_NOT_FOUND + id)));
    }

    @Override
    public List<ToolResponseDto> findAll() {
        List<Tool> tools = toolRepository.findAll();
        return tools.stream()
                .map(toolMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ToolResponseDto create(ToolRequestDto toolRequestDto) {
        Tool tool = toolMapper.toEntity(toolRequestDto);
        Tool toolWithStatus = changeToolStatus(tool, IN_THE_STORAGE_ROOM, null);
        Tool toolSaved = toolRepository.save(toolWithStatus);
        toolStatusDateModificationService.create(tool, null);
        return toolMapper.toResponseDto(toolSaved);
    }

    @Override
    @Transactional
    public ToolResponseDto update(Long id, ToolRequestDto toolRequestDto) {
        Optional<Tool> optionalTool = toolRepository.findById(id);
        Tool toolUpdate = toolMapper.toEntityUpdate(toolRequestDto,
                optionalTool.orElseThrow(() -> new ResourceNotFoundException(TOOL_NOT_FOUND + id)));
        Tool toolSaved = toolRepository.save(toolUpdate);
        return toolMapper.toResponseDto(toolSaved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (toolRepository.existsById(id)) {
            toolRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(TOOL_NOT_FOUND + id);
        }
    }

    @Transactional
    @Override
    public ToolResponseDto submitForPrecisionCheck(Long id, LocalDateTime finishDatetime) {
        Tool tool = toolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TOOL_NOT_FOUND + id));
        Tool toolWithToolStatus = changeToolStatus(tool, UNDER_PRECISION_CHECK, finishDatetime);
        Tool toolSaved = toolRepository.save(toolWithToolStatus);
        toolStatusDateModificationService.create(tool, finishDatetime);
        return toolMapper.toResponseDto(toolSaved);
    }

    private Tool changeToolStatus(Tool tool, String statusName, LocalDateTime statusFinish) {
        ToolStatus toolStatus = toolStatusService.findByName(statusName);
        tool.setToolStatus(toolStatus);
        return tool;
    }

    @Override
    public List<ToolResponseDto> findBy(Long statusId) {
        List<Tool> tools = toolRepository.findByToolStatusId(statusId);
        return tools.stream()
                .map(toolMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ToolResponseDto> findBy(Long toolTypeId, Long storageRoomId) {
        List<Tool> tools = toolRepository.findByToolTypeIdAndStorageRoomId(toolTypeId, storageRoomId);
        return tools.stream()
                .map(toolMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
