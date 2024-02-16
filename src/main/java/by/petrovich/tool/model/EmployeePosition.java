package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_position_seq")
    @SequenceGenerator(name = "employee_position_seq", sequenceName = "employee_position_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, length = 20)
    private String position;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @DateTimeFormat(style = DATA_TIME_PATTERN)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(style = DATA_TIME_PATTERN)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "employeePosition", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Employee> employees;


}
