package by.petrovich.tool.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pantry {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pantry_seq")
    @SequenceGenerator(name = "pantry_seq", sequenceName = "pantry_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @OneToOne
    @JoinColumn(name = "department_id", nullable = false, columnDefinition = "bigint")
    private Department department;

    @OneToMany(mappedBy = "pantry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees;

}
