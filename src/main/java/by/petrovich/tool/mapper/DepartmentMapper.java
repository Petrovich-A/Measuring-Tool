/**
 * A mapper interface to convert between Department entities and Department DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.response.DepartmentResponseDto;
import by.petrovich.tool.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between Department entities and Department DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = DepartmentMapper.class
)
public interface DepartmentMapper {

    /**
     * Maps a Department entity to a DepartmentResponseDto.
     *
     * @param department The Department entity to map.
     * @return The corresponding DepartmentResponseDto.
     */
    DepartmentResponseDto toResponseDto(Department department);

    /**
     * Maps a DepartmentRequestDto to a Department entity.
     *
     * @param departmentRequestDto The DepartmentRequestDto to map.
     * @return The corresponding Department entity.
     */
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    Department toEntity(DepartmentRequestDto departmentRequestDto);

    /**
     * Maps a DepartmentRequestDto to update an existing Department entity.
     *
     * @param departmentRequestDto The DepartmentRequestDto containing updated information.
     * @param department The Department entity to update.
     * @return The updated Department entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    Department toEntityUpdate(DepartmentRequestDto departmentRequestDto, @MappingTarget Department department);
}
