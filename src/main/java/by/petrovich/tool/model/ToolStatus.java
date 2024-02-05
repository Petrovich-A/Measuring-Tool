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
public class ToolStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_status_seq")
    @SequenceGenerator(name = "tool_status_seq", sequenceName = "tool_status_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Size(min = 5, max = 20)
    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime finish;

    @OneToMany(mappedBy = "toolStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tool> tools;

}
