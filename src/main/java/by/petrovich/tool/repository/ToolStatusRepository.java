package by.petrovich.tool.repository;

import by.petrovich.tool.model.ToolStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolStatusRepository extends JpaRepository<ToolStatus, Long> {
}
