/**
 * A service interface for performing CRUD operations on Employee entities.
 */
package by.petrovich.tool.service;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;

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
     * @throws ResourceNotFoundException if no employee is found with the specified ID.
     */
    EmployeeResponseDto find(Long id);

    /**
     * Retrieves a list of employee response DTOs based on the specified criteria.
     * <p>
     * This method searches for employees in the database whose surnames contain the provided substring, ignoring case.
     * It then maps the found employees to their corresponding response DTOs and returns the list of DTOs.
     * If no employees are found with the specified criteria, a ResourceNotFoundException is thrown.
     *
     * @param surname The substring to search for within employee surnames.
     * @return A list of employee response DTOs whose surnames contain the specified substring, ignoring case.
     * @throws ResourceNotFoundException if no employees are found with the specified surname.
     * @throws IllegalArgumentException  if the provided surname is null.
     */
    List<EmployeeResponseDto> findByCriteria(String surname);

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
     * @param id                 The ID of the Employee to update.
     * @param employeeRequestDto The EmployeeRequestDto containing the updated data.
     * @return The EmployeeResponseDto representing the updated Employee.
     * @throws ResourceNotFoundException if no employee is found with the specified ID.
     */
    EmployeeResponseDto update(Long id, EmployeeRequestDto employeeRequestDto);

    /**
     * Deletes an Employee by its ID.
     *
     * @param id The ID of the Employee to delete.
     * @throws ResourceNotFoundException if no employee is found with the specified ID.
     */
    void delete(Long id);
}
