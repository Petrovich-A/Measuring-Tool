package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.EmployeeMapper;
import by.petrovich.tool.model.Employee;
import by.petrovich.tool.repository.EmployeeRepository;
import by.petrovich.tool.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto find(Long id) {
        return employeeMapper.toResponseDto(employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id)));
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(employeeMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeRequestDto));
        return employeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employeeUpdate = employeeMapper.toEntityUpdate(employeeRequestDto,
                optionalEmployee.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id)));
        Employee employeeSaved = employeeRepository.save(employeeUpdate);
        return employeeMapper.toResponseDto(employeeSaved);
    }

    @Override
    public void delete(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
    }

}
