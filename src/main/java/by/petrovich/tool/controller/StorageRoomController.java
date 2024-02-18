package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;
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

@Tags({@Tag(name = "Storage Room Controller", description = "APIs for managing storage rooms")})
public interface StorageRoomController {
    @Operation(
            tags = {"Storage Room Controller"},
            summary = "Retrieve all storage rooms",
            description = "Get a list of all storage rooms available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of storage rooms",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StorageRoomResponseDto.class))
    )
    ResponseEntity<List<StorageRoomResponseDto>> findAll();

    @Operation(
            tags = {"Storage Room Controller"},
            summary = "Retrieve a storage room by ID",
            description = "Retrieve detailed information about a specific storage room identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved storage room",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StorageRoomResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Storage room not found"
    )
    ResponseEntity<StorageRoomResponseDto> find(@Parameter(description = "The ID of the storage room",
            example = "1",
            required = true) Long id);

    @Operation(
            tags = {"Storage Room Controller"},
            summary = "Create a new storage room",
            description = "Add a new storage room to the system with the provided details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Successfully created storage room",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StorageRoomResponseDto.class))
    )
    ResponseEntity<StorageRoomResponseDto> create(@RequestBody(
            description = "Storage room details",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DepartmentRequestDto.class),
                    examples = @ExampleObject(
                            value = "{" +
                                    "\"departmentRequestDto\":{" +
                                    "\"id\":\"2\"" +
                                    "}" +
                                    "}"
                    )
            )
    ) StorageRoomRequestDto storageRoomRequestDto);

    @Operation(
            tags = {"Storage Room Controller"},
            summary = "Update a storage room",
            description = "Update details of an existing storage room identified by its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully updated storage room",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StorageRoomResponseDto.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Storage room not found"
    )
    ResponseEntity<StorageRoomResponseDto> update(@Parameter(description = "The ID of the storage room",
            example = "3",
            required = true) Long id,
                                                  @RequestBody(
                                                          description = "Updated storage room details",
                                                          required = true,
                                                          content = @Content(
                                                                  mediaType = "application/json",
                                                                  schema = @Schema(implementation = DepartmentRequestDto.class),
                                                                  examples = @ExampleObject(
                                                                          value = "{" +
                                                                                  "\"departmentRequestDto\":{" +
                                                                                  "\"id\":\"1\"" +
                                                                                  "}" +
                                                                                  "}"
                                                                  )
                                                          )
                                                  ) StorageRoomRequestDto storageRoomRequestDto);

    @Operation(
            tags = {"Storage Room Controller"},
            summary = "Delete a storage room",
            description = "Remove a storage room from the system using its unique ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Successfully deleted storage room"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Storage room not found"
    )
    ResponseEntity<Long> delete(@Parameter(description = "The ID of the storage room",
            example = "3",
            required = true) Long id);
}
