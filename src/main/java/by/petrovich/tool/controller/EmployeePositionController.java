package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.service.impl.EmployeePositionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class EmployeePositionController {
    private final EmployeePositionServiceImpl employeePositionServiceImpl;

    @GetMapping
    public ResponseEntity<List<EmployeePositionResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeePositionServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePositionResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(employeePositionServiceImpl.find(id));
    }

    @PostMapping("/")
    public ResponseEntity<EmployeePositionResponseDto> create(
            @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeePositionServiceImpl.create(employeePositionRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeePositionResponseDto> update(@PathVariable("id") Long id,
                                                              @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(employeePositionServiceImpl.update(id, employeePositionRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        employeePositionServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee position deleted successfully");
    }

}
