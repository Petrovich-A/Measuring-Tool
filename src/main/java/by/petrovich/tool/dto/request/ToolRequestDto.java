package by.petrovich.tool.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolRequestDto {
    private Long id;

    private String designation;

    private String laboratoryNumber;

    private String measurableSizeRange;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ToolTypeRequestDto toolTypeRequestDto;

    private ToolStatusRequestDto toolStatusRequestDto;

    private ToolIssuanceRequestDto toolIssuanceRequestDto;

    private StorageRoomRequestDto storageRoomRequestDto;

}
