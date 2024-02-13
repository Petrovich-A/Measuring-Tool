package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;
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

import static by.petrovich.tool.controller.StorageRoomController.STORAGE_ROOM_BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(STORAGE_ROOM_BASE_URL)
@RequiredArgsConstructor
public class StorageRoomController {
    public static final String STORAGE_ROOM_BASE_URL = "/api/v1/storage-rooms";
    public static final String ID = "/{id}";
    public static final String SLASH = "/";

    private final StorageRoomServiceImpl storageRoomService;

    @GetMapping
    public ResponseEntity<List<StorageRoomResponseDto>> findAll() {
        return ResponseEntity.status(OK).body(storageRoomService.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<StorageRoomResponseDto> find(@PathVariable("id") Long id) {
        return ResponseEntity.status(OK).body(storageRoomService.find(id));
    }

    @PostMapping(SLASH)
    public ResponseEntity<StorageRoomResponseDto> create(@Valid @RequestBody StorageRoomRequestDto storageRoomRequestDto) {
        return ResponseEntity.status(CREATED).body(storageRoomService.create(storageRoomRequestDto));

    }

    @PutMapping(ID)
    public ResponseEntity<StorageRoomResponseDto> update(@PathVariable("id") Long id,
                                                      @Valid @RequestBody StorageRoomRequestDto storageRoomRequestDto) {
        return ResponseEntity.status(OK).body(storageRoomService.update(id, storageRoomRequestDto));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        storageRoomService.delete(id);
        return ResponseEntity.status(OK).body(id);
    }

}
