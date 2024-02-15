package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeResponseDto {
    private Long id;

    private String personnelNumber;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private EmployeePositionResponseDto employeePositionResponseDto;

    private DepartmentResponseDto departmentResponseDto;

}
