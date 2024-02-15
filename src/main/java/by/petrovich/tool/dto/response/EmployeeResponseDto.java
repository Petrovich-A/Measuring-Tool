package by.petrovich.tool.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
public class EmployeeResponseDto {
    private Long id;

    private String personnelNumber;

    private String name;

    private String surname;

    private String patronymic;

    private String email;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    private LocalDateTime updatedAt;

    private EmployeePositionResponseDto employeePositionResponseDto;

    private DepartmentResponseDto departmentResponseDto;

}
