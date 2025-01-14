package ru.den.projectpractice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.den.projectpractice.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);
}
