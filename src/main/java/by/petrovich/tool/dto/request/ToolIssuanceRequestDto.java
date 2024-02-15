package by.petrovich.tool.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
public class ToolIssuanceRequestDto {

    @Schema(description = "Unique identifier for the tool issuance")
    private Long id;

    @Schema(description = "Date and time when the tool issuance was created")
    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the tool issuance was last updated")
    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime updatedAt;

    @Schema(description = "Employee who distributed the tool")
    private EmployeeRequestDto distributingByEmployee;

    @Schema(description = "Employee who received the tool")
    private EmployeeRequestDto receivingByEmployee;

    @Schema(description = "Storage room associated with the tool issuance")
    private StorageRoomRequestDto storageRoomRequestDto;
}
