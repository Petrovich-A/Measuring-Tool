package by.petrovich.tool.model;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StorageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_room_seq")
    @SequenceGenerator(name = "storage_room_seq", sequenceName = "storage_room_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "department_id", nullable = false, columnDefinition = "bigint")
    private Department department;

    @OneToMany(mappedBy = "storageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ToolIssuance> toolIssuances;

    @OneToMany(mappedBy = "storageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tool> tools;

}