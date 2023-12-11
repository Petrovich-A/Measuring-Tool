package by.petrovich.tool.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeePositionRequestDto {
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String position;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime updatedAt;
}
