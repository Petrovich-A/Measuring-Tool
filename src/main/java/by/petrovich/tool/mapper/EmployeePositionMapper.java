/**
 * A mapper interface to convert between EmployeePosition entities and EmployeePosition DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.model.EmployeePosition;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between EmployeePosition entities and EmployeePosition DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface EmployeePositionMapper {

    /**
     * Maps an EmployeePositionRequestDto to an EmployeePosition entity.
     * Sets the createdAt field with the current LocalDateTime expression.
     * Sets the updatedAt field with the current LocalDateTime expression.
     *
     * @param employeePositionRequestDto The EmployeePositionRequestDto to map.
     * @return The corresponding EmployeePosition entity.
     */
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    EmployeePosition toEntity(EmployeePositionRequestDto employeePositionRequestDto);

    /**
     * Maps an EmployeePositionRequestDto to update an existing EmployeePosition entity.
     * Ignores null values during mapping.
     * Ignores assigning a value to the createdAt field.
     * Sets the updatedAt field with the current LocalDateTime expression.
     *
     * @param employeePositionRequestDto The EmployeePositionRequestDto containing updated information.
     * @param employeePosition The EmployeePosition entity to update.
     * @return The updated EmployeePosition entity.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    EmployeePosition toEntityUpdate(EmployeePositionRequestDto employeePositionRequestDto, @MappingTarget EmployeePosition employeePosition);

    /**
     * Maps an EmployeePosition entity to an EmployeePositionResponseDto.
     *
     * @param employeePosition The EmployeePosition entity to map.
     * @return The corresponding EmployeePositionResponseDto.
     */
    EmployeePositionResponseDto toResponseDto(EmployeePosition employeePosition);

}
