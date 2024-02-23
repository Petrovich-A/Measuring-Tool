package by.petrovich.tool.controller.impl;

import by.petrovich.tool.controller.StorageRoomController;
import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;
import by.petrovich.tool.service.impl.StorageRoomServiceImpl;
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
@RequestMapping("/api/v1/storage-rooms")
@RequiredArgsConstructor
public class StorageRoomControllerImpl implements StorageRoomController {

    private final StorageRoomServiceImpl storageRoomService;

    @Override
    @GetMapping
    public ResponseEntity<List<StorageRoomResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(storageRoomService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StorageRoomResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(storageRoomService.find(id));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<StorageRoomResponseDto> create(@Valid @RequestBody StorageRoomRequestDto storageRoomRequestDto) {
        return ResponseEntity.status(CREATED).body(storageRoomService.create(storageRoomRequestDto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StorageRoomResponseDto> update(@PathVariable("id") Long id,
                                                         @Valid @RequestBody StorageRoomRequestDto storageRoomRequestDto) {
        return ResponseEntity.status(OK).body(storageRoomService.update(id, storageRoomRequestDto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        storageRoomService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }
}
