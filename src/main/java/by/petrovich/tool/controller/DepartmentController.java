package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.response.DepartmentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tags({@Tag(name = "Department controller", description = "Departments management APIs")})
public interface DepartmentController {
    @Operation(
            summary = "Retrieve all departments",
            description = "Get a list of all departments available in the system.",
            tags = {"Department controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of departments",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentResponseDto.class)))
    ResponseEntity<List<DepartmentResponseDto>> findAll();

    @Operation(
            summary = "Retrieve a department by ID",
            description = "Retrieve detailed information about a specific department identified by its unique ID.",
            tags = {"Department controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved department",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Department not found")
    ResponseEntity<DepartmentResponseDto> find(Long id);

    @Operation(
            summary = "Create a new department",
            description = "Add a new department to the system with the provided details.",
            tags = {"Department controller"})
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created department",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentResponseDto.class)))
    ResponseEntity<DepartmentResponseDto> create(DepartmentRequestDto departmentRequestDto);


    @Operation(
            summary = "Update a department",
            description = "Update details of an existing department identified by its unique ID.",
            tags = {"Department controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated department",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Department not found")
    ResponseEntity<DepartmentResponseDto> update(Long id, DepartmentRequestDto departmentRequestDto);

    @Operation(
            summary = "Delete a department",
            description = "Remove a department from the system using its unique ID.",
            tags = {"Department controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted department")
    @ApiResponse(
            responseCode = "404",
            description = "Department not found")
    ResponseEntity<Long> delete(Long id);
}
