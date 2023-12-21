package by.petrovich.tool.controller;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.service.impl.EmployeeServiceImpl;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    private EmployeeServiceImpl employeeService;

    @DisplayName("Test find Success")
    @Test
    void find() {
        long id = 1;
        String name = "Petr";
        String surname = "Petrov";
        String patronymic = "Petrovich";
        String email = "petrov@mail.com";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        EmployeeResponseDto employeeResponseDto = createEmployeeResponseDto(id, createdAt, updatedAt, name, surname, patronymic, email);
        EmployeeResponseDto expected = createEmployeeResponseDto(id, createdAt, updatedAt, name, surname, patronymic, email);

        when(employeeService.find(id)).thenReturn(employeeResponseDto);

        ResponseEntity<EmployeeResponseDto> responseEntity = employeeController.find(id);
        EmployeeResponseDto actualBody = responseEntity.getBody();

        assertEquals(responseEntity.getStatusCode(), OK);
        assertNotNull(actualBody);
        verify(employeeService, times(1)).find(id);
        assertEquals(expected.getId(), actualBody.getId());
        assertEquals(expected.getName(), actualBody.getName());
        assertEquals(expected.getSurname(), actualBody.getSurname());
        assertEquals(expected.getPatronymic(), actualBody.getPatronymic());
        assertEquals(expected.getEmail(), actualBody.getEmail());
        assertEquals(expected.getCreatedAt(), actualBody.getCreatedAt());
        assertEquals(expected.getUpdatedAt(), actualBody.getUpdatedAt());
    }

    @DisplayName("Test findAll Success")
    @Test
    void findAll() {
        long id1 = 1;
        String name1 = "Petr";
        String surname1 = "Petrov";
        String patronymic1 = "Petrovich";
        String email1 = "petrov@mail.com";
        long id2 = 2L;
        String name2 = "Sidor";
        String surname2 = "Sidorov";
        String patronymic2 = "Sidorovich";
        String email2 = "jane.doe@example.com";
        LocalDateTime createdAt = LocalDateTime.now().withNano(0);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        List<EmployeeResponseDto> expectedList = Arrays.asList(
                createEmployeeResponseDto(id1, createdAt, updatedAt, name1, surname1, patronymic1, email1),
                createEmployeeResponseDto(id2, createdAt, updatedAt, name2, surname2, patronymic2, email2)
        );

        when(employeeService.findAll()).thenReturn(expectedList);

        ResponseEntity<List<EmployeeResponseDto>> responseEntity = employeeController.findAll();
        List<EmployeeResponseDto> actualBodyList = responseEntity.getBody();

        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(actualBodyList);
        assertEquals(expectedList.size(), actualBodyList.size());
        verify(employeeService, times(1)).findAll();
        IntStream.range(0, expectedList.size()).forEach(i -> {
            EmployeeResponseDto expectedDto = expectedList.get(i);
            EmployeeResponseDto actualDto = actualBodyList.get(i);
            assertEquals(expectedDto.getId(), actualDto.getId());
            assertEquals(expectedDto.getName(), actualDto.getName());
            assertEquals(expectedDto.getSurname(), actualDto.getSurname());
            assertEquals(expectedDto.getPatronymic(), actualDto.getPatronymic());
            assertEquals(expectedDto.getEmail(), actualDto.getEmail());
            assertEquals(expectedDto.getCreatedAt(), actualDto.getCreatedAt());
            assertEquals(expectedDto.getUpdatedAt(), actualDto.getUpdatedAt());
        });
    }

    @DisplayName("Test create Success")
    @Test
    void create() {
        long id = 1L;
        String name = "Petr";
        String surname = "Petrov";
        String email = "petrov@mail.com";

        EmployeeRequestDto employeeRequestDto = createEmployeeRequestDto(name, surname, email);

        EmployeeResponseDto employeeResponseDto = createEmployeeResponseDto(id, name, surname, email);

        when(employeeService.create(employeeRequestDto)).thenReturn(employeeResponseDto);

        ResponseEntity<EmployeeResponseDto> responseEntity = employeeController.create(employeeRequestDto);
        EmployeeResponseDto actualBody = responseEntity.getBody();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(actualBody);
        verify(employeeService, times(1)).create(employeeRequestDto);
        assertEquals(employeeResponseDto.getId(), actualBody.getId());
        assertEquals(employeeResponseDto.getName(), actualBody.getName());
        assertEquals(employeeResponseDto.getSurname(), actualBody.getSurname());
        assertEquals(employeeResponseDto.getEmail(), actualBody.getEmail());
    }

    @DisplayName("Test update Success")
    @Test
    void update() {
        long id = 1;
        String name = "Petr";
        String surname = "Petrov";
        String email = "petrov@mail.com";

        EmployeeRequestDto requestDto = createEmployeeRequestDto(name, surname, email);
        EmployeeResponseDto responseDto = createEmployeeResponseDto(id, name, surname, email);

        when(employeeService.update(id, requestDto)).thenReturn(responseDto);

        ResponseEntity<EmployeeResponseDto> responseEntity = employeeController.update(id, requestDto);
        EmployeeResponseDto actualBody = responseEntity.getBody();

        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(actualBody);
        verify(employeeService, times(1)).update(id, requestDto);
        assertEquals(responseDto.getId(), actualBody.getId());
        assertEquals(responseDto.getName(), actualBody.getName());
        assertEquals(responseDto.getSurname(), actualBody.getSurname());
        assertEquals(responseDto.getEmail(), actualBody.getEmail());
    }

    @DisplayName("Test delete Success")
    @Test
    void delete() {
        long id = 1;

        doNothing().when(employeeService).delete(id);

        ResponseEntity<Long> responseEntity = employeeController.delete(id);

        verify(employeeService, times(1)).delete(id);
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(id, responseEntity.getBody().longValue());
    }

    private EmployeeResponseDto createEmployeeResponseDto(long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String surname, String patronymic, String email) {
        return EmployeeResponseDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    private EmployeeResponseDto createEmployeeResponseDto(long id, String name, String surname, String email) {
        return EmployeeResponseDto.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .build();
    }

    private EmployeeRequestDto createEmployeeRequestDto(String name, String surname, String email) {
        return EmployeeRequestDto.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .build();
    }
}