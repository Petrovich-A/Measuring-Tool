package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "DTO for creating or updating a department")
public class DepartmentRequestDto {

    @Schema(description = "Unique identifier of the department")
    private Long id;

    @Schema(description = "Name of the department", example = "CNC Machine Shop", minLength = 3, maxLength = 50)
    private String name;

    @Schema(description = "Code of the department", example = "050", minLength = 3, maxLength = 4)
    private String code;

    @Schema(description = "Timestamp when the department was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the department was last updated")
    private LocalDateTime updatedAt;
}
