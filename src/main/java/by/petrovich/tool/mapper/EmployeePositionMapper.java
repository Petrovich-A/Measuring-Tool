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

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeePositionMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    EmployeePosition toEntity(EmployeePositionRequestDto employeePositionRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now().withNano(0))")
    EmployeePosition toEntityUpdate(EmployeePositionRequestDto employeePositionRequestDto, @MappingTarget EmployeePosition employeePosition);

    EmployeePositionResponseDto toResponseDto(EmployeePosition employeePosition);

}
