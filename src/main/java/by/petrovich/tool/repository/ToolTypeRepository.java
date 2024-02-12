package by.petrovich.tool.repository;

import by.petrovich.tool.model.ToolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolTypeRepository extends JpaRepository<ToolType, Long> {
}
