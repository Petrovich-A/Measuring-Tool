package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;
import by.petrovich.tool.service.impl.ToolStatusServiceImpl;
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

import static by.petrovich.tool.controller.ToolStatusController.TOOL_STATUS_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(TOOL_STATUS_BASE_URL)
@RequiredArgsConstructor
public class ToolStatusController {
    public static final String TOOL_STATUS_BASE_URL = "/api/v1/tool-statuses";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final ToolStatusServiceImpl toolStatusService;

    @GetMapping
    public ResponseEntity<List<ToolStatusResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolStatusService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<ToolStatusResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolStatusService.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<ToolStatusResponseDto> create(@Valid @RequestBody ToolStatusRequestDto toolStatusRequestDto) {
        return ResponseEntity.status(CREATED).body(toolStatusService.create(toolStatusRequestDto));

    }

    @PutMapping(ID)
    public ResponseEntity<ToolStatusResponseDto> update(@PathVariable("id") Long id,
                                                        @Valid @RequestBody ToolStatusRequestDto toolStatusRequestDto) {
        return ResponseEntity.status(OK).body(toolStatusService.update(id, toolStatusRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        toolStatusService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
