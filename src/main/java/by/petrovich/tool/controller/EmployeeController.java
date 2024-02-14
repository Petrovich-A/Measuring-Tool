package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tags({@Tag(name = "Employee controller", description = "Employees management APIs")})
public interface EmployeeController {
    @Operation(
            summary = "Retrieve all employees",
            description = "Get a list of all employees available in the system.",
            tags = {"Employee Controller"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of employees",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    ResponseEntity<List<EmployeeResponseDto>> findAll();

    @Operation(
            summary = "Retrieve an employee by ID",
            description = "Retrieve detailed information about a specific employee identified by its unique ID.",
            tags = {"Employee Controller"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved employee",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee not found"
    )
    ResponseEntity<EmployeeResponseDto> find(Long id);

    @Operation(
            summary = "Create a new employee",
            description = "Add a new employee to the system with the provided details.",
            tags = {"Employee Controller"}
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created employee",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    ResponseEntity<EmployeeResponseDto> create(EmployeeRequestDto employeeRequestDto);

    @Operation(
            summary = "Update an employee",
            description = "Update details of an existing employee identified by its unique ID.",
            tags = {"Employee Controller"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated employee",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee not found"
    )
    ResponseEntity<EmployeeResponseDto> update(Long id, EmployeeRequestDto employeeRequestDto);

    @Operation(
            summary = "Delete an employee",
            description = "Remove an employee from the system using its unique ID.",
            tags = {"Employee Controller"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted employee"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee not found"
    )
    ResponseEntity<Long> delete(Long id);
}
