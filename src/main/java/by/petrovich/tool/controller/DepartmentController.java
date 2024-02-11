package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.response.DepartmentResponseDto;
import by.petrovich.tool.service.impl.DepartmentServiceImpl;
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

import static by.petrovich.tool.controller.DepartmentController.DEPARTMENT_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(DEPARTMENT_BASE_URL)
@RequiredArgsConstructor
public class DepartmentController {
    public static final String DEPARTMENT_BASE_URL = "/api/v1/departments";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final DepartmentServiceImpl departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(departmentService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<DepartmentResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(departmentService.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        return ResponseEntity.status(CREATED).body(departmentService.create(departmentRequestDto));

    }

    @PutMapping(ID)
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        return ResponseEntity.status(OK).body(departmentService.update(id, departmentRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
