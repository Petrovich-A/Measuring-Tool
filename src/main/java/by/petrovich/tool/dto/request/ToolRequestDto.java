package by.petrovich.tool.dto.request;

import by.petrovich.tool.model.StorageRoom;
import by.petrovich.tool.model.ToolIssuance;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.model.ToolType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ToolRequestDto {
    private Long id;

    private String designation;

    private String laboratoryNumber;

    private String measurableSizeRange;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private ToolType toolType;

    private ToolStatus toolStatus;

    private ToolIssuance toolIssuance;

    private StorageRoom storageRoom;

}
