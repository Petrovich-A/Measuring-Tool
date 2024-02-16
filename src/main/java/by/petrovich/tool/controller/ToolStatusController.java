package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;
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

@Tags({@Tag(name = "Tool Status controller", description = "APIs for managing tool statuses")})
public interface ToolStatusController {
    @Operation(
            tags = {"Tool Status controller"},
            summary = "Retrieve all tool statuses",
            description = "Get a list of all tool statuses available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of tool statuses",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolStatusResponseDto.class))
    )
    ResponseEntity<List<ToolStatusResponseDto>> findAll();

    @Operation(
            tags = {"Tool Status controller"},
            summary = "Retrieve a tool status by ID",
            description = "Retrieve detailed information about a specific tool status identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved tool status",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolStatusResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool status not found"
    )
    ResponseEntity<ToolStatusResponseDto> find(@Parameter(description = "ID of the tool status to retrieve",
            example = "1",
            required = true) Long id);

    @Operation(
            tags = {"Tool Status controller"},
            summary = "Create a new tool status",
            description = "Add a new tool status to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created tool status",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolStatusResponseDto.class))
    )
    ResponseEntity<ToolStatusResponseDto> create(@RequestBody(
            description = "Tool status details",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentRequestDto.class),
                    examples = @ExampleObject(
                            value = "{  \"name\":\"deprecated\"}"
                    )
            )
    ) ToolStatusRequestDto toolStatusRequestDto);

    @Operation(
            tags = {"Tool Status controller"},
            summary = "Update a tool status",
            description = "Update details of an existing tool status identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated tool status",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolStatusResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool status not found"
    )
    ResponseEntity<ToolStatusResponseDto> update(@Parameter(description = "ID of the tool status to update",
            example = "4",
            required = true) Long id,
                                                 @RequestBody(
                                                         description = "Updated tool status details",
                                                         required = true,
                                                         content = @Content(
                                                                 mediaType = "application/json",
                                                                 schema = @Schema(implementation = DepartmentRequestDto.class),
                                                                 examples = @ExampleObject(
                                                                         value = "{  \"name\":\"deleted\"}"
                                                                 )
                                                         )
                                                 ) ToolStatusRequestDto toolStatusRequestDto);

    @Operation(
            tags = {"Tool Status controller"},
            summary = "Delete a tool status",
            description = "Remove a tool status from the system using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted tool status"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool status not found"
    )
    ResponseEntity<Long> delete(@Parameter(description = "ID of the tool status to delete",
            example = "4",
            required = true) Long id);
}
