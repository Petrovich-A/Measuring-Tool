package by.petrovich.tool.service.impl;

import by.petrovich.tool.dto.request.EmployeeRequestDto;
import by.petrovich.tool.dto.response.EmployeeResponseDto;
import by.petrovich.tool.exception.ResourceNotFoundException;
import by.petrovich.tool.mapper.EmployeeMapper;
import by.petrovich.tool.model.Employee;
import by.petrovich.tool.repository.EmployeeRepository;
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
class EmployeeServiceImplTest {
    @Autowired
    @Spy
    private EmployeeMapper employeeMapper;

    private EmployeeServiceImpl employeeService;

    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository, employeeMapper);
    }

    @DisplayName("test findAll method")
    @Test
    void whenFindAll_thenReturnListOfEmployeeResponseDto() {
        long id1 = 1;
        String personnelNumber1 = "11111";
        String name1 = "Petr";
        String surname1 = "Petrov";
        String patronymic1 = "Petrovich";
        String email1 = "petrov@mail.com";
        LocalDateTime createdAt1 = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt1 = LocalDateTime.of(2023, 12, 15, 14, 35, 55);
        Employee employee1 = createEmployee(id1, personnelNumber1, createdAt1, updatedAt1, name1, surname1, patronymic1, email1);

        long id2 = 2L;
        String personnelNumber2 = "22222";
        String name2 = "Ivan";
        String surname2 = "Ivanov";
        String patronymic2 = "Ivanovich";
        String email2 = "ivanov@mail.com";
        LocalDateTime createdAt2 = LocalDateTime.of(2023, 12, 14, 21, 30, 10);
        LocalDateTime updatedAt2 = LocalDateTime.of(2023, 12, 15, 15, 45, 30);
        Employee employee2 = createEmployee(id2, personnelNumber2, createdAt2, updatedAt2, name2, surname2, patronymic2, email2);

        List<Employee> employees = Arrays.asList(employee1, employee2);

        EmployeeResponseDto employeeResponseDto1 = createEmployeeResponseDto(id1, personnelNumber1, createdAt1, updatedAt1, name1, surname1, patronymic1, email1);
        EmployeeResponseDto employeeResponseDto2 = createEmployeeResponseDto(id2, personnelNumber2, createdAt2, updatedAt2, name2, surname2, patronymic2, email2);
        List<EmployeeResponseDto> expected = Arrays.asList(employeeResponseDto1, employeeResponseDto2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeResponseDto> actual = employeeService.findAll();

        assertNotNull(actual);
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(2)).toResponseDto(any());
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }


    @DisplayName("test find method")
    @Test
    void find() {
        long id = 1;
        String personnelNumber = "11111";
        String name = "Petr";
        String surname = "Petrov";
        String patronymic = "Petrovich";
        String email = "petrov@mail.com";
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 12, 15, 14, 35, 55);
        Employee employee = createEmployee(id, personnelNumber, createdAt, updatedAt, name, surname, patronymic, email);
        EmployeeResponseDto expected = createEmployeeResponseDto(id, personnelNumber, createdAt, updatedAt, name, surname, patronymic, email);

        when(employeeRepository.findById(id)).thenReturn(Optional.ofNullable(employee));

        EmployeeResponseDto actual = employeeService.find(id);

        assertNotNull(actual);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toResponseDto(employee);
        assertEquals(expected, actual);
    }

    @DisplayName("test find method with throwing exception")
    @Test
    void givenInvalidEmployeeId_whenFind_thenThrowResourceNotFoundException() {
        long id = 1;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.find(id));
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, never()).toResponseDto(any());
    }

    @DisplayName("test create method")
    @Test
    void givenEmployeeRequestDto_whenCreate_thenReturnEmployeeResponseDto() {
        long id = 1;
        String personnelNumber = "11111";
        String name = "Petr";
        String surname = "Petrov";
        String patronymic = "Petrovich";
        String email = "petrov@mail.com";
        LocalDateTime localDateTime = LocalDateTime.now().withNano(0);

        EmployeeRequestDto employeeRequestDto = createEmployeeRequestDto(personnelNumber, name, surname, patronymic, email);

        Employee employeeWithoutId = Employee.builder()
                .personnelNumber(personnelNumber)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .createdAt(localDateTime)
                .updatedAt(localDateTime)
                .build();

        Employee employee = createEmployee(id, personnelNumber, localDateTime, localDateTime, name, surname, patronymic, email);
        EmployeeResponseDto expected = createEmployeeResponseDto(id, personnelNumber, localDateTime, localDateTime, name, surname, patronymic, email);

        when(employeeRepository.save(employeeWithoutId)).thenReturn(employee);

        EmployeeResponseDto actual = employeeService.create(employeeRequestDto);

        assertNotNull(actual);
        verify(employeeMapper, times(1)).toEntity(employeeRequestDto);
        verify(employeeRepository, times(1)).save(employeeWithoutId);
        verify(employeeMapper, times(1)).toResponseDto(employee);
        assertEquals(expected, actual);
    }

    @DisplayName("test update method")
    @Test
    void givenEmployeeIdAndEmployeeRequestDto_whenUpdate_thenReturnEmployeeResponseDto() {
        long id = 1;
        String personnelNumber = "11111";
        String name = "Petr";
        String surname = "Petrov";
        String patronymic = "Petrovich";
        String email = "petrov@mail.com";
        LocalDateTime createdAt = LocalDateTime.of(2023, 12, 14, 20, 15, 45);
        LocalDateTime updatedAt = LocalDateTime.now().withNano(0);
        LocalDateTime currentUpdatedAt = LocalDateTime.now().withNano(0);

        EmployeeRequestDto employeeRequestDto = createEmployeeRequestDto(id, personnelNumber, name, surname, patronymic, email, createdAt, updatedAt);

        Optional<Employee> employeeOptional = Optional.of(createEmployee(id, personnelNumber, createdAt, updatedAt, name, surname, patronymic, email));
        Employee employeeUpdated = createEmployee(id, personnelNumber, createdAt, currentUpdatedAt, name, surname, patronymic, email);
        Employee employeeSaved = createEmployee(id, personnelNumber, createdAt, currentUpdatedAt, name, surname, patronymic, email);
        EmployeeResponseDto expected = createEmployeeResponseDto(id, personnelNumber, createdAt, currentUpdatedAt, name, surname, patronymic, email);

        when(employeeRepository.findById(id)).thenReturn(employeeOptional);
        when(employeeRepository.save(employeeUpdated)).thenReturn(employeeSaved);

        EmployeeResponseDto actual = employeeService.update(id, employeeRequestDto);

        assertNotNull(actual);
        verify(employeeRepository, times(1)).findById(id);
        verify(employeeMapper, times(1)).toEntityUpdate(employeeRequestDto, employeeOptional.get());
        verify(employeeRepository, times(1)).save(employeeUpdated);
        verify(employeeMapper, times(1)).toResponseDto(employeeSaved);
        assertEquals(expected, actual);
    }

    @DisplayName("test update method with throwing exception")
    @Test
    void givenInvalidEmployeeIdAndEmployeeRequestDto_whenUpdate_thenThrowResourceNotFoundException() {
        long id = 1;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        verify(employeeRepository, never()).saveAndFlush(any());
        assertThrows(ResourceNotFoundException.class, () -> employeeService.find(id));
    }

    @DisplayName("test delete method")
    @Test
    void givenEmployeeId_whenDelete_shouldDeleteEmployee() {
        long id = 1;

        when(employeeRepository.existsById(id)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(isA(Long.class));

        employeeService.delete(id);

        verify(employeeRepository, times(1)).existsById(id);
        verify(employeeRepository, times(1)).deleteById(id);
    }

    @DisplayName("test delete method with throwing exception")
    @Test
    void givenInvalidEmployeeId_whenDelete_thenThrowResourceNotFoundException() {
        long id = 1;

        when(employeeRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.delete(id));
        verify(employeeRepository, times(1)).existsById(id);
        verify(employeeRepository, never()).deleteById(any());
    }

    private Employee createEmployee(long id, String personnelNumber, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String surname, String patronymic, String email) {
        return Employee.builder()
                .id(id)
                .personnelNumber(personnelNumber)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    private EmployeeRequestDto createEmployeeRequestDto(long id, String personnelNumber, String name, String surname, String patronymic, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return EmployeeRequestDto.builder()
                .id(id)
                .personnelNumber(personnelNumber)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    private EmployeeResponseDto createEmployeeResponseDto(long id, String personnelNumber, LocalDateTime createdAt, LocalDateTime updatedAt, String name, String surname, String patronymic, String email) {
        return EmployeeResponseDto.builder()
                .id(id)
                .personnelNumber(personnelNumber)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    private EmployeeRequestDto createEmployeeRequestDto(String personnelNumber, String name, String surname, String patronymic, String email) {
        return EmployeeRequestDto.builder()
                .personnelNumber(personnelNumber)
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .email(email)
                .build();
    }
}