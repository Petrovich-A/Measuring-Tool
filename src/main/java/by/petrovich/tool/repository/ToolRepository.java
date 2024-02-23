package by.petrovich.tool.repository;

import by.petrovich.tool.model.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {
    List<Tool> findByToolStatusId(Long statusId);

    List<Tool> findByToolTypeIdAndStorageRoomId(Long toolTypeId, Long storageRoomId);

}
