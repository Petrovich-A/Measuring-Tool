package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
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

@Tags({@Tag(name = "Tool controller", description = "Tools management APIs")})
public interface ToolController {
    @Operation(
            summary = "Retrieve all tools",
            description = "Get a list of all tools",
            tags = {"Tool controller"})
    @ApiResponse(
            responseCode = "200",
            description = "List of tools retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    ResponseEntity<List<ToolResponseDto>> findAll();

    @Operation(
            summary = "Retrieve a tool by ID",
            description = "Get a specific tool by its ID",
            tags = {"Tool controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<ToolResponseDto> find(@Parameter(description = "ID of the tool to retrieve",
            example = "1",
            required = true) Long id);

    @Operation(
            summary = "Create a new tool",
            description = "Create a new tool with the provided details",
            tags = {"Tool controller"})
    @ApiResponse(
            responseCode = "201",
            description = "Tool created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    ResponseEntity<ToolResponseDto> create(
            @RequestBody(
                    description = "Tool details",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentRequestDto.class),
                            examples = @ExampleObject(
                                    value = "{" +
                                            "\"designation\": \"2100-0001\"," +
                                            "\"laboratoryNumber\": \"155400\"," +
                                            "\"measurableSizeRange\": \"75-100\"," +
                                            "\"toolTypeRequestDto\": {" +
                                            "    \"id\": 2" +
                                            "}," +
                                            "\"toolStatusRequestDto\": {" +
                                            "    \"id\": 3" +
                                            "}," +
                                            "\"toolIssuanceRequestDto\": null," +
                                            "\"storageRoomRequestDto\": {" +
                                            "    \"id\": 1" +
                                            "}" +
                                            "}"
                            )
                    )
            ) ToolRequestDto toolRequestDto);

    @Operation(
            summary = "Update a tool",
            description = "Update an existing tool with the provided details",
            tags = {"Tool controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ToolResponseDto.class)))
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<ToolResponseDto> update(@Parameter(
            description = "ID of the tool to update",
            example = "403",
            required = true) Long id,
                                           @RequestBody(
                                                   description = "Updated tool details",
                                                   required = true,
                                                   content = @Content(
                                                           mediaType = "application/json",
                                                           schema = @Schema(implementation = DepartmentRequestDto.class),
                                                           examples = @ExampleObject(
                                                                   value = "{" +
                                                                           "\"designation\": \"1099-0002\"," +
                                                                           "\"laboratoryNumber\": \"1600\"," +
                                                                           "\"measurableSizeRange\": \"100\"," +
                                                                           "\"toolTypeRequestDto\": {" +
                                                                           "    \"id\": 3" +
                                                                           "}," +
                                                                           "\"toolStatusRequestDto\": {" +
                                                                           "    \"id\": 1" +
                                                                           "}," +
                                                                           "\"toolIssuanceRequestDto\": null," +
                                                                           "\"storageRoomRequestDto\": {" +
                                                                           "    \"id\": 3" +
                                                                           "}" +
                                                                           "}"
                                                           )
                                                   )
                                           ) ToolRequestDto toolRequestDto);

    @Operation(
            summary = "Delete a tool",
            description = "Delete a tool by its ID",
            tags = {"Tool controller"})
    @ApiResponse(
            responseCode = "200",
            description = "Tool deleted successfully")
    @ApiResponse(
            responseCode = "404",
            description = "Tool not found")
    ResponseEntity<Void> delete(@Parameter(description = "ID of the tool to delete",
            example = "9",
            required = true) Long id);

    @Operation(
            summary = "Submit Tool for Precision Check",
            description = "Submits a tool for precision check by updating its status to 'Under Precision Check'.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully submitted tool for precision check",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToolResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tool not found"
                    )
            }
    )
    ResponseEntity<ToolResponseDto> submitForPrecisionCheck(@Parameter(
            description = "ID of the tool to submit for precision check",
            example = "1",
            required = true
    ) Long id);

}
