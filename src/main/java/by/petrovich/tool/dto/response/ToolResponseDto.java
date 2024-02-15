package by.petrovich.tool.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolResponseDto {
    private Long id;

    private String designation;

    private String laboratoryNumber;

    private String measurableSizeRange;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ToolTypeResponseDto toolTypeResponseDto;

    private ToolStatusResponseDto toolStatusResponseDto;

    private ToolIssuanceResponseDto toolIssuanceResponseDto;

    private StorageRoomResponseDto storageRoomResponseDto;

}
