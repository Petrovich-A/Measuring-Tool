package by.petrovich.tool.controller.impl;

import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.response.ToolIssuanceResponseDto;
import by.petrovich.tool.service.impl.ToolIssuanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/tool-issuances")
@RequiredArgsConstructor
public class ToolIssuanceControllerImpl implements by.petrovich.tool.controller.ToolIssuanceController {

    private final ToolIssuanceServiceImpl toolIssuanceService;

    @Override
    @GetMapping
    public ResponseEntity<List<ToolIssuanceResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(toolIssuanceService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ToolIssuanceResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(toolIssuanceService.find(id));
    }

    @Override
    @PostMapping("/")
    @ResponseStatus(CREATED)
    public ResponseEntity<ToolIssuanceResponseDto> create(@Valid @RequestBody ToolIssuanceRequestDto toolIssuanceRequestDto) {
        return ResponseEntity.status(CREATED).body(toolIssuanceService.create(toolIssuanceRequestDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ToolIssuanceResponseDto> update(@PathVariable("id") Long id,
                                                          @Valid @RequestBody ToolIssuanceRequestDto toolIssuanceRequestDto) {
        return ResponseEntity.status(OK).body(toolIssuanceService.update(id, toolIssuanceRequestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        toolIssuanceService.delete(id);
        return ResponseEntity.status(OK).build();
    }
}
