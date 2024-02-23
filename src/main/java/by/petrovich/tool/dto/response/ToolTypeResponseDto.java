package by.petrovich.tool.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolTypeResponseDto {
    private Long id;

    private String name;

}
