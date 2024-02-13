/**
 * A mapper interface to convert between StorageRoom entities and StorageRoom DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.response.StorageRoomResponseDto;
import by.petrovich.tool.model.StorageRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between StorageRoom entities and StorageRoom DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = StorageRoomMapper.class
)
public interface StorageRoomMapper {

    /**
     * Maps a StorageRoom entity to a StorageRoomResponseDto.
     *
     * @param storageRoom The StorageRoom entity to map.
     * @return The corresponding StorageRoomResponseDto.
     */
    StorageRoomResponseDto toResponseDto(StorageRoom storageRoom);

    /**
     * Maps a StorageRoomRequestDto to a StorageRoom entity.
     *
     * @param storageRoomRequestDto The StorageRoomRequestDto to map.
     * @return The corresponding StorageRoom entity.
     */
    @Mapping(source = "department.id", target = "department.id")
    StorageRoom toEntity(StorageRoomRequestDto storageRoomRequestDto);


    /**
     * Maps a StorageRoomRequestDto to update an existing StorageRoom entity.
     *
     * @param storageRoomRequestDto The StorageRoomRequestDto containing updated information.
     * @param storageRoom The StorageRoom entity to update.
     * @return The updated StorageRoom entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "department", target = "department")
    StorageRoom toEntityUpdate(StorageRoomRequestDto storageRoomRequestDto, @MappingTarget StorageRoom storageRoom);
}
