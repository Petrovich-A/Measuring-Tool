package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.service.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static by.petrovich.tool.controller.EmployeeController.EMPLOYEE_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(EMPLOYEE_BASE_URL)
@RequiredArgsConstructor
public class EmployeeController {
    public static final String EMPLOYEE_BASE_URL = "/api/v1/employees";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final EmployeeServiceImpl employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(employeeService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<EmployeeResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(employeeService.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<EmployeeResponseDto> create(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(CREATED).body(employeeService.create(employeeRequestDto));

    }

    @PutMapping(ID)
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.status(OK).body(employeeService.update(id, employeeRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
