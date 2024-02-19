package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tools", "distributingByEmployee", "receivingByEmployee", "storageRoom"})
@ToString(exclude = {"tools", "distributingByEmployee", "receivingByEmployee", "storageRoom"})
@Entity
public class ToolIssuance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_issuance_seq")
    @SequenceGenerator(name = "tool_issuance_seq", sequenceName = "tool_issuance_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "toolIssuance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tool> tools;

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
