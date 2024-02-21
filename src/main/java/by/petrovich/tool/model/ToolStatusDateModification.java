package by.petrovich.tool.model;

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
import javax.validation.constraints.Future;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToolStatusDateModification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_status_date_modification_seq")
    @SequenceGenerator(name = "tool_status_date_modification_seq", sequenceName = "tool_status_date_modification_id_seq")
    @Column(columnDefinition = "bigint")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime start;

    @CreationTimestamp
    @Column(nullable = false)
    @Future
    private LocalDateTime finish;

    @ManyToOne
    @JoinColumn(name = "tool_status_id")
    private ToolStatus toolStatus;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private Tool tool;

}
