package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
public class ToolResponseDto {
    private Long id;

    private String designation;

    private String laboratoryNumber;

    private String measurableSizeRange;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime updatedAt;

    private ToolTypeResponseDto toolTypeResponseDto;

    private ToolStatusResponseDto toolStatusResponseDto;

    private ToolIssuanceResponseDto toolIssuanceResponseDto;

    private StorageRoomResponseDto storageRoomResponseDto;

}
