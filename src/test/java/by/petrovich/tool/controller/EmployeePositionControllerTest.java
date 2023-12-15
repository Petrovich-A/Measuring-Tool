package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeePositionRequestDto;
import by.petrovich.tool.dto.response.EmployeePositionResponseDto;
import by.petrovich.tool.service.impl.EmployeePositionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeePositionControllerTest {
    @InjectMocks
    EmployeePositionController employeePositionController;

    @Mock
    private EmployeePositionServiceImpl employeePositionService;

    @DisplayName("Test find Success")
    @Test
    void find() {
        long id = 1;
        String position = "technologist";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        EmployeePositionResponseDto employeePositionResponseDto = createEmployeePositionResponseDto(id, position, createdAt, updatedAt);
        EmployeePositionResponseDto expected = createEmployeePositionResponseDto(id, position, createdAt, updatedAt);

        when(employeePositionService.find(id)).thenReturn(employeePositionResponseDto);

        ResponseEntity<EmployeePositionResponseDto> responseEntity = employeePositionController.find(id);
        EmployeePositionResponseDto actualBody = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualBody);
        verify(employeePositionService, times(1)).find(id);
        assertEquals(expected.getId(), actualBody.getId());
        assertEquals(expected.getPosition(), actualBody.getPosition());
        assertEquals(expected.getCreatedAt(), actualBody.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actualBody.getUpdatedAt());
    }

    private EmployeePositionResponseDto createEmployeePositionResponseDto(long id, String position, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return EmployeePositionResponseDto.builder()
                .id(id)
                .position(position)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    @DisplayName("Test findAll Success")
    @Test
    void findAll() {
        long id1 = 1;
        String position1 = "technologist";
        long id2 = 2L;
        String position2 = "manager";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        List<EmployeePositionResponseDto> expectedList = Arrays.asList(
                createEmployeePositionResponseDto(id1, position1, createdAt, updatedAt),
                createEmployeePositionResponseDto(id2, position2, createdAt, updatedAt)
        );

        when(employeePositionService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<EmployeePositionResponseDto>> responseEntity = employeePositionController.findAll();
        List<EmployeePositionResponseDto> actualBodyList = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(actualBodyList);
        assertEquals(expectedList.size(), actualBodyList.size());
        verify(employeePositionService, times(1)).findAll();
        IntStream.range(0, expectedList.size()).forEach(i -> {
            EmployeePositionResponseDto expectedDto = expectedList.get(i);
            EmployeePositionResponseDto actualDto = actualBodyList.get(i);
            assertEquals(expectedDto.getId(), actualDto.getId());
            assertEquals(expectedDto.getPosition(), actualDto.getPosition());
            assertEquals(expectedDto.getCreatedAt(), actualDto.getCreatedAt());
            assertEquals(expectedDto.getUpdatedAt(), actualDto.getUpdatedAt());
        });
    }

    @DisplayName("Test create Success")
    @Test
    void create() {
        EmployeePositionRequestDto employeePositionRequestDto = EmployeePositionRequestDto.builder()
                .position("technologist")
                .build();

        EmployeePositionResponseDto employeePositionResponseDto = EmployeePositionResponseDto.builder()
                .id(1L)
                .position("technologist")
                .build();

        when(employeePositionService.create(employeePositionRequestDto)).thenReturn(employeePositionResponseDto);

        ResponseEntity<EmployeePositionResponseDto> responseEntity = employeePositionController.create(employeePositionRequestDto);
        EmployeePositionResponseDto actualBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(actualBody);
        verify(employeePositionService, times(1)).create(employeePositionRequestDto);
        assertEquals(employeePositionResponseDto.getId(), actualBody.getId());
        assertEquals(employeePositionResponseDto.getPosition(), actualBody.getPosition());
    }

    @DisplayName("Test update Success")
    @Test
    void update() {
        long id = 1;
        EmployeePositionRequestDto requestDto = EmployeePositionRequestDto.builder()
                .position("technologist")
                .build();
        EmployeePositionResponseDto responseDto = EmployeePositionResponseDto.builder()
                .id(id)
                .position("technologist")
                .build();

        when(employeePositionService.update(id, requestDto)).thenReturn(responseDto);

        ResponseEntity<EmployeePositionResponseDto> responseEntity = employeePositionController.update(id, requestDto);
        EmployeePositionResponseDto actualBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(actualBody);
        verify(employeePositionService, times(1)).update(id, requestDto);
        assertEquals(responseDto.getId(), actualBody.getId());
        assertEquals(responseDto.getPosition(), actualBody.getPosition());
    }

    @DisplayName("Test delete Success")
    @Test
    void delete() {
        long id = 1;

        ResponseEntity<String> responseEntity = employeePositionController.delete(id);
        verify(employeePositionService, times(1)).delete(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}