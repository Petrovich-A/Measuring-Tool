package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tags({@Tag(name = "Tool Type Controller", description = "APIs for managing tool types")})
public interface ToolTypeController {
    @Operation(
            tags = {"Tool Type Controller"},
            summary = "Retrieve all tool types",
            description = "Get a list of all tool types available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of tool types",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolTypeResponseDto.class))
    )
    ResponseEntity<List<ToolTypeResponseDto>> findAll();

    @Operation(
            tags = {"Tool Type Controller"},
            summary = "Retrieve a tool type by ID",
            description = "Retrieve detailed information about a specific tool type identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved tool type",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolTypeResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool type not found"
    )
    ResponseEntity<ToolTypeResponseDto> find(@Parameter(description = "ID of the tool type to retrieve",
                                                        example = "1",
                                                        required = true) Long id);

    @Operation(
            tags = {"Tool Type Controller"},
            summary = "Create a new tool type",
            description = "Add a new tool type to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created tool type",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolTypeResponseDto.class))
    )
    ResponseEntity<ToolTypeResponseDto> create(@Parameter(description = "Tool type details",
                                                          required = true) ToolTypeRequestDto toolTypeRequestDto);

    @Operation(
            tags = {"Tool Type Controller"},
            summary = "Update a tool type",
            description = "Update details of an existing tool type identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated tool type",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolTypeResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool type not found"
    )
    ResponseEntity<ToolTypeResponseDto> update(@Parameter(description = "ID of the tool type to update",
                                                          example = "1",
                                                          required = true) Long id,
                                               @Parameter(description = "Updated tool type details",
                                                          required = true) ToolTypeRequestDto toolTypeRequestDto);

    @Operation(
            tags = {"Tool Type Controller"},
            summary = "Delete a tool type",
            description = "Remove a tool type from the system using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted tool type"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool type not found"
    )
    ResponseEntity<Long> delete(@Parameter(description = "ID of the tool type to delete",
                                           example = "1",
                                           required = true) Long id);
}
