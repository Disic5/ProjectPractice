package ru.den.projectpractice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.den.projectpractice.dto.EmployeeDto;
import ru.den.projectpractice.model.Employee;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);

    List<Employee> empListToEmpDto(List<EmployeeDto> employeeDtos);

    List<EmployeeDto> empDtoListToEmp(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee employee);

}
