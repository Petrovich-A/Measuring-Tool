package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.mapper.EmployeePositionMapper;
import by.petrovich.tool.model.EmployeePosition;
import by.petrovich.tool.repository.EmployeePositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeePositionService implements by.petrovich.tool.service.EmployeePositionService {
    private final EmployeePositionRepository positionRepository;
    private final EmployeePositionMapper positionMapper;

    @Override
    public List<EmployeePositionResponseDto> findAll() {
        List<EmployeePosition> employeePositions = positionRepository.findAll();
        return employeePositions.stream()
                .map(positionMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeePositionResponseDto create(EmployeePositionRequestDto employeePositionRequestDto) {
        EmployeePosition employeePosition = positionRepository.save(positionMapper.toEntity(employeePositionRequestDto));
        return positionMapper.toResponseDto(employeePosition);
    }
}
