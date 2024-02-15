package by.petrovich.tool.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolStatusRequestDto {
    @Schema(description = "Unique identifier for the tool status", example = "1")
    private Long id;

    @Schema(description = "Name of the tool status", example = "In Use", minLength = 5, maxLength = 20)
    private String name;

    @Schema(description = "Start date and time of the tool status")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;

    @Schema(description = "Finish date and time of the tool status")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finish;
}
