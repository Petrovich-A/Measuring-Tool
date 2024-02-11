/**
 * A service interface for EmployeePosition operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on EmployeePosition entities.
 */
public interface EmployeePositionService {

    /**
     * Retrieves all EmployeePositions.
     *
     * @return A list of all EmployeePositionResponseDto objects.
     */
    List<EmployeePositionResponseDto> findAll();

    /**
     * Retrieves an EmployeePosition by its ID.
     *
     * @param id The ID of the EmployeePosition to retrieve.
     * @return The EmployeePositionResponseDto corresponding to the given ID.
     */
    EmployeePositionResponseDto find(Long id);

    /**
     * Creates a new EmployeePosition.
     *
     * @param employeePosition The EmployeePositionRequestDto containing the data for the new EmployeePosition.
     * @return The EmployeePositionResponseDto representing the newly created EmployeePosition.
     */
    EmployeePositionResponseDto create(EmployeePositionRequestDto employeePosition);

    /**
     * Updates an existing EmployeePosition.
     *
     * @param id                         The ID of the EmployeePosition to update.
     * @param employeePositionRequestDto The EmployeePositionRequestDto containing the updated data.
     * @return The EmployeePositionResponseDto representing the updated EmployeePosition.
     */
    EmployeePositionResponseDto update(Long id, EmployeePositionRequestDto employeePositionRequestDto);

    /**
     * Deletes an EmployeePosition by its ID.
     *
     * @param id The ID of the EmployeePosition to delete.
     */
    void delete(Long id);
}
