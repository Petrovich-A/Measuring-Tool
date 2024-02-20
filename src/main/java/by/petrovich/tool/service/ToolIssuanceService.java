/**
 * Service interface for managing tool issuances.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.response.ToolIssuanceResponseDto;

import java.util.List;

public interface ToolIssuanceService {

    /**
     * Retrieves a tool issuance by its ID.
     *
     * @param id the ID of the tool issuance to retrieve
     * @return the tool issuance with the specified ID
     */
    ToolIssuanceResponseDto find(Long id);

    /**
     * Retrieves all tool issuances.
     *
     * @return a list of all tool issuances
     */
    List<ToolIssuanceResponseDto> findAll();

    /**
     * Creates a new tool issuance.
     *
     * @param toolIssuanceRequestDto the DTO containing information for the new tool issuance
     * @return the created tool issuance
     */
    ToolIssuanceResponseDto create(ToolIssuanceRequestDto toolIssuanceRequestDto);

    /**
     * Updates an existing tool issuance.
     *
     * @param id                     the ID of the tool issuance to update
     * @param toolIssuanceRequestDto the DTO containing updated information for the tool issuance
     * @return the updated tool issuance
     */
    ToolIssuanceResponseDto update(Long id, ToolIssuanceRequestDto toolIssuanceRequestDto);

    /**
     * Deletes a tool issuance by its ID.
     *
     * @param id the ID of the tool issuance to delete
     */
    void delete(Long id);
}
