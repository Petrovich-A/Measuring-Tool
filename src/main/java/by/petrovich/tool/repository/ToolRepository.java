package by.petrovich.tool.repository;

import by.petrovich.tool.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    @Query("SELECT t FROM Tool t WHERE t.toolStatus.id = :statusId")
    List<Tool> findByToolStatusOrderByToolStatusUpdatedAtAsc(Long statusId);
}
