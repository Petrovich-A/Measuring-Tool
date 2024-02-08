package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", sequenceName = "employee_id_seq")
    @Column(columnDefinition = "bigint")
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
    @JoinColumn(name = "employee_position_id", nullable = false, columnDefinition = "bigint")
    private EmployeePosition employeePosition;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, columnDefinition = "bigint")
    private Department department;

    @OneToMany(mappedBy = "receivingByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ToolIssuance> receivingToolIssuances;

    @OneToMany(mappedBy = "distributingByEmployee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ToolIssuance> distributingToolIssuances;

}
