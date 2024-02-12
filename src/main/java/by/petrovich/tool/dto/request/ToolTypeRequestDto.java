package by.petrovich.tool.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToolTypeRequestDto {
    private Long id;

    private String name;

}
