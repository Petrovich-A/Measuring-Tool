/**
 * A mapper interface to convert between Tool entities and Tool DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.model.Tool;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between Tool entities and Tool DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = ToolMapper.class
)
public interface ToolMapper {

    /**
     * Maps a Tool entity to a ToolResponseDto.
     *
     * @param tool The Tool entity to map.
     * @return The corresponding ToolResponseDto.
     */
    ToolResponseDto toResponseDto(Tool tool);

    /**
     * Maps a ToolRequestDto to a Tool entity.
     *
     * @param toolRequestDto The ToolRequestDto to map.
     * @return The corresponding Tool entity.
     */
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(source = "toolType.id", target = "toolType.id")
    @Mapping(source = "toolStatus.id", target = "toolStatus.id")
    @Mapping(source = "toolIssuance.id", target = "toolIssuance.id")
    @Mapping(source = "storageRoom.id", target = "storageRoom.id")
    Tool toEntity(ToolRequestDto toolRequestDto);

    /**
     * Maps a ToolRequestDto to update an existing Tool entity.
     *
     * @param toolRequestDto The ToolRequestDto containing updated information.
     * @param tool The Tool entity to update.
     * @return The updated Tool entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(source = "toolStatus", target = "toolStatus")
    @Mapping(source = "toolIssuance", target = "toolIssuance")
    @Mapping(source = "storageRoom", target = "storageRoom")
    Tool toEntityUpdate(ToolRequestDto toolRequestDto, @MappingTarget Tool tool);
}
