package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.ToolController;
import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.service.impl.ToolServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static by.petrovich.tool.util.Pattern.DATA_TIME_REQUEST_PATTERN;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/tools")
@RequiredArgsConstructor
public class ToolControllerImpl implements ToolController {

    private final ToolServiceImpl toolService;

    @Override
    @GetMapping
    public ResponseEntity<List<ToolResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ToolResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolService.find(id));
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(CREATED)
    public ResponseEntity<ToolResponseDto> create(@Valid @RequestBody ToolRequestDto toolRequestDto) {
        return ResponseEntity.status(CREATED).body(toolService.create(toolRequestDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ToolResponseDto> update(@PathVariable("id") Long id,
                                                  @Valid @RequestBody ToolRequestDto toolRequestDto) {
        return ResponseEntity.status(OK).body(toolService.update(id, toolRequestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        toolService.delete(id);
        return ResponseEntity.status(OK).build();
    }

    @Override
    @PostMapping("/{id}/submit-for-precision-check")
    public ResponseEntity<ToolResponseDto> submitForPrecisionCheck(@PathVariable Long id,
                                                                   @RequestParam("finishDatetime")
                                                                   @DateTimeFormat(pattern = DATA_TIME_REQUEST_PATTERN)
                                                                   LocalDateTime finishDatetime) {
        return ResponseEntity.status(CREATED).body(toolService.submitForPrecisionCheck(id, finishDatetime));
    }

    @GetMapping("/find-by/{statusId}")
    public ResponseEntity<List<ToolResponseDto>> findBy(@PathVariable("statusId") Long statusId) {
        return ResponseEntity.status(OK).body(toolService.findBy(statusId));
    }

}
