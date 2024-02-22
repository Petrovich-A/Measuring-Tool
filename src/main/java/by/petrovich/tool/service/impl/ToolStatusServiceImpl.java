package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolStatusMapper;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.repository.ToolStatusRepository;
import by.petrovich.tool.service.ToolStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolStatusServiceImpl implements ToolStatusService {
    public static final String TOOL_STATUS_NOT_FOUND = "Tool status not found with id: ";
    private final ToolStatusRepository toolStatusRepository;
    private final ToolStatusMapper toolStatusMapper;

    @Override
    public ToolStatusResponseDto find(Long id) {
        return toolStatusMapper.toResponseDto(toolStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TOOL_STATUS_NOT_FOUND + id)));
    }

    @Override
    public List<ToolStatusResponseDto> findAll() {
        List<ToolStatus> toolStatuses = toolStatusRepository.findAll();
        return toolStatuses.stream()
                .map(toolStatusMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ToolStatusResponseDto create(ToolStatusRequestDto toolStatusRequestDto) {
        ToolStatus toolStatus = toolStatusRepository.save(toolStatusMapper.toEntity(toolStatusRequestDto));
        return toolStatusMapper.toResponseDto(toolStatus);
    }

    @Override
    @Transactional
    public ToolStatusResponseDto update(Long id, ToolStatusRequestDto toolStatusRequestDto) {
        Optional<ToolStatus> optionalToolStatus = toolStatusRepository.findById(id);
        ToolStatus toolStatusUpdate = toolStatusMapper.toEntityUpdate(toolStatusRequestDto,
                optionalToolStatus.orElseThrow(() -> new ResourceNotFoundException(TOOL_STATUS_NOT_FOUND + id)));
        ToolStatus toolStatusSaved = toolStatusRepository.save(toolStatusUpdate);
        return toolStatusMapper.toResponseDto(toolStatusSaved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (toolStatusRepository.existsById(id)) {
            toolStatusRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(TOOL_STATUS_NOT_FOUND + id);
        }
    }

    @Override
    public ToolStatus findByName(String name) {
        return toolStatusRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ResourceNotFoundException(TOOL_STATUS_NOT_FOUND + name));
    }

    @Override
    public List<ToolStatus> findAllEntities() {
        return toolStatusRepository.findAll();
    }

}
