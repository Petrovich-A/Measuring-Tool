package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.service.impl.EmployeePositionServiceImpl;

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

import static by.petrovich.tool.controller.EmployeePositionController.EMPLOYEE_POSITION_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(EMPLOYEE_POSITION_BASE_URL)
@RequiredArgsConstructor
public class EmployeePositionController {
    public static final String EMPLOYEE_POSITION_BASE_URL = "/api/v1/positions";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final EmployeePositionServiceImpl employeePositionServiceImpl;

    @GetMapping
    public ResponseEntity<List<EmployeePositionResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<EmployeePositionResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<EmployeePositionResponseDto> create(
            @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(CREATED).body(employeePositionServiceImpl.create(employeePositionRequestDto));
    }

    @PutMapping(ID)
    public ResponseEntity<EmployeePositionResponseDto> update(@PathVariable("id") Long id,
                                                              @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.update(id, employeePositionRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        employeePositionServiceImpl.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
