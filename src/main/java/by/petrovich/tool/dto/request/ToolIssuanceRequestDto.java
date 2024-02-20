package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolIssuanceRequestDto {

    @Schema(description = "Unique identifier for the tool issuance")
    private Long id;

    @Schema(description = "Date and time when the tool issuance was created")
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the tool issuance was last updated")
    private LocalDateTime updatedAt;

    @Schema(description = "Employee who distributed the tool")
    private EmployeeRequestDto distributingByEmployeeRequestDto;

    @Schema(description = "Employee who received the tool")
    private EmployeeRequestDto receivingByEmployeeRequestDto;

    @Schema(description = "Storage room associated with the tool issuance")
    private StorageRoomRequestDto storageRoomRequestDto;
}
