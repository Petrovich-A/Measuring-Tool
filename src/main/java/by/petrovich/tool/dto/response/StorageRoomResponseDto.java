package by.petrovich.tool.dto.response;

import by.petrovich.tool.model.Department;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageRoomResponseDto {
    private Long id;

    private Department department;

}
