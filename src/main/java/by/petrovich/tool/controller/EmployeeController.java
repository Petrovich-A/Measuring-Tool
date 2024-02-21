package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
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

@Tags({@Tag(name = "Employee controller", description = "Employees management APIs")})
public interface EmployeeController {
    @Operation(
            summary = "Retrieve all employees",
            description = "Get a list of all employees available in the system.",
            tags = {"Employee controller"}
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
            tags = {"Employee controller"}
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
    ResponseEntity<EmployeeResponseDto> find(@Parameter(description = "The ID of the employee",
            example = "1",
            required = true) Long id);

    @Operation(
            summary = "Find employees by surname",
            description = "Search for employees whose last name contains a specific substring, regardless of the case. " +
                    "For instance, if the surname 'Smith' exists in the database, using 'mi' will return " +
                    "employees with the last name 'Smith', 'Jamison', 'Mitchell' etc."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of employees",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    ResponseEntity<List<EmployeeResponseDto>> findByCriteria(@Parameter(
            description = "Surname's substring to search for",
            required = true,
            example = "petr") String surname);

    @Operation(
            summary = "Create a new employee",
            description = "Add a new employee to the system with the provided details.",
            tags = {"Employee controller"}
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created employee",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmployeeResponseDto.class))
    )
    ResponseEntity<EmployeeResponseDto> create(@RequestBody(
            description = "Employee details", required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentRequestDto.class),
                    examples = @ExampleObject(value =
                            "{  \"personnelNumber\":\"41525\"," +
                                    " \"name\":\"Alexandr\"," +
                                    "\"surname\":\"Alexandrov\"," +
                                    "\"patronymic\":\"Alexandrovich\"," +
                                    "\"email\":\"alexandrov@mail.com\"," +
                                    "\"employeePositionRequestDto\": {" +
                                    "\"id\":\"1\"" +
                                    "}," +
                                    "\"departmentRequestDto\":{" +
                                    "\"id\":\"1\"" +
                                    "}}"
                    )
            )
    ) EmployeeRequestDto employeeRequestDto);

    @Operation(
            summary = "Update an employee",
            description = "Update details of an existing employee identified by its unique ID.",
            tags = {"Employee controller"}
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
    ResponseEntity<EmployeeResponseDto> update(@Parameter(
            description = "The ID of the employee",
            example = "151",
            required = true) Long id,
                                               @RequestBody(
                                                       description = "Updated employee details", required = true,
                                                       content = @Content(
                                                               mediaType = "application/json",
                                                               schema = @Schema(implementation = DepartmentRequestDto.class),
                                                               examples = @ExampleObject(value =
                                                                       "{  \"personnelNumber\":\"21150\"," +
                                                                               " \"name\":\"Alex\"," +
                                                                               "\"surname\":\"Nazarov\"," +
                                                                               "\"patronymic\":\"Mihaylovich\"," +
                                                                               "\"email\":\"nazarov@mail.com\"," +
                                                                               "\"employeePositionRequestDto\": {" +
                                                                               "\"id\":\"2\"" +
                                                                               "}," +
                                                                               "\"departmentRequestDto\":{" +
                                                                               "\"id\":\"2\"" +
                                                                               "}}"
                                                               )
                                                       )
                                               ) EmployeeRequestDto employeeRequestDto);

    @Operation(
            summary = "Delete an employee",
            description = "Remove an employee from the system using its unique ID.",
            tags = {"Employee controller"}
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted employee"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Employee not found"
    )
    ResponseEntity<Long> delete(@Parameter(
            description = "The ID of the employee",
            example = "1",
            required = true) Long id);
}
