package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.request.EmployeePositionResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.EmployeePositionMapper;
import by.petrovich.tool.model.EmployeePosition;
import by.petrovich.tool.repository.EmployeePositionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionServiceImplTest {
    @Mock
    private EmployeePositionMapper employeePositionMapper;

    @Mock
    private EmployeePositionRepository employeePositionRepository;

    @InjectMocks
    private EmployeePositionServiceImpl employeePositionService;

    @DisplayName("test findAll method")
    @Test
    void whenFindAll_thenReturnListOfEmployeePositionResponseDto() {
        long id1 = 1L;
        String position1 = "technologist";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        EmployeePosition employeePosition1 = createEmployeePosition(id1, position1, createdAt, updatedAt);
        long id2 = 2L;
        String position2 = "warehouseman";
        EmployeePosition employeePosition2 = createEmployeePosition(id2, position2, createdAt, updatedAt);
        List<EmployeePosition> positions = Arrays.asList(employeePosition1, employeePosition2);

        EmployeePositionResponseDto employeePositionResponseDto1 = createEmployeePositionResponseDto(id1, position1,
                createdAt, updatedAt);
        EmployeePositionResponseDto employeePositionResponseDto2 = createEmployeePositionResponseDto(id2, position2,
                createdAt, updatedAt);
        List<EmployeePositionResponseDto> expected = Arrays.asList(employeePositionResponseDto1, employeePositionResponseDto2);

        when(employeePositionMapper.toResponseDto(employeePosition1)).thenReturn(employeePositionResponseDto1);
        when(employeePositionMapper.toResponseDto(employeePosition2)).thenReturn(employeePositionResponseDto2);
        when(employeePositionRepository.findAll()).thenReturn(positions);

        List<EmployeePositionResponseDto> actual = employeePositionService.findAll();

        assertNotNull(actual);
        verify(employeePositionMapper, times(2)).toResponseDto(any());
        verify(employeePositionRepository, times(1)).findAll();
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("test find method")
    @Test
    void givenEmployeePositionId_whenFind_thenReturnEmployeePositionResponseDto() {
        Long id = 1L;
        String position = "technologist";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        EmployeePosition employeePosition = createEmployeePosition(id, position, createdAt, updatedAt);
        Optional<EmployeePosition> employeePositionOptional = ofNullable(employeePosition);
        EmployeePositionResponseDto employeePositionResponseDto = createEmployeePositionResponseDto(1L, position, createdAt, updatedAt);

        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(1L, position, createdAt, updatedAt);

        when(employeePositionMapper.toResponseDto(employeePosition)).thenReturn(employeePositionResponseDto);
        when(employeePositionRepository.findById(id)).thenReturn(employeePositionOptional);

        EmployeePositionResponseDto actual = employeePositionService.find(id);

        assertNotNull(actual);
        verify(employeePositionMapper, times(1)).toResponseDto(employeePosition);
        verify(employeePositionRepository, times(1)).findById(id);
        assertEquals(expected, actual);
    }

    @DisplayName("test find method with throwing exception")
    @Test
    void givenInvalidEmployeePositionId_whenFind_thenThrowResourceNotFoundException() {
        Long id = 1L;

        when(employeePositionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.find(id));
        verify(employeePositionRepository, times(1)).findById(id);
        verify(employeePositionMapper, never()).toResponseDto(any());
    }

    @DisplayName("test create method")
    @Test
    void givenEmployeePositionIdAndEmployeePositionRequestDto_whenCreate_thenReturnEmployeePositionResponseDto() {
        Long id = 1L;
        String position = "CNC programmer";
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        EmployeePositionRequestDto employeePositionRequestDto = EmployeePositionRequestDto.builder()
                .position(position)
                .build();
        EmployeePosition employeePositionWithoutId = EmployeePosition.builder()
                .position(position)
                .createdAt(localDateTime)
                .updatedAt(localDateTime)
                .build();
        EmployeePosition employeePosition = createEmployeePosition(id, position, localDateTime, localDateTime);
        EmployeePositionResponseDto employeePositionResponseDto = createEmployeePositionResponseDto(id, position, localDateTime, localDateTime);

        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(id, position, localDateTime, localDateTime);

        when(employeePositionMapper.toEntity(employeePositionRequestDto)).thenReturn(employeePositionWithoutId);
        when(employeePositionRepository.save(employeePositionWithoutId)).thenReturn(employeePosition);
        when(employeePositionMapper.toResponseDto(employeePosition)).thenReturn(employeePositionResponseDto);

        EmployeePositionResponseDto actual = employeePositionService.create(employeePositionRequestDto);

        assertNotNull(actual);
        verify(employeePositionMapper, times(1)).toEntity(employeePositionRequestDto);
        verify(employeePositionRepository, times(1)).save(employeePositionWithoutId);
        verify(employeePositionMapper, times(1)).toResponseDto(employeePosition);
        assertEquals(expected, actual);
    }

    @DisplayName("test update method")
    @Test
    void givenEmployeePositionIdAndEmployeePositionRequestDto_whenUpdate_thenReturnEmployeePositionResponseDto() {
        Long id = 1L;
        String position = "CNC programmer";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        LocalDateTime currentUpdatedAt = LocalDateTime.now().withNano(0);

        EmployeePositionRequestDto employeePositionRequestDto = EmployeePositionRequestDto.builder()
                .id(id)
                .position(position)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
        Optional<EmployeePosition> employeePositionOptional = Optional.of(createEmployeePosition(id, position, createdAt, updatedAt));
        EmployeePosition employeePositionUpdated = createEmployeePosition(id, position, createdAt, currentUpdatedAt);
        EmployeePosition employeePositionSaved = createEmployeePosition(id, position, createdAt, currentUpdatedAt);
        EmployeePositionResponseDto employeePositionResponseDto = createEmployeePositionResponseDto(id, position, createdAt, currentUpdatedAt);
        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(id, position, createdAt, currentUpdatedAt);

        when(employeePositionRepository.findById(id)).thenReturn(employeePositionOptional);
        when(employeePositionMapper.toEntityUpdate(employeePositionRequestDto, employeePositionOptional.get()))
                .thenReturn(employeePositionUpdated);
        when(employeePositionRepository.saveAndFlush(employeePositionUpdated)).thenReturn(employeePositionSaved);
        when(employeePositionMapper.toResponseDto(employeePositionSaved)).thenReturn(employeePositionResponseDto);

        EmployeePositionResponseDto actual = employeePositionService.update(id, employeePositionRequestDto);

        assertNotNull(actual);
        verify(employeePositionRepository, times(1)).findById(id);
        verify(employeePositionMapper, times(1)).toEntityUpdate(employeePositionRequestDto, employeePositionOptional.get());
        verify(employeePositionRepository, times(1)).saveAndFlush(employeePositionUpdated);
        verify(employeePositionMapper, times(1)).toResponseDto(employeePositionSaved);
        assertEquals(expected, actual);
    }

    @DisplayName("test update method with throwing exception")
    @Test
    void givenInvalidEmployeePositionIdAndEmployeePositionRequestDto_whenUpdate_thenThrowResourceNotFoundException() {
        Long id = 1L;

        when(employeePositionRepository.findById(id)).thenReturn(Optional.empty());

        verify(employeePositionRepository, never()).saveAndFlush(any());
        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.find(id));
    }

    @DisplayName("test delete method")
    @Test
    void givenEmployeePositionId_whenDelete_shouldDeleteEmployeePosition() {
        Long id = 1L;
        EmployeePosition employeePosition = createEmployeePosition(1L, "technologist",
                LocalDateTime.now().withNano(0), LocalDateTime.now().withNano(0));

        when(employeePositionRepository.findById(id)).thenReturn(Optional.of(employeePosition));

        employeePositionService.delete(id);

        verify(employeePositionRepository, times(1)).findById(id);
        verify(employeePositionRepository, times(1)).deleteById(id);
    }

    @DisplayName("test delete method with throwing exception")
    @Test
    void givenInvalidEmployeePositionId_whenDelete_thanThrowResourceNotFoundException() {
        Long id = 1L;

        when(employeePositionRepository.findById(id)).thenReturn(Optional.empty());

        verify(employeePositionRepository, never()).deleteById(any());
        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.delete(id));
    }


    private EmployeePosition createEmployeePosition(long id, String position, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return EmployeePosition.builder()
                .id(id)
                .position(position)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    private EmployeePositionResponseDto createEmployeePositionResponseDto(long id, String position, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return EmployeePositionResponseDto.builder()
                .id(id)
                .position(position)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}