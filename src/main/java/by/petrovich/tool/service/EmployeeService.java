package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDto find(Long id);

    List<EmployeeResponseDto> findAll();

    EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto);

    void delete(Long id);
}
