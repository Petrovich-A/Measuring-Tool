/**
 * A service interface for Department operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.response.DepartmentResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on Department entities.
 */
public interface DepartmentService {

    /**
     * Retrieves a Department by its ID.
     *
     * @param id The ID of the Department to retrieve.
     * @return The DepartmentResponseDto corresponding to the given ID.
     */
    DepartmentResponseDto find(Long id);

    /**
     * Retrieves all Departments.
     *
     * @return A list of all DepartmentResponseDto objects.
     */
    List<DepartmentResponseDto> findAll();

    /**
     * Creates a new Department.
     *
     * @param departmentRequestDto The DepartmentRequestDto containing the data for the new Department.
     * @return The DepartmentResponseDto representing the newly created Department.
     */
    DepartmentResponseDto create(DepartmentRequestDto departmentRequestDto);

    /**
     * Updates an existing Department.
     *
     * @param id The ID of the Department to update.
     * @param departmentRequestDto The DepartmentRequestDto containing the updated data.
     * @return The DepartmentResponseDto representing the updated Department.
     */
    DepartmentResponseDto update(Long id, DepartmentRequestDto departmentRequestDto);

    /**
     * Deletes a Department by its ID.
     *
     * @param id The ID of the Department to delete.
     */
    void delete(Long id);
}
