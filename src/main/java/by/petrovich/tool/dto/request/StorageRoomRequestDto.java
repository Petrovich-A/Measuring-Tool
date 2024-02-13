package by.petrovich.tool.dto.request;

import by.petrovich.tool.model.Department;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageRoomRequestDto {
    private Long id;

    private Department department;

}
