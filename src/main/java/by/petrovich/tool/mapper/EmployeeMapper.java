/**
 * A mapper interface to convert between Employee entities and Employee DTOs.
 * This mapper utilizes MapStruct library for automatic mapping generation.
 */
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.DepartmentRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.model.Department;
import by.petrovich.tool.model.Employee;
import by.petrovich.tool.model.EmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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
    @Mapping(source = "employeePosition", target = "employeePositionResponseDto")
    @Mapping(source = "department", target = "departmentResponseDto")
    EmployeeResponseDto toResponseDto(Employee employee);

    /**
     * Maps an EmployeeRequestDto to an Employee entity.
     * Sets the createdAt field with the current LocalDateTime expression.
     * Sets the updatedAt field with the current LocalDateTime expression.
     * Maps the employeePositionRequestDto.id to employeePosition.id.
     * Maps the departmentRequestDto.id to department.id.
     *
     * @param employeeRequestDto The EmployeeRequestDto to map.
     * @return The corresponding Employee entity.
     */
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "employeePositionRequestDto.id", target = "employeePosition.id")
    @Mapping(source = "departmentRequestDto.id", target = "department.id")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

    /**
     * Maps an EmployeeRequestDto to update an existing Employee entity.
     * Ignores assigning a value to the id field.
     * Ignores assigning a value to the createdAt field.
     * Sets the updatedAt field with the current LocalDateTime expression.
     * Maps the departmentRequestDto to the department entity using mapDepartmentDtoToDepartment.
     * Maps the employeePositionRequestDto to the employeePosition entity using mapEmployeePositionDtoToEmployeePosition.
     *
     * @param employeeRequestDto The EmployeeRequestDto containing updated information.
     * @param employee           The Employee entity to update.
     * @return The updated Employee entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "department", source = "departmentRequestDto", qualifiedByName = "mapDepartmentDtoToDepartment")
    @Mapping(target = "employeePosition", source = "employeePositionRequestDto", qualifiedByName = "mapEmployeePositionDtoToEmployeePosition")
    Employee toEntityUpdate(EmployeeRequestDto employeeRequestDto, @MappingTarget Employee employee);

    /**
     * Maps a DepartmentRequestDto to a Department entity.
     *
     * @param departmentRequestDto The DepartmentRequestDto to map.
     * @return The corresponding Department entity.
     */
    @Named("mapDepartmentDtoToDepartment")
    default Department mapDepartmentDtoToDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setId(departmentRequestDto.getId());
        return department;
    }

    /**
     * Maps an EmployeePositionRequestDto to an EmployeePosition entity.
     *
     * @param employeePositionRequestDto The EmployeePositionRequestDto to map.
     * @return The corresponding EmployeePosition entity.
     */
    @Named("mapEmployeePositionDtoToEmployeePosition")
    default EmployeePosition mapEmployeePositionDtoToEmployeePosition(EmployeePositionRequestDto employeePositionRequestDto) {
        EmployeePosition employeePosition = new EmployeePosition();
        employeePosition.setId(employeePositionRequestDto.getId());
        return employeePosition;
    }

}
