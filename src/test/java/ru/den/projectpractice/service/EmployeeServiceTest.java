package ru.den.projectpractice.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.den.projectpractice.dto.EmployeeDto;
import ru.den.projectpractice.mapper.EmployeeMapper;
import ru.den.projectpractice.model.Employee;
import ru.den.projectpractice.model.Roles;
import ru.den.projectpractice.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @InjectMocks
    EmployeeService service;

    @Test
    void addEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();

        when(mapper.employeeDtoToEmployee(employeeDto)).thenReturn(employee);
        service.addEmployee(employeeDto);
        verify(repository).save(employee);
    }

    @Test
    void addEmployeeShouldThrowExeptionTest() {
        assertThrows(EntityNotFoundException.class, () -> service.addEmployee(null));
    }

    @Test
    void findAllEmployees() {
        List<Employee> employees = List.of(
                new Employee(1L, "Den", "Den", "den@mail.ru", Set.of(new Roles())),
                new Employee(1L, "Den", "Den", "dens@mail.ru", Set.of(new Roles())),
                new Employee(1L, "Den", "Den", "denr@mail.ru",Set.of(new Roles()))
        );
        List<EmployeeDto> dtos = List.of(
                new EmployeeDto(1L, "Den", "Den", "den@mail.ru"),
                new EmployeeDto(1L, "Den", "Den", "dens@mail.ru"),
                new EmployeeDto(1L, "Den", "Den", "denr@mail.ru")
        );

        when(repository.findAll()).thenReturn(employees);
        when(mapper.empDtoListToEmp(employees)).thenReturn(dtos);

        List<EmployeeDto> result = service.findAllEmployees();

        verify(repository).findAll();
        verify(mapper).empDtoListToEmp(employees);
        assertEquals(dtos, result);
    }

    @Test
    void findEmpByIdTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        EmployeeDto dto = new EmployeeDto();
        dto.setId(1L);
        when(repository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(mapper.employeeToEmployeeDto(employee)).thenReturn(dto);

        EmployeeDto result = service.findEmpById(dto.getId());

        verify(repository).findById(employee.getId());
        verify(mapper).employeeToEmployeeDto(employee);
        assertEquals(dto, result);

    }

    @Test
    void findEmpByIdShouldBeThrowException() {
        Long id = 0L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.findEmpById(id));
    }

    @Test
    void updateEmployeeTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();

        when(repository.findById(employee.getId())).thenReturn(Optional.of(employee));

        service.updateEmployee(employeeDto);

        verify(repository).findById(employee.getId());
        verify(mapper).updateEmployeeFromDto(employeeDto, employee);
        verify(repository).save(employee);
    }

    @Test
    void getEmployeeEmailTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("den@mail.ru");

        Employee employee = new Employee();
        employee.setEmail("den@mail.ru");

        when(repository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
        when(mapper.employeeToEmployeeDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = service.getEmployeeEmail(employeeDto.getEmail());

        assertEquals(employeeDto.getEmail(), result.getEmail());
    }

    @Test
    void getEmployeeEmailShouldThrowExeptionTest() {
        String email = "notFound@example";

        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.getEmployeeEmail(email));

        assertTrue(exception.getMessage().contains("Email is not found"));
        verify(repository).findByEmail(email);
    }

    @Test
    void deleteEmployeeTest() {
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);

       when(repository.findById(id)).thenReturn(Optional.of(employee));

       service.deleteEmployee(employee.getId());

       verify(repository).deleteById(id);
    }

    @Test
    void deleteEmployeeShouldBeThrowException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.findEmpById(id));
        verify(repository, never()).deleteById(id);
    }

}