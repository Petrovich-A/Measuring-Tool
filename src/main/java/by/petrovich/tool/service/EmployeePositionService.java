package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;

import java.util.List;

public interface EmployeePositionService {
    List<EmployeePositionResponseDto> findAll();

    EmployeePositionResponseDto find(Long id);

    EmployeePositionResponseDto create(EmployeePositionRequestDto employeePosition);

    EmployeePositionResponseDto update(Long id, EmployeePositionRequestDto employeePositionRequestDto);

    void delete(Long id);
}
