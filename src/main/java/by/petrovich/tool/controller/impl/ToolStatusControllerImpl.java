package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.ToolStatusController;
import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;
import by.petrovich.tool.service.impl.ToolStatusServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static by.petrovich.tool.controller.impl.ToolStatusControllerImpl.TOOL_STATUS_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(TOOL_STATUS_BASE_URL)
@RequiredArgsConstructor
public class ToolStatusControllerImpl implements ToolStatusController {
    public static final String TOOL_STATUS_BASE_URL = "/api/v1/tool-statuses";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final ToolStatusServiceImpl toolStatusService;

    @Override
    @GetMapping
    public ResponseEntity<List<ToolStatusResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolStatusService.findAll());
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<ToolStatusResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolStatusService.find(id));
    }

    @Override
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
    @PostMapping(SLASH)
    public ResponseEntity<ToolStatusResponseDto> create(@Valid @RequestBody ToolStatusRequestDto toolStatusRequestDto) {
        return ResponseEntity.status(CREATED).body(toolStatusService.create(toolStatusRequestDto));
    }

    @Override
    @PutMapping(ID)
    public ResponseEntity<ToolStatusResponseDto> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody ToolStatusRequestDto toolStatusRequestDto) {
        return ResponseEntity.status(OK).body(toolStatusService.update(id, toolStatusRequestDto));
    }

    @Override
    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        toolStatusService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }
}
