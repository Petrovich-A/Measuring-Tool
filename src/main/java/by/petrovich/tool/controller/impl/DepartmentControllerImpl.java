package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.DepartmentController;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentServiceImpl departmentService;

    @Override
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(departmentService.findAll());
    }

    @Override

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(departmentService.find(id));
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(CREATED)
    public ResponseEntity<DepartmentResponseDto> create(@Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        return ResponseEntity.status(CREATED).body(departmentService.create(departmentRequestDto));

    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDto> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody DepartmentRequestDto departmentRequestDto) {
        return ResponseEntity.status(OK).body(departmentService.update(id, departmentRequestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
