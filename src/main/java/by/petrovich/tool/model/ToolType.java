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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"tools"})
@ToString(exclude = {"tools"})
@Entity
public class ToolType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_type_seq")
    @SequenceGenerator(name = "tool_type_seq", sequenceName = "tool_type_id_seq", allocationSize = 1)
    @Column(columnDefinition = "bigint")
    private Long id;

    @Size(min = 5, max = 20)
    @Column(length = 20, nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "toolType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tool> tools;

}
