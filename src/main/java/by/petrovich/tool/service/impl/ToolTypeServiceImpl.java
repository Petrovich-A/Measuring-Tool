package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolTypeMapper;
import by.petrovich.tool.model.ToolType;
import by.petrovich.tool.repository.ToolTypeRepository;
import by.petrovich.tool.service.ToolTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolTypeServiceImpl implements ToolTypeService {
    public static final String TOOL_TYPE_NOT_FOUND = "Tool type not found with id: ";
    private final ToolTypeRepository toolTypeRepository;
    private final ToolTypeMapper toolTypeMapper;

    @Override
    public ToolTypeResponseDto find(Long id) {
        return toolTypeMapper.toResponseDto(toolTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TOOL_TYPE_NOT_FOUND + id)));
    }

    @Override
    public List<ToolTypeResponseDto> findAll() {
        List<ToolType> toolTypes = toolTypeRepository.findAll();
        return toolTypes.stream()
                .map(toolTypeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ToolTypeResponseDto create(ToolTypeRequestDto toolTypeRequestDto) {
        ToolType toolType = toolTypeRepository.save(toolTypeMapper.toEntity(toolTypeRequestDto));
        return toolTypeMapper.toResponseDto(toolType);
    }

    @Override
    public ToolTypeResponseDto update(Long id, ToolTypeRequestDto toolTypeRequestDto) {
        Optional<ToolType> optionalToolType = toolTypeRepository.findById(id);
        ToolType toolTypeUpdate = toolTypeMapper.toEntityUpdate(toolTypeRequestDto,
                optionalToolType.orElseThrow(() -> new ResourceNotFoundException(TOOL_TYPE_NOT_FOUND + id)));
        ToolType toolTypeSaved = toolTypeRepository.save(toolTypeUpdate);
        return toolTypeMapper.toResponseDto(toolTypeSaved);
    }

    @Override
    public void delete(Long id) {
        if (toolTypeRepository.existsById(id)) {
            toolTypeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(TOOL_TYPE_NOT_FOUND + id);
        }
    }

}
