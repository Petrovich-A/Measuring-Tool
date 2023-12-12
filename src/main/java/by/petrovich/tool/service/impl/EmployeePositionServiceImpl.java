package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.EmployeePositionMapper;
import by.petrovich.tool.model.EmployeePosition;
import by.petrovich.tool.repository.EmployeePositionRepository;
import by.petrovich.tool.service.EmployeePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeePositionServiceImpl implements EmployeePositionService {
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
    public EmployeePositionResponseDto find(Long id) {
        Optional<EmployeePosition> employeePosition = positionRepository.findById(id);
        return positionMapper.toResponseDto(
                employeePosition.orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id)));
    }

    @Override
    public EmployeePositionResponseDto create(EmployeePositionRequestDto employeePositionRequestDto) {
        EmployeePosition employeePosition = positionRepository.save(positionMapper.toEntity(employeePositionRequestDto));
        return positionMapper.toResponseDto(employeePosition);
    }

    @Override
    public EmployeePositionResponseDto update(Long id, EmployeePositionRequestDto employeePositionRequestDto) {
        Optional<EmployeePosition> employeePosition = positionRepository.findById(id);
        EmployeePosition entityUpdated = positionMapper.toEntityUpdate(employeePositionRequestDto,
                employeePosition.orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id)));
        EmployeePosition employeePositionSaved = positionRepository.saveAndFlush(entityUpdated);
        return positionMapper.toResponseDto(employeePositionSaved);
    }

    @Override
    public void delete(Long id) {
        Optional<EmployeePosition> employeePosition = positionRepository.findById(id);
        employeePosition.orElseThrow(() -> new ResourceNotFoundException("Position not found with id: " + id));
        positionRepository.deleteById(id);
    }

}
