package by.petrovich.tool.repository;

import by.petrovich.tool.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Retrieves a list of employees whose surnames contain the specified substring, ignoring case.
     * <p>
     * This method searches the database for employees whose surnames contain the provided substring, regardless of the case of the characters.
     *
     * @param surname The substring to search for within employee surnames.
     * @return A list of employees whose surnames contain the specified substring, ignoring case. An empty list is returned if no matches are found.
     * @throws IllegalArgumentException if the provided surname is null.
     */
    List<Employee> findBySurnameContainingIgnoreCase(String surname);

}
