package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.model.EmployeePosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EmployeePositionMapper {
    @Mapping(target = "createdAt", expression = "java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now().withNano(0)))")
    @Mapping(target = "updatedAt", expression = "java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now().withNano(0)))")
    EmployeePosition toEntity(EmployeePositionRequestDto employeePositionRequestDto);

    EmployeePositionResponseDto toResponseDto(EmployeePosition employeePosition);


}
