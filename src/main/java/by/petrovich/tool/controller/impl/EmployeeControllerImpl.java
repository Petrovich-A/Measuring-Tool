package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.EmployeeController;
import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeServiceImpl employeeService;

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(employeeService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(employeeService.find(id));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(CREATED).body(employeeService.create(employeeRequestDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(OK).body(employeeService.update(id, employeeRequestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }
}
