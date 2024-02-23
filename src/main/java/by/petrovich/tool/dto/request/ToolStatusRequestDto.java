package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolStatusRequestDto {
    @Schema(description = "Unique identifier for the tool status", example = "1")
    private Long id;

    @Schema(description = "Name of the tool status", example = "In Use", minLength = 5, maxLength = 20)
    private String name;

}
