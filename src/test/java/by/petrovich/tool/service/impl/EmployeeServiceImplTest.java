package by.petrovich.tool.service.impl;

import by.petrovich.tool.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    void find() {
//        Employee employee = employeeService.find(1L);
//        assertNotNull(employee);
    }
}