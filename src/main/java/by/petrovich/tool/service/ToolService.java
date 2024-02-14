/**
 * A service interface for Tool operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on Tool entities.
 */
public interface ToolService {

    /**
     * Retrieves a Tool by its ID.
     *
     * @param id The ID of the Tool to retrieve.
     * @return The ToolResponseDto corresponding to the given ID.
     */
    ToolResponseDto find(Long id);

    /**
     * Retrieves all Tools.
     *
     * @return A list of all ToolResponseDto objects.
     */
    List<ToolResponseDto> findAll();

    /**
     * Creates a new Tool.
     *
     * @param toolRequestDto The ToolRequestDto containing the data for the new Tool.
     * @return The ToolResponseDto representing the newly created Tool.
     */
    ToolResponseDto create(ToolRequestDto toolRequestDto);

    /**
     * Updates an existing Tool.
     *
     * @param id The ID of the Tool to update.
     * @param toolRequestDto The ToolRequestDto containing the updated data.
     * @return The ToolResponseDto representing the updated Tool.
     */
    ToolResponseDto update(Long id, ToolRequestDto toolRequestDto);

    /**
     * Deletes a Tool by its ID.
     *
     * @param id The ID of the Tool to delete.
     */
    void delete(Long id);
}
