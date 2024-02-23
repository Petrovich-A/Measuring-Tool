package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolRequestDto {
    @Schema(description = "Unique identifier for the tool", example = "1")
    private Long id;

    @Schema(description = "Designation of the tool", example = "Hammer", minLength = 3, maxLength = 10)
    private String designation;

    @Schema(description = "Laboratory number of the tool", example = "55123", minLength = 3, maxLength = 15)
    private String laboratoryNumber;

    @Schema(description = "Measurable size range of the tool", example = "75-100", minLength = 2, maxLength = 15)
    private String measurableSizeRange;

    @Schema(description = "Date and time when the tool was created")
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the tool was last updated")
    private LocalDateTime updatedAt;

    @Schema(description = "Type of the tool")
    private ToolTypeRequestDto toolTypeRequestDto;

    @Schema(description = "Status of the tool")
    private ToolStatusRequestDto toolStatusRequestDto;

    @Schema(description = "Issuance where tools belong")
    private ToolIssuanceRequestDto toolIssuanceRequestDto;

    @Schema(description = "Storage room where tools belong")
    private StorageRoomRequestDto storageRoomRequestDto;
}
