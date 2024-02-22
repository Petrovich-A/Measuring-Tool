package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static by.petrovich.tool.util.Pattern.DATA_TIME_RESPONSE_PATTERN;

@Data
@Builder
public class ToolIssuanceResponseDto {
    private Long id;

    @JsonFormat(pattern = DATA_TIME_RESPONSE_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_RESPONSE_PATTERN)
    private LocalDateTime updatedAt;

    private List<ToolResponseDto> toolsResponseDto;

    private EmployeeResponseDto distributingByEmployeeResponseDto;

    private EmployeeResponseDto receivingByEmployeeResponseDto;

    private StorageRoomResponseDto storageRoomResponseDto;

}
