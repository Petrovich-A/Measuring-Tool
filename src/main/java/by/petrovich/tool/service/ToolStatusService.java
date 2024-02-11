/**
 * A service interface for ToolStatus operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on ToolStatus entities.
 */
public interface ToolStatusService {

    /**
     * Retrieves a ToolStatus by its ID.
     *
     * @param id The ID of the ToolStatus to retrieve.
     * @return The ToolStatusResponseDto corresponding to the given ID.
     */
    ToolStatusResponseDto find(Long id);

    /**
     * Retrieves all ToolStatuses.
     *
     * @return A list of all ToolStatusResponseDto objects.
     */
    List<ToolStatusResponseDto> findAll();

    /**
     * Creates a new ToolStatus.
     *
     * @param toolStatusRequestDto The ToolStatusRequestDto containing the data for the new ToolStatus.
     * @return The ToolStatusResponseDto representing the newly created ToolStatus.
     */
    ToolStatusResponseDto create(ToolStatusRequestDto toolStatusRequestDto);

    /**
     * Updates an existing ToolStatus.
     *
     * @param id The ID of the ToolStatus to update.
     * @param toolStatusRequestDto The ToolStatusRequestDto containing the updated data.
     * @return The ToolStatusResponseDto representing the updated ToolStatus.
     */
    ToolStatusResponseDto update(Long id, ToolStatusRequestDto toolStatusRequestDto);

    /**
     * Deletes a ToolStatus by its ID.
     *
     * @param id The ID of the ToolStatus to delete.
     */
    void delete(Long id);
}
