package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.response.DepartmentResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.DepartmentMapper;
import by.petrovich.tool.model.Department;
import by.petrovich.tool.repository.DepartmentRepository;
import by.petrovich.tool.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {
    public static final String DEPARTMENT_NOT_FOUND = "Department not found with id: ";
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto find(Long id) {
        return departmentMapper.toResponseDto(departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DEPARTMENT_NOT_FOUND + id)));
    }

    @Override
    public List<DepartmentResponseDto> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(departmentMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DepartmentResponseDto create(DepartmentRequestDto departmentRequestDto) {
        Department department = departmentRepository.save(departmentMapper.toEntity(departmentRequestDto));
        return departmentMapper.toResponseDto(department);
    }

    @Override
    @Transactional
    public DepartmentResponseDto update(Long id, DepartmentRequestDto departmentRequestDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department departmentUpdate = departmentMapper.toEntityUpdate(departmentRequestDto,
                optionalDepartment.orElseThrow(() -> new ResourceNotFoundException(DEPARTMENT_NOT_FOUND + id)));
        Department departmentSaved = departmentRepository.save(departmentUpdate);
        return departmentMapper.toResponseDto(departmentSaved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(DEPARTMENT_NOT_FOUND + id);
        }
    }

}
