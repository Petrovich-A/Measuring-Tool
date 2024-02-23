package by.petrovich.tool.repository;

import by.petrovich.tool.model.StorageRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRoomRepository extends JpaRepository<StorageRoom, Long> {
}
