package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolMapper;
import by.petrovich.tool.model.Tool;
import by.petrovich.tool.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolServiceImpl implements by.petrovich.tool.service.ToolService {
    public static final String TOOL_NOT_FOUND = "Tool not found with id: ";
    private final ToolRepository toolRepository;
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

}
