package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
public class ToolIssuanceResponseDto {
    private Long id;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime updatedAt;

    private EmployeeResponseDto distributingByEmployee;

    private EmployeeResponseDto receivingByEmployee;

    private StorageRoomResponseDto storageRoomResponseDto;

}
