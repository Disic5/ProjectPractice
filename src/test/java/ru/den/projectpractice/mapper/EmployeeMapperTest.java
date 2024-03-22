package ru.den.projectpractice.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.den.projectpractice.dto.EmployeeDto;
import ru.den.projectpractice.model.Employee;
import ru.den.projectpractice.model.Roles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    void employeeToEmployeeDtoTest() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("den");
        employee.setLastname("sen");
        employee.setEmail("den@msdf");

        EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);
        assertEquals(employee.getId(), employeeDto.getId());
        assertEquals(employee.getName(), employeeDto.getName());
        assertEquals(employee.getLastname(), employeeDto.getLastname());
        assertEquals(employee.getEmail(), employeeDto.getEmail());
    }

    @Test
    void employeeDtoToEmployeeTest() {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(1L);
        dto.setEmail("email.mail");
        dto.setLastname("den");
        dto.setName("den");

        Employee employee = employeeMapper.employeeDtoToEmployee(dto);

        assertEquals(dto.getName(), employee.getName());
        assertEquals(dto.getLastname(), employee.getLastname());
        assertEquals(dto.getEmail(), employee.getEmail());
        assertEquals(dto.getId(), employee.getId());
    }

    @Test
    void empListToEmpDtoTest() {
        List<Employee> employees = List.of(
                new Employee(1L, "Den", "Den", "den@mail.ru", Set.of(new Roles())),
                new Employee(1L, "Den", "Den", "dens@mail.ru",Set.of(new Roles())),
                new Employee(1L, "Den", "Den", "denr@mail.ru", Set.of(new Roles()))
        );

        List<EmployeeDto> dtos = employeeMapper.empDtoListToEmp(employees);

        assertEquals(dtos.size(), employees.size());

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            EmployeeDto dto = dtos.get(i);

            assertEquals(employee.getId(), dto.getId());
            assertEquals(employee.getName(), dto.getName());
            assertEquals(employee.getLastname(), dto.getLastname());
            assertEquals(employee.getEmail(), dto.getEmail());
        }
    }

    @Test
    void empDtoListToEmpTest() {
        List<EmployeeDto> employeeDto = List.of(
                new EmployeeDto(1L, "Den", "Den", "den@mail.ru"),
                new EmployeeDto(1L, "Den", "Den", "dens@mail.ru"),
                new EmployeeDto(1L, "Den", "Den", "denr@mail.ru")
        );

        List<Employee> employees = employeeMapper.empListToEmpDto(employeeDto);

        assertEquals(employeeDto.size(), employees.size());

        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            EmployeeDto dto = employeeDto.get(i);

            assertEquals(dto.getId(), employee.getId());
            assertEquals(dto.getName(), employee.getName());
            assertEquals(dto.getLastname(), employee.getLastname());
            assertEquals(dto.getEmail(), employee.getEmail());
        }

    }
}
