/**
 * A mapper interface to convert between Employee entities and Employee DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * An interface that defines mapping methods between Employee entities and Employee DTOs.
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = EmployeeMapper.class
)
public interface EmployeeMapper {

    /**
     * Maps an Employee entity to an EmployeeResponseDto.
     *
     * @param employee The Employee entity to map.
     * @return The corresponding EmployeeResponseDto.
     */
    @Mapping(source = "employeePosition", target = "employeePosition")
    @Mapping(source = "department", target = "department")
    EmployeeResponseDto toResponseDto(Employee employee);

    /**
     * Maps an EmployeeRequestDto to an Employee entity.
     *
     * @param employeeRequestDto The EmployeeRequestDto to map.
     * @return The corresponding Employee entity.
     */
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(source = "employeePosition.id", target = "employeePosition.id")
    @Mapping(source = "department.id", target = "department.id")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

    /**
     * Maps an EmployeeRequestDto to update an existing Employee entity.
     *
     * @param employeeRequestDto The EmployeeRequestDto containing updated information.
     * @param employee The Employee entity to update.
     * @return The updated Employee entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    Employee toEntityUpdate(EmployeeRequestDto employeeRequestDto, @MappingTarget Employee employee);
}
