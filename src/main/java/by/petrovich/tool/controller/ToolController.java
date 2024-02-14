package by.petrovich.tool.controller;


import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.service.impl.ToolServiceImpl;
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

import static by.petrovich.tool.controller.ToolController.TOOL_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(TOOL_BASE_URL)
@RequiredArgsConstructor
public class ToolController {
    public static final String TOOL_BASE_URL = "/api/v1/tools";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final ToolServiceImpl toolService;

    @GetMapping
    public ResponseEntity<List<ToolResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<ToolResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolService.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<ToolResponseDto> create(@Valid @RequestBody ToolRequestDto toolRequestDto) {
        return ResponseEntity.status(CREATED).body(toolService.create(toolRequestDto));
    }

    @PutMapping(ID)
    public ResponseEntity<ToolResponseDto> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody ToolRequestDto toolRequestDto) {
        return ResponseEntity.status(OK).body(toolService.update(id, toolRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        toolService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
