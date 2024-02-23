/**
 * A service interface for ToolType operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on ToolType entities.
 */
public interface ToolTypeService {

    /**
     * Retrieves a ToolType by its ID.
     *
     * @param id The ID of the ToolType to retrieve.
     * @return The ToolTypeResponseDto corresponding to the given ID.
     */
    ToolTypeResponseDto find(Long id);

    /**
     * Retrieves all ToolTypes.
     *
     * @return A list of all ToolTypeResponseDto objects.
     */
    List<ToolTypeResponseDto> findAll();

    /**
     * Creates a new ToolType.
     *
     * @param toolTypeRequestDto The ToolTypeRequestDto containing the data for the new ToolType.
     * @return The ToolTypeResponseDto representing the newly created ToolType.
     */
    ToolTypeResponseDto create(ToolTypeRequestDto toolTypeRequestDto);

    /**
     * Updates an existing ToolType.
     *
     * @param id The ID of the ToolType to update.
     * @param toolTypeRequestDto The ToolTypeRequestDto containing the updated data.
     * @return The ToolTypeResponseDto representing the updated ToolType.
     */
    ToolTypeResponseDto update(Long id, ToolTypeRequestDto toolTypeRequestDto);

    /**
     * Deletes a ToolType by its ID.
     *
     * @param id The ID of the ToolType to delete.
     */
    void delete(Long id);
}
