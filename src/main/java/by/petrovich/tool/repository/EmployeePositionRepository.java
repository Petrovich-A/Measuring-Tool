package by.petrovich.tool.repository;

import by.petrovich.tool.model.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePositionRepository extends JpaRepository<EmployeePosition, Long> {
}
