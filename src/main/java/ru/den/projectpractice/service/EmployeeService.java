package ru.den.projectpractice.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ru.den.projectpractice.dto.EmployeeDto;
import ru.den.projectpractice.mapper.EmployeeMapper;
import ru.den.projectpractice.model.Employee;
import ru.den.projectpractice.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public void addEmployee(EmployeeDto employeeDto) {
        if (Objects.isNull(employeeDto)) {
            throw new EntityNotFoundException("Entity is null");
        }
        Employee employee = mapper.employeeDtoToEmployee(employeeDto);
        employeeRepository.save(employee);
    }

    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return mapper.empDtoListToEmp(employees);
    }

    public EmployeeDto findEmpById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id with " + id + " not found"));
        return mapper.employeeToEmployeeDto(employee);
    }

    public void updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id " + employeeDto.getId()));
        mapper.updateEmployeeFromDto(employeeDto, employee);
        employeeRepository.save(employee);
    }

    public EmployeeDto getEmployeeEmail(String email){
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email is not found"));
        return mapper.employeeToEmployeeDto(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id " + id));
        employeeRepository.deleteById(id);
    }
}
