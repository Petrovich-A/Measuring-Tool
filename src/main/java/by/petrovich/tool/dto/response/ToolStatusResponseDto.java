package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
public class ToolStatusResponseDto {
    private Long id;

    private String name;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime start;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime finish;

}
