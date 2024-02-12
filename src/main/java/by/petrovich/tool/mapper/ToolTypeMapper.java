/**
 * A mapper interface to convert between ToolType entities and ToolType DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolTypeResponseDto;
import by.petrovich.tool.model.ToolType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between ToolType entities and ToolType DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = ToolTypeMapper.class
)
public interface ToolTypeMapper {

    /**
     * Maps a ToolType entity to a ToolTypeResponseDto.
     *
     * @param toolType The ToolType entity to map.
     * @return The corresponding ToolTypeResponseDto.
     */
    ToolTypeResponseDto toResponseDto(ToolType toolType);

    /**
     * Maps a ToolTypeRequestDto to a ToolType entity.
     *
     * @param toolTypeRequestDto The ToolTypeRequestDto to map.
     * @return The corresponding ToolType entity.
     */
    ToolType toEntity(ToolTypeRequestDto toolTypeRequestDto);


    /**
     * Maps a ToolTypeRequestDto to update an existing ToolType entity.
     *
     * @param toolTypeRequestDto The ToolTypeRequestDto containing updated information.
     * @param toolType The ToolType entity to update.
     * @return The updated ToolType entity.
     */
    @Mapping(target = "id", ignore = true)
    ToolType toEntityUpdate(ToolTypeRequestDto toolTypeRequestDto, @MappingTarget ToolType toolType);
}
