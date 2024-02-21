package by.petrovich.tool.repository;

import by.petrovich.tool.model.ToolStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for accessing ToolStatus entities.
 */
@Repository
public interface ToolStatusRepository extends JpaRepository<ToolStatus, Long> {
    /**
     * Retrieves a ToolStatus by its name, ignoring case.
     *
     * @param name The name of the ToolStatus to retrieve.
     * @return An Optional containing the ToolStatus corresponding to the given name, if found.
     */
    Optional<ToolStatus> findByNameIgnoreCase(String name);
}
