package by.petrovich.tool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"department", "toolIssuances", "tools"})
@ToString(exclude = {"department", "toolIssuances", "tools"})
@Entity
public class StorageRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storage_room_seq")
    @SequenceGenerator(name = "storage_room_seq", sequenceName = "storage_room_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @OneToOne
    @JoinColumn(name = "department_id", columnDefinition = "bigint", referencedColumnName = "id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "storageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ToolIssuance> toolIssuances;

    @JsonIgnore
    @OneToMany(mappedBy = "storageRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tool> tools;

}
