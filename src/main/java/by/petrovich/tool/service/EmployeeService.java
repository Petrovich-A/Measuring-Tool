/**
 * A service interface for Employee operations.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;

import java.util.List;

/**
 * An interface defining methods to perform CRUD operations on Employee entities.
 */
public interface EmployeeService {

    /**
     * Retrieves an Employee by its ID.
     *
     * @param id The ID of the Employee to retrieve.
     * @return The EmployeeResponseDto corresponding to the given ID.
     */
    EmployeeResponseDto find(Long id);

    /**
     * Retrieves all Employees.
     *
     * @return A list of all EmployeeResponseDto objects.
     */
    List<EmployeeResponseDto> findAll();

    /**
     * Creates a new Employee.
     *
     * @param employeeRequestDto The EmployeeRequestDto containing the data for the new Employee.
     * @return The EmployeeResponseDto representing the newly created Employee.
     */
    EmployeeResponseDto create(EmployeeRequestDto employeeRequestDto);

    /**
     * Updates an existing Employee.
     *
     * @param id The ID of the Employee to update.
     * @param employeeRequestDto The EmployeeRequestDto containing the updated data.
     * @return The EmployeeResponseDto representing the updated Employee.
     */
    EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto);

    /**
     * Deletes an Employee by its ID.
     *
     * @param id The ID of the Employee to delete.
     */
    void delete(Long id);
}
