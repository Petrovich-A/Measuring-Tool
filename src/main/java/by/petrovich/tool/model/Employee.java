package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Size(min = 5, max = 5)
    @Column(length = 5)
    private String personnelNumber;
    @Size(min = 3, max = 15)
    @Column(length = 15)
    private String name;
    @Column(length = 15)
    @Size(min = 2, max = 15)
    private String surname;
    @Column(length = 15)
    @Size(min = 5, max = 15)
    private String patronymic;
    @Email
    @Column(length = 50)
    private String email;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "employee_position_id", nullable = false)
    private EmployeePosition employeePosition;

}
