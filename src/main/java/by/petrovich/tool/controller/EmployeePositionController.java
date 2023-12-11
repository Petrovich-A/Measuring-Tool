package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.service.impl.EmployeePositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class EmployeePositionController {
    private final EmployeePositionService employeePositionService;

    @GetMapping
    public ResponseEntity<List<EmployeePositionResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employeePositionService.findAll());
    }

}
