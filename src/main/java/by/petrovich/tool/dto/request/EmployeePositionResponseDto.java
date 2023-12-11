package by.petrovich.tool.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmployeePositionResponseDto {
    private Long id;
    private String position;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
