package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE, uses = EmployeeMapper.class)
public interface EmployeeMapper {
    //    @Mapping(source = "employeePosition.position", target = "position")
    EmployeeResponseDto toResponseDto(Employee employee);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
//    @Mapping(source = "employeePositionId", target = "employeePosition.id")
    Employee toEntity(EmployeeRequestDto employeeRequestDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    Employee toEntityUpdate(EmployeeRequestDto employeeRequestDto, @MappingTarget Employee employee);

}
