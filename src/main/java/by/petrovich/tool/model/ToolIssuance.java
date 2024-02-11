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
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ToolIssuance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_issuance_seq")
    @SequenceGenerator(name = "tool_issuance_seq", sequenceName = "tool_issuance_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "toolIssuance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tool> tools;

    @ManyToOne
    @JoinColumn(name = "distributing_by_employee_id", nullable = false, columnDefinition = "bigint")
    private Employee distributingByEmployee;

    @ManyToOne
    @JoinColumn(name = "receiving_by_employee_id", nullable = false, columnDefinition = "bigint")
    private Employee receivingByEmployee;

    @ManyToOne
    @JoinColumn(name = "storage_room_id", nullable = false, columnDefinition = "bigint")
    private StorageRoom storageRoom;

}