package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DepartmentResponseDto {

    @Schema(description = "Unique identifier of the department", example = "1")
    private Long id;

    @Schema(description = "Name of the department", example = "CNC Machine Shop")
    private String name;

    @Schema(description = "Code of the department", example = "050")
    private String code;

    @Schema(description = "Date and time when the department was created",
            example = "2023-02-14T10:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the department was last updated",
            example = "2023-02-14T11:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

}
