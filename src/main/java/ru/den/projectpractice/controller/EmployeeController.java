package ru.den.projectpractice.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.den.projectpractice.dto.EmployeeDto;
import ru.den.projectpractice.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> findAll() {
        List<EmployeeDto> allEmployees = employeeService.findAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @PostMapping("/id")
    public ResponseEntity<EmployeeDto> findById(@RequestBody Long id) {
        EmployeeDto employeeDto = employeeService.findEmpById(id);
        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.addEmployee(employeeDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateEmp(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeService.updateEmployee(employeeDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<EmployeeDto> getEmployeeByEmail(@RequestBody String email) {
        EmployeeDto employee = employeeService.getEmployeeEmail(email);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
       try {
           employeeService.deleteEmployee(id);
       }catch (EntityNotFoundException e){
           e.printStackTrace();
           return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
