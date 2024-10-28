package com.exemple.monexam;
import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
}



