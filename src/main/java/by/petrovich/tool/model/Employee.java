package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigserial")
    private Long id;

    @Size(min = 5, max = 5)
    @Column(length = 5, nullable = false, unique = true)
    private String personnelNumber;

    @Size(min = 3, max = 15)
    @Column(length = 15, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    @Size(min = 2, max = 15)
    private String surname;

    @Column(length = 15, nullable = false)
    @Size(min = 5, max = 15)
    private String patronymic;

    @Email
    @Column(length = 50, nullable = false)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "employee_position_id", nullable = false, columnDefinition = "bigserial")
    private EmployeePosition employeePosition;

}
