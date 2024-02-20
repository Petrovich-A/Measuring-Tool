package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "DTO for creating or updating an employee position")
public class EmployeePositionRequestDto {

    @Schema(description = "Unique identifier of the employee position", example = "1")
    private Long id;

    @Schema(description = "Name of the employee position", example = "Manager", maxLength = 20)
    private String position;

    @Schema(description = "Timestamp when the employee position was created", example = "2024-02-14T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the employee position was last updated", example = "2024-02-14T10:05:00")
    private LocalDateTime updatedAt;
}
