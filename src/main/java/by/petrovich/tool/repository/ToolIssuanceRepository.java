package by.petrovich.tool.repository;

import by.petrovich.tool.model.ToolIssuance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolIssuanceRepository extends JpaRepository<ToolIssuance, Long> {
}
