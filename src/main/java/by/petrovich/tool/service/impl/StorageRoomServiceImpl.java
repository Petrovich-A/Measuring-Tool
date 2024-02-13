package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.StorageRoomMapper;
import by.petrovich.tool.model.StorageRoom;
import by.petrovich.tool.repository.StorageRoomRepository;
import by.petrovich.tool.service.StorageRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageRoomServiceImpl implements StorageRoomService {
    public static final String STORAGE_ROOM_NOT_FOUND = "Storage room not found with id: ";
    private final StorageRoomRepository storageRoomRepository;
    private final StorageRoomMapper storageRoomMapper;

    @Override
    public StorageRoomResponseDto find(Long id) {
        return storageRoomMapper.toResponseDto(storageRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STORAGE_ROOM_NOT_FOUND + id)));
    }

    @Override
    public List<StorageRoomResponseDto> findAll() {
        List<StorageRoom> storageRooms = storageRoomRepository.findAll();
        return storageRooms.stream()
                .map(storageRoomMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public StorageRoomResponseDto create(StorageRoomRequestDto storageRoomRequestDto) {
        StorageRoom storageRoom = storageRoomRepository.save(storageRoomMapper.toEntity(storageRoomRequestDto));
        return storageRoomMapper.toResponseDto(storageRoom);
    }

    @Override
    public StorageRoomResponseDto update(Long id, StorageRoomRequestDto storageRoomRequestDto) {
        StorageRoom storageRoom = storageRoomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(STORAGE_ROOM_NOT_FOUND + id));
        storageRoomMapper.toEntityUpdate(storageRoomRequestDto, storageRoom);
        StorageRoom updatedStorageRoom = storageRoomRepository.save(storageRoom);
        return storageRoomMapper.toResponseDto(updatedStorageRoom);
    }

    @Override
    public void delete(Long id) {
        if (storageRoomRepository.existsById(id)) {
            storageRoomRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(STORAGE_ROOM_NOT_FOUND + id);
        }
    }

}
