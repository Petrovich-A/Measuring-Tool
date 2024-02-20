package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.response.ToolIssuanceResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.ToolIssuanceMapper;
import by.petrovich.tool.model.ToolIssuance;
import by.petrovich.tool.repository.ToolIssuanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolIssuanceServiceImpl implements by.petrovich.tool.service.ToolIssuanceService {
    public static final String TOOL_ISSUANCE_NOT_FOUND = "Tool issuance not found with id: ";
    private final ToolIssuanceRepository toolIssuanceRepository;
    private final ToolIssuanceMapper toolIssuanceMapper;

    @Override
    public ToolIssuanceResponseDto find(Long id) {
        return toolIssuanceMapper.toResponseDto(toolIssuanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TOOL_ISSUANCE_NOT_FOUND + id)));
    }

    @Override
    public List<ToolIssuanceResponseDto> findAll() {
        List<ToolIssuance> toolIssuances = toolIssuanceRepository.findAll();
        return toolIssuances.stream()
                .map(toolIssuanceMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ToolIssuanceResponseDto create(ToolIssuanceRequestDto toolIssuanceRequestDto) {
        ToolIssuance toolIssuance = toolIssuanceRepository.save(toolIssuanceMapper.toEntity(toolIssuanceRequestDto));
        return toolIssuanceMapper.toResponseDto(toolIssuance);
    }

    @Override
    @Transactional
    public ToolIssuanceResponseDto update(Long id, ToolIssuanceRequestDto toolIssuanceRequestDto) {
        Optional<ToolIssuance> optionalToolIssuance = toolIssuanceRepository.findById(id);
        ToolIssuance toolIssuanceUpdate = toolIssuanceMapper.toEntityUpdate(toolIssuanceRequestDto,
                optionalToolIssuance.orElseThrow(() -> new ResourceNotFoundException(TOOL_ISSUANCE_NOT_FOUND + id)));
        ToolIssuance toolIssuanceSaved = toolIssuanceRepository.save(toolIssuanceUpdate);
        return toolIssuanceMapper.toResponseDto(toolIssuanceSaved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (toolIssuanceRepository.existsById(id)) {
            toolIssuanceRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(TOOL_ISSUANCE_NOT_FOUND + id);
        }
    }

}
