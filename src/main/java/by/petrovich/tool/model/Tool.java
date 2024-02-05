package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_seq")
    @SequenceGenerator(name = "tool_seq", sequenceName = "tool_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Size(min = 5, max = 5)
    @Column(length = 5, nullable = false, unique = true)
    private String designation;

    @Size(min = 3, max = 15)
    @Column(length = 15, nullable = false)
    private String laboratoryNumber;

    @Column(length = 15, nullable = false)
    @Size(min = 2, max = 15)
    private String measurableSizeRange;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "tool_type_id", nullable = false, columnDefinition = "bigint")
    private ToolType toolType;

    @ManyToOne
    @JoinColumn(name = "tool_status_id", nullable = false, columnDefinition = "bigint")
    private ToolStatus toolStatus;

    @ManyToOne
    @JoinColumn(name = "tool_kit_id", nullable = false, columnDefinition = "bigint")
    private ToolKit toolKit;

}
