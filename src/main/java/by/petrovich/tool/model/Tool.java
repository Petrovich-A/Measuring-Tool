package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

import static by.petrovich.tool.util.Pattern.DATA_TIME_PATTERN;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_seq")
    @SequenceGenerator(name = "tool_seq", sequenceName = "tool_id_seq", allocationSize = 50)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Size(min = 3, max = 10)
    @Column(length = 10, nullable = false, unique = true)
    private String designation;

    @Size(min = 3, max = 15)
    @Column(length = 15, nullable = false)
    private String laboratoryNumber;

    @Column(length = 15, nullable = false)
    @Size(min = 2, max = 15)
    private String measurableSizeRange;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = DATA_TIME_PATTERN)
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "tool_type_id", nullable = false, columnDefinition = "bigint")
    private ToolType toolType;

    @ManyToOne
    @JoinColumn(name = "tool_status_id", nullable = false, columnDefinition = "bigint")
    private ToolStatus toolStatus;

    @ManyToOne
    @JoinColumn(name = "tool_issuance_id", columnDefinition = "bigint")
    private ToolIssuance toolIssuance;

    @ManyToOne
    @JoinColumn(name = "storage_room_id", nullable = false, columnDefinition = "bigint")
    private StorageRoom storageRoom;

}
