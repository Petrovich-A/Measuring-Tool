package by.petrovich.tool.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, length = 20)
    private String position;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "employeePosition", cascade = CascadeType.ALL)
    private Set<Employee> employees;

}
