package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.response.ToolIssuanceResponseDto;
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

@Tags({@Tag(name = "Tool Issuance Controller", description = "APIs for managing tool issuance")})
public interface ToolIssuanceController {

    @Operation(
            tags = {"Tool Issuance Controller"},
            summary = "Retrieve all tool issuances",
            description = "Get a list of all tool issuances available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of tool issuances",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolIssuanceResponseDto.class))
    )
    ResponseEntity<List<ToolIssuanceResponseDto>> findAll();

    @Operation(
            tags = {"Tool Issuance Controller"},
            summary = "Retrieve a tool issuance by ID",
            description = "Retrieve detailed information about a specific tool issuance identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved tool issuance",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolIssuanceResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool issuance not found"
    )
    ResponseEntity<ToolIssuanceResponseDto> find(@Parameter(
            description = "ID of the tool issuance to retrieve",
            example = "1",
            required = true) Long id);

    @Operation(
            tags = {"Tool Issuance Controller"},
            summary = "Create a new tool issuance",
            description = "Add a new tool issuance to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created tool issuance",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolIssuanceResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool issuance not found")
    ResponseEntity<ToolIssuanceResponseDto> create(@RequestBody(
            description = "Tool issuance details",
            required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolIssuanceRequestDto.class),
                    examples = @ExampleObject(
                            value = "{\"distributingByEmployeeRequestDto\":" +
                                    "{\"id\":1}," +
                                    "\"receivingByEmployeeRequestDto\":" +
                                    "{\"id\":51" +
                                    "}," +
                                    "\"storageRoomRequestDto\":" +
                                    "{\"id\":1" +
                                    "}" +
                                    "}")))
                                                   ToolIssuanceRequestDto toolIssuanceRequestDto);

    @Operation(
            tags = {"Tool Issuance Controller"},
            summary = "Update a tool issuance",
            description = "Update details of an existing tool issuance identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated tool issuance",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolIssuanceResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool issuance not found"
    )
    ResponseEntity<ToolIssuanceResponseDto> update(@Parameter(
            description = "ID of the tool issuance to update",
            example = "3",
            required = true) Long id,
                                                   @RequestBody(
                                                           description = "Tool issuance details",
                                                           required = true,
                                                           content = @Content(
                                                                   mediaType = "application/json",
                                                                   schema = @Schema(implementation = ToolIssuanceRequestDto.class),
                                                                   examples = @ExampleObject(
                                                                           value = "{\"distributingByEmployeeRequestDto\":" +
                                                                                   "{\"id\":101}," +
                                                                                   "\"receivingByEmployeeRequestDto\":" +
                                                                                   "{\"id\":151" +
                                                                                   "}," +
                                                                                   "\"storageRoomRequestDto\":" +
                                                                                   "{\"id\":2" +
                                                                                   "}" +
                                                                                   "}")))
                                                   ToolIssuanceRequestDto toolIssuanceRequestDto);

    @Operation(
            tags = {"Tool Issuance Controller"},
            summary = "Delete a tool issuance",
            description = "Remove a tool issuance from the system using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted tool issuance"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tool issuance not found"
    )
    ResponseEntity<Void> delete(@Parameter(
            description = "ID of the tool issuance to delete",
            example = "3",
            required = true) Long id);
}
