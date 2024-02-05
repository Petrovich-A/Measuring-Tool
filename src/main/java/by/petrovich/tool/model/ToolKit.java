package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToolKit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_kit_seq")
    @SequenceGenerator(name = "tool_kit_seq", sequenceName = "tool_kit_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "toolKit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tool> tools;

    @ManyToOne
    @JoinColumn(name = "distributing_by_employee_id", nullable = false, columnDefinition = "bigint")
    private Employee distributingByEmployee;

    @ManyToOne
    @JoinColumn(name = "receiving_by_employee_id", nullable = false, columnDefinition = "bigint")
    private Employee receivingByEmployee;

}
