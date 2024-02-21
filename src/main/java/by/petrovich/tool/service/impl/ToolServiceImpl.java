package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolMapper;
import by.petrovich.tool.model.Tool;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.model.ToolStatusDateModification;
import by.petrovich.tool.repository.ToolRepository;
import by.petrovich.tool.repository.ToolStatusDateModificationRepository;
import by.petrovich.tool.service.ToolService;
import by.petrovich.tool.service.ToolStatusService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
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
    private final ToolRepository toolRepository;
    private final ToolStatusService toolStatusService;
    private final ToolStatusDateModificationRepository toolStatusDateModificationRepository;
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
        Tool tool = toolRepository.save(toolMapper.toEntity(toolRequestDto));
        ToolStatusDateModification toolStatusDateModification = buildToolStatusDateModification(tool);
        toolStatusDateModificationRepository.save(toolStatusDateModification);
        return toolMapper.toResponseDto(tool);
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
    public ToolResponseDto changeToolStatusToUnderPrecisionCheck(Long id) {
        Tool tool = toolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TOOL_NOT_FOUND + id));
        ToolStatus underPrecisionCheckToolStatus = toolStatusService.findByName(UNDER_PRECISION_CHECK);
        tool.setToolStatus(underPrecisionCheckToolStatus);
        Tool toolUpdated = toolRepository.save(tool);
        return toolMapper.toResponseDto(toolUpdated);
    }


    private ToolStatusDateModification buildToolStatusDateModification(Tool tool) {
        return ToolStatusDateModification.builder()
                .start(LocalDateTime.now())
                .toolStatus(tool.getToolStatus())
                .tool(tool)
                .build();
    }
}
