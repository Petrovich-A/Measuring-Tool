package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageRoomRequestDto {

    @Schema(description = "Unique identifier for the storage room")
    private Long id;

    @Schema(description = "Details of the department associated with the storage room")
    private DepartmentRequestDto departmentRequestDto;
}
