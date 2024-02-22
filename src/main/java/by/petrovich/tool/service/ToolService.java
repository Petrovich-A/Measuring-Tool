/**
 * A service interface for Tool operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;

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
     * Creates a new Tool based on the provided ToolRequestDto.
     * <p>
     * This method saves the Tool entity obtained by mapping the ToolRequestDto to the database.
     * It also constructs a ToolStatusDateModification entity based on the newly created Tool and saves it to the database.
     * Finally, it returns the response DTO corresponding to the newly created Tool.
     *
     * @param toolRequestDto The ToolRequestDto containing the data for the new Tool.
     * @return The response DTO representing the newly created Tool.
     */
    ToolResponseDto create(ToolRequestDto toolRequestDto);

    /**
     * Updates an existing Tool.
     *
     * @param id             The ID of the Tool to update.
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

    /**
     * Submits a Tool for precision check based on the provided ID.
     * <p>
     * This method retrieves the Tool entity from the database based on the provided ID.
     * If the Tool is not found, it throws a ResourceNotFoundException.
     * It then changes the status of the Tool to "UNDER_PRECISION_CHECK" and updates it in the database.
     * After updating the Tool status, it creates a new ToolStatusDateModification entity and saves it to the database.
     * Finally, it returns the response DTO corresponding to the updated Tool.
     *
     * @param id The ID of the Tool to submit for precision check.
     * @return The response DTO representing the Tool after being submitted for precision check.
     * @throws ResourceNotFoundException if the Tool with the provided ID is not found.
     */
    ToolResponseDto submitForPrecisionCheck(Long id);
}
