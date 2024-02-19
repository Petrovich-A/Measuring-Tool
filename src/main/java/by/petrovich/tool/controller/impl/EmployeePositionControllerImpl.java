package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.EmployeePositionController;
import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.service.impl.EmployeePositionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class EmployeePositionControllerImpl implements EmployeePositionController {

    private final EmployeePositionServiceImpl employeePositionServiceImpl;

    @Override
    @Operation(
            summary = "Retrieve all employee positions",
            description = "Get a list of all employee positions available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of employee positions",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    @GetMapping
    public ResponseEntity<List<EmployeePositionResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.findAll());
    }

    @Override
    @Operation(
            summary = "Retrieve an employee position by ID",
            description = "Retrieve detailed information about a specific employee position identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved employee position",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee position not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<EmployeePositionResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.find(id));
    }

    @Override
    @Operation(
            summary = "Create a new employee position",
            description = "Add a new employee position to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created employee position",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    @PostMapping("/")
    public ResponseEntity<EmployeePositionResponseDto> create(
            @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(CREATED).body(employeePositionServiceImpl.create(employeePositionRequestDto));
    }

    @Override
    @Operation(
            summary = "Update an employee position",
            description = "Update details of an existing employee position identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated employee position",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee position not found"
    )
    @PutMapping("/{id}")
    public ResponseEntity<EmployeePositionResponseDto> update(@PathVariable("id") Long id,
                                                              @Valid @RequestBody EmployeePositionRequestDto employeePositionRequestDto) {
        return ResponseEntity.status(OK).body(employeePositionServiceImpl.update(id, employeePositionRequestDto));
    }

    @Override
    @Operation(
            summary = "Delete an employee position",
            description = "Remove an employee position from the system using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted employee position"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee position not found"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        employeePositionServiceImpl.delete(id);
        return ResponseEntity.status(OK).body(id);
    }
}
