package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.ToolTypeController;
import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;
import by.petrovich.tool.service.impl.ToolTypeServiceImpl;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(ToolTypeControllerImpl.TOOL_TYPE_BASE_URL)
@RequiredArgsConstructor
public class ToolTypeControllerImpl implements ToolTypeController {
    public static final String TOOL_TYPE_BASE_URL = "/api/v1/tool-types";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final ToolTypeServiceImpl toolTypeService;

    @Override
    @GetMapping
    public ResponseEntity<List<ToolTypeResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolTypeService.findAll());
    }

    @Override
    @GetMapping(ID)
    public ResponseEntity<ToolTypeResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolTypeService.find(id));
    }

    @Override
    @PostMapping(SLASH)
    public ResponseEntity<ToolTypeResponseDto> create(@Valid @RequestBody ToolTypeRequestDto toolTypeRequestDto) {
        return ResponseEntity.status(CREATED).body(toolTypeService.create(toolTypeRequestDto));
    }

    @Override
    @PutMapping(ID)
    public ResponseEntity<ToolTypeResponseDto> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody ToolTypeRequestDto toolTypeRequestDto) {
        return ResponseEntity.status(OK).body(toolTypeService.update(id, toolTypeRequestDto));
    }

    @Override
    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        toolTypeService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }
}
