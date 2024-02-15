package by.petrovich.tool.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolIssuanceRequestDto {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private EmployeeRequestDto distributingByEmployee;

    private EmployeeRequestDto receivingByEmployee;

    private StorageRoomRequestDto storageRoomRequestDto;

}
