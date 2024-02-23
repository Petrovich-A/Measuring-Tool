package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tags({@Tag(name = "Employee Position Controller", description = "APIs for managing employee positions")})
public interface EmployeePositionController {
    @Operation(
            tags = {"Employee Position Controller"},
            summary = "Retrieve all employee positions",
            description = "Get a list of all employee positions available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of employee positions",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    ResponseEntity<List<EmployeePositionResponseDto>> findAll();

    @Operation(
            tags = {"Employee Position Controller"},
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
    ResponseEntity<EmployeePositionResponseDto> find(@Parameter(description = "The ID of the employee position",
            example = "1",
            required = true) Long id);

    @Operation(
            tags = {"Employee Position Controller"},
            summary = "Create a new employee position",
            description = "Add a new employee position to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created employee position",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeePositionResponseDto.class))
    )
    ResponseEntity<EmployeePositionResponseDto> create(@RequestBody(
            description = "Employee details", required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentRequestDto.class),
                    examples = @ExampleObject(
                            value = "{  \"position\":\"CNC operator\"}"
                    )
            )
    ) EmployeePositionRequestDto employeePositionRequestDto);

    @Operation(
            tags = {"Employee Position Controller"},
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
    ResponseEntity<EmployeePositionResponseDto> update(@Parameter(
            description = "The ID of the employee position",
            example = "4",
            required = true) Long id,
                                                       @RequestBody(
                                                               description = "Updated employee position details", required = true,
                                                               content = @Content(
                                                                       mediaType = "application/json",
                                                                       schema = @Schema(implementation = DepartmentRequestDto.class),
                                                                       examples = @ExampleObject(
                                                                               value = "{  \"position\":\"inspector\"}"
                                                                       )
                                                               )
                                                       ) EmployeePositionRequestDto employeePositionRequestDto);

    @Operation(
            tags = {"Employee Position Controller"},
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
    ResponseEntity<Long> delete(@Parameter(description = "The ID of the employee position",
            example = "4",
            required = true) Long id);
}
