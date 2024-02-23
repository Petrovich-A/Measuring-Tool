package by.petrovich.tool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeRequestDto {
    private Long id;

    @Schema(description = "Personnel number of the employee", example = "42935", minLength = 5, maxLength = 5)
    private String personnelNumber;

    @Schema(description = "Name of the employee", example = "Roman", minLength = 3, maxLength = 15)
    private String name;

    @Schema(description = "Surname of the employee", example = "Romanov", minLength = 2, maxLength = 15)
    private String surname;

    @Schema(description = "Patronymic of the employee", example = "Romanovich", minLength = 5, maxLength = 15)
    private String patronymic;

    @Schema(description = "Email of the employee", example = "romanov@mail.com", maxLength = 50)
    private String email;

    @Schema(description = "Timestamp when the employee was created")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp when the employee was last updated")
    private LocalDateTime updatedAt;

    @Schema(description = "Position of the employee")
    private EmployeePositionRequestDto employeePositionRequestDto;

    @Schema(description = "Department of the employee")
    private DepartmentRequestDto departmentRequestDto;
}
