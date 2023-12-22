package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.EmployeePositionMapper;
import by.petrovich.tool.model.EmployeePosition;
import by.petrovich.tool.repository.EmployeePositionRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@RequiredArgsConstructor
public class EmployeePositionServiceImplTest {
    @Autowired
    @Spy
    private EmployeePositionMapper employeePositionMapper;

    private EmployeePositionRepository employeePositionRepository;

    private EmployeePositionServiceImpl employeePositionService;

    @BeforeEach
    void setUp() {
        employeePositionRepository = mock(EmployeePositionRepository.class);
        employeePositionService = new EmployeePositionServiceImpl(employeePositionRepository, employeePositionMapper);
    }

    @DisplayName("test findAll method")
    @Test
    void whenFindAll_thenReturnListOfEmployeePositionResponseDto() {
        long id1 = 1;
        String position1 = "technologist";
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 12, 15, 14, 35, 55);
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

        when(employeePositionRepository.findAll()).thenReturn(positions);

        List<EmployeePositionResponseDto> actual = employeePositionService.findAll();

        assertNotNull(actual);
        verify(employeePositionRepository, times(1)).findAll();
        verify(employeePositionMapper, times(2)).toResponseDto(any());
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }

    @DisplayName("test find method")
    @Test
    void givenEmployeePositionId_whenFind_thenReturnEmployeePositionResponseDto() {
        long id = 1;
        String position = "technologist";
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 12, 15, 14, 35, 55);
        EmployeePosition employeePosition = createEmployeePosition(id, position, createdAt, updatedAt);
        Optional<EmployeePosition> employeePositionOptional = ofNullable(employeePosition);

        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(1, position, createdAt, updatedAt);

        when(employeePositionRepository.findById(id)).thenReturn(employeePositionOptional);

        EmployeePositionResponseDto actual = employeePositionService.find(id);

        assertNotNull(actual);
        verify(employeePositionRepository, times(1)).findById(id);
        verify(employeePositionMapper, times(1)).toResponseDto(any());
        assertEquals(expected, actual);
    }

    @DisplayName("test find method with throwing exception")
    @Test
    void givenInvalidEmployeePositionId_whenFind_thenThrowResourceNotFoundException() {
        long id = 1;

        when(employeePositionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.find(id));
        verify(employeePositionRepository, times(1)).findById(id);
        verify(employeePositionMapper, never()).toResponseDto(any());
    }

    @DisplayName("test create method")
    @Test
    void givenEmployeePositionIdAndEmployeePositionRequestDto_whenCreate_thenReturnEmployeePositionResponseDto() {
        long id = 1;
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

        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(id, position, localDateTime, localDateTime);

        when(employeePositionRepository.save(employeePositionWithoutId)).thenReturn(employeePosition);

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
        long id = 1;
        String position = "CNC programmer";
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 12, 15, 14, 35, 55);
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
        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(id, position, createdAt, currentUpdatedAt);

        when(employeePositionRepository.findById(id)).thenReturn(employeePositionOptional);
        when(employeePositionRepository.saveAndFlush(employeePositionUpdated)).thenReturn(employeePositionSaved);

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
        long id = 1;

        when(employeePositionRepository.findById(id)).thenReturn(Optional.empty());

        verify(employeePositionRepository, never()).saveAndFlush(any());
        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.find(id));
    }

    @DisplayName("test delete method")
    @Test
    void givenEmployeePositionId_whenDelete_shouldDeleteEmployeePosition() {
        long id = 1;

        when(employeePositionRepository.existsById(id)).thenReturn(true);
        doNothing().when(employeePositionRepository).deleteById(isA(Long.class));

        employeePositionService.delete(id);

        verify(employeePositionRepository, times(1)).existsById(id);
        verify(employeePositionRepository, times(1)).deleteById(id);
    }

    @DisplayName("test delete method with throwing exception")
    @Test
    void givenInvalidEmployeePositionId_whenDelete_thanThrowResourceNotFoundException() {
        long id = 1;

        when(employeePositionRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> employeePositionService.delete(id));
        verify(employeePositionRepository, times(1)).existsById(id);
        verify(employeePositionRepository, never()).deleteById(any());
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