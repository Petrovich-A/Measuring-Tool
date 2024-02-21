/**
 * A mapper interface to convert between Tool entities and Tool DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.request.ToolRequestDto;
import by.petrovich.tool.dto.request.ToolStatusRequestDto;
import by.petrovich.tool.dto.request.ToolTypeRequestDto;
import by.petrovich.tool.dto.response.ToolResponseDto;
import by.petrovich.tool.model.StorageRoom;
import by.petrovich.tool.model.Tool;
import by.petrovich.tool.model.ToolIssuance;
import by.petrovich.tool.model.ToolStatus;
import by.petrovich.tool.model.ToolType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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
     * <p>
     * This method converts a Tool entity into a ToolResponseDto object. It maps the fields of the Tool entity
     * to their corresponding fields in the ToolResponseDto, including toolType, toolStatus, toolIssuance, and storageRoom.
     * The toolType, toolStatus, toolIssuance, and storageRoom fields are mapped to their respective DTOs (toolTypeResponseDto,
     * toolStatusResponseDto, toolIssuanceResponseDto, and storageRoomResponseDto) to ensure that the response DTO
     * contains all relevant information about the tool.
     * </p>
     *
     * @param tool The Tool entity to map.
     * @return The corresponding ToolResponseDto.
     */
    @Mapping(source = "toolType", target = "toolTypeResponseDto")
    @Mapping(source = "toolStatus", target = "toolStatusResponseDto")
    @Mapping(source = "toolIssuance", target = "toolIssuanceResponseDto")
    @Mapping(source = "storageRoom", target = "storageRoomResponseDto")
    ToolResponseDto toResponseDto(Tool tool);

    /**
     * Maps a ToolRequestDto to a Tool entity.
     * Sets the corresponding IDs from the DTOs to the entity.
     * Sets the createdAt and updatedAt fields to the current LocalDateTime.
     *
     * @param toolRequestDto The ToolRequestDto to map.
     * @return The corresponding Tool entity.
     */
    @Mapping(source = "toolTypeRequestDto.id", target = "toolType.id")
    @Mapping(source = "toolStatusRequestDto.id", target = "toolStatus.id")
    @Mapping(source = "toolIssuanceRequestDto.id", target = "toolIssuance.id")
    @Mapping(source = "storageRoomRequestDto.id", target = "storageRoom.id")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Tool toEntity(ToolRequestDto toolRequestDto);

    /**
     * Maps a ToolRequestDto to update an existing Tool entity.
     * Ignores assigning a value to the id field.
     * Sets the corresponding entity fields with the updated information.
     * Sets the updatedAt field to the current LocalDateTime.
     *
     * @param toolRequestDto The ToolRequestDto containing updated information.
     * @param tool           The Tool entity to update.
     * @return The updated Tool entity.
     */
    @Mapping(source = "toolTypeRequestDto", target = "toolType", qualifiedByName = "mapToolTypeDtoToToolType")
    @Mapping(source = "toolStatusRequestDto", target = "toolStatus", qualifiedByName = "mapToolStatusDtoToToolStatus")
    @Mapping(source = "toolIssuanceRequestDto", target = "toolIssuance", qualifiedByName = "mapToolIssuanceDtoToToolIssuance")
    @Mapping(source = "storageRoomRequestDto", target = "storageRoom", qualifiedByName = "mapStorageRoomDtoToStorageRoom")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Tool toEntityUpdate(ToolRequestDto toolRequestDto, @MappingTarget Tool tool);

    /**
     * Maps a ToolTypeRequestDto to a ToolType entity.
     *
     * @param toolTypeRequestDto The ToolTypeRequestDto containing information.
     * @return The corresponding ToolType entity.
     */
    @Named("mapToolTypeDtoToToolType")
    default ToolType mapToolTypeDtoToToolType(ToolTypeRequestDto toolTypeRequestDto) {
        ToolType toolType = new ToolType();
        toolType.setId(toolTypeRequestDto.getId());
        return toolType;
    }

    /**
     * Maps a ToolStatusRequestDto to a ToolStatus entity.
     *
     * @param toolStatusRequestDto The ToolStatusRequestDto containing information.
     * @return The corresponding ToolStatus entity.
     */
    @Named("mapToolStatusDtoToToolStatus")
    default ToolStatus mapToolStatusDtoToToolStatus(ToolStatusRequestDto toolStatusRequestDto) {
        ToolStatus toolStatus = new ToolStatus();
        toolStatus.setId(toolStatusRequestDto.getId());
        return toolStatus;
    }

    /**
     * Maps a ToolIssuanceRequestDto to a ToolIssuance entity.
     *
     * @param toolIssuanceRequestDto The ToolIssuanceRequestDto containing information.
     * @return The corresponding ToolIssuance entity.
     */
    @Named("mapToolIssuanceDtoToToolIssuance")
    default ToolIssuance mapToolIssuanceDtoToToolIssuance(ToolIssuanceRequestDto toolIssuanceRequestDto) {
        ToolIssuance toolIssuance = new ToolIssuance();
        if (toolIssuanceRequestDto == null) {
            return toolIssuance;
        } else {
            toolIssuance.setId(toolIssuanceRequestDto.getId());
        }
        return toolIssuance;
    }

    /**
     * Maps a StorageRoomRequestDto to a StorageRoom entity.
     *
     * @param storageRoomRequestDto The StorageRoomRequestDto containing information.
     * @return The corresponding StorageRoom entity.
     */
    @Named("mapStorageRoomDtoToStorageRoom")
    default StorageRoom mapStorageRoomDtoToStorageRoom(StorageRoomRequestDto storageRoomRequestDto) {
        StorageRoom storageRoom = new StorageRoom();
        storageRoom.setId(storageRoomRequestDto.getId());
        return storageRoom;
    }

}
