package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.model.EmployeePosition;

import java.util.List;

public interface EmployeePositionService {
    List<EmployeePositionResponseDto> findAll();

    EmployeePositionResponseDto create(EmployeePositionRequestDto employeePosition);
}
