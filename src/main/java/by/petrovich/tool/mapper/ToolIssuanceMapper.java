
package by.petrovich.tool.mapper;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.request.StorageRoomRequestDto;
import by.petrovich.tool.dto.request.ToolIssuanceRequestDto;
import by.petrovich.tool.dto.response.ToolIssuanceResponseDto;
import by.petrovich.tool.model.Employee;
import by.petrovich.tool.model.StorageRoom;
import by.petrovich.tool.model.ToolIssuance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = ToolIssuanceMapper.class
)
public interface ToolIssuanceMapper {

    @Mapping(source = "tools", target = "toolsResponseDto")
    @Mapping(source = "distributingByEmployee", target = "distributingByEmployeeResponseDto")
    @Mapping(source = "receivingByEmployee", target = "receivingByEmployeeResponseDto")
    @Mapping(source = "storageRoom", target = "storageRoomResponseDto")
    ToolIssuanceResponseDto toResponseDto(ToolIssuance toolIssuance);

    @Mapping(source = "distributingByEmployeeRequestDto.id", target = "distributingByEmployee.id")
    @Mapping(source = "receivingByEmployeeRequestDto.id", target = "receivingByEmployee.id")
    @Mapping(source = "storageRoomRequestDto.id", target = "storageRoom.id")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    ToolIssuance toEntity(ToolIssuanceRequestDto toolIssuanceRequestDto);

    @Mapping(source = "distributingByEmployeeRequestDto", target = "distributingByEmployee", qualifiedByName = "mapDistributingByEmployeeDtoToDistributingByEmployee")
    @Mapping(source = "receivingByEmployeeRequestDto", target = "receivingByEmployee", qualifiedByName = "mapReceivingByEmployeeDtoToReceivingByEmployee")
    @Mapping(source = "storageRoomRequestDto", target = "storageRoom", qualifiedByName = "mapStorageRoomDtoToStorageRoom")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    ToolIssuance toEntityUpdate(ToolIssuanceRequestDto toolIssuanceRequestDto, @MappingTarget ToolIssuance toolIssuance);


    @Named("mapDistributingByEmployeeDtoToDistributingByEmployee")
    default Employee mapDistributingByEmployeeDtoToDistributingByEmployee(EmployeeRequestDto distributingByEmployee) {
        Employee employee = new Employee();
        employee.setId(distributingByEmployee.getId());
        return employee;
    }


    @Named("mapReceivingByEmployeeDtoToReceivingByEmployee")
    default Employee mapReceivingByEmployeeDtoToReceivingByEmployee(EmployeeRequestDto receivingByEmployee) {
        Employee employee = new Employee();
        employee.setId(receivingByEmployee.getId());
        return employee;
    }

    @Named("mapStorageRoomDtoToStorageRoom")
    default StorageRoom mapStorageRoomDtoToStorageRoom(StorageRoomRequestDto storageRoomRequestDto) {
        StorageRoom storageRoom = new StorageRoom();
        storageRoom.setId(storageRoomRequestDto.getId());
        return storageRoom;
    }

}
