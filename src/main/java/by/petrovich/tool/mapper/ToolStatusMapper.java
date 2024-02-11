/**
 * A mapper interface to convert between ToolStatus entities and ToolStatus DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.response.ToolStatusResponseDto;
import by.petrovich.tool.model.ToolStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between ToolStatus entities and ToolStatus DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = ToolStatusMapper.class
)
public interface ToolStatusMapper {

    /**
     * Maps a ToolStatus entity to a ToolStatusResponseDto.
     *
     * @param toolStatus The ToolStatus entity to map.
     * @return The corresponding ToolStatusResponseDto.
     */
    ToolStatusResponseDto toResponseDto(ToolStatus toolStatus);

    /**
     * Maps a ToolStatusRequestDto to a ToolStatus entity.
     *
     * @param toolStatusRequestDto The ToolStatusRequestDto to map.
     * @return The corresponding ToolStatus entity.
     */
    @Mapping(target = "start", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "finish", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    ToolStatus toEntity(ToolStatusRequestDto toolStatusRequestDto);


    /**
     * Maps a ToolStatusRequestDto to update an existing ToolStatus entity.
     *
     * @param toolStatusRequestDto The ToolStatusRequestDto containing updated information.
     * @param toolStatus The ToolStatus entity to update.
     * @return The updated ToolStatus entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "start", ignore = true)
    @Mapping(target = "finish", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    ToolStatus toEntityUpdate(ToolStatusRequestDto toolStatusRequestDto, @MappingTarget ToolStatus toolStatus);
}
