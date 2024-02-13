/**
 * A service interface for StorageRoom operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on StorageRoom entities.
 */
public interface StorageRoomService {

    /**
     * Retrieves a StorageRoom by its ID.
     *
     * @param id The ID of the StorageRoom to retrieve.
     * @return The StorageRoomResponseDto corresponding to the given ID.
     */
    StorageRoomResponseDto find(Long id);

    /**
     * Retrieves all StorageRooms.
     *
     * @return A list of all StorageRoomResponseDto objects.
     */
    List<StorageRoomResponseDto> findAll();

    /**
     * Creates a new StorageRoom.
     *
     * @param storageRoomRequestDto The StorageRoomRequestDto containing the data for the new StorageRoom.
     * @return The StorageRoomResponseDto representing the newly created StorageRoom.
     */
    StorageRoomResponseDto create(StorageRoomRequestDto storageRoomRequestDto);

    /**
     * Updates an existing StorageRoom.
     *
     * @param id The ID of the StorageRoom to update.
     * @param storageRoomRequestDto The StorageRoomRequestDto containing the updated data.
     * @return The StorageRoomResponseDto representing the updated StorageRoom.
     */
    StorageRoomResponseDto update(Long id, StorageRoomRequestDto storageRoomRequestDto);

    /**
     * Deletes a StorageRoom by its ID.
     *
     * @param id The ID of the StorageRoom to delete.
     */
    void delete(Long id);
}
