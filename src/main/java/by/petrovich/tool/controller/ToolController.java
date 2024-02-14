package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tags({@Tag(name = "Tools controller", description = "Tools management APIs")})
public interface ToolController {
    @Operation(
            summary = "Retrieve all tools",
            description = "Get a list of all tools",
            tags = {"Tools controller"})
    @ApiResponse(
            responseCode = "200",
            description = "List of tools retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    ResponseEntity<List<ToolResponseDto>> findAll();

    @Operation(
            summary = "Retrieve a tool by ID",
            description = "Get a specific tool by its ID",
            tags = {"Tools controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<ToolResponseDto> find(@Parameter(description = "ID of the tool to retrieve") Long id);

    @Operation(
            summary = "Create a new tool",
            description = "Create a new tool with the provided details",
            tags = {"Tools controller"})
    @ApiResponse(
            responseCode = "201",
            description = "Tool created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    ResponseEntity<ToolResponseDto> create(ToolRequestDto toolRequestDto);

    @Operation(
            summary = "Update a tool",
            description = "Update an existing tool with the provided details",
            tags = {"Tools controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<ToolResponseDto> update(@Parameter(description = "ID of the tool to update") Long id,
                                           ToolRequestDto toolRequestDto);

    @Operation(
            summary = "Delete a tool",
            description = "Delete a tool by its ID",
            tags = {"Tools controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool deleted successfully")
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<Void> delete(@Parameter(description = "ID of the tool to delete") Long id);
}
