package by.petrovich.tool.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageRoomRequestDto {
    private Long id;

    private DepartmentRequestDto departmentRequestDto;

}
