package com.exemple.monexam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Récupérer tous les employés
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Récupérer un employé par ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Ajouter un employé
    public Employee addEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return employeeRepository.save(employee);
    }

    // Modifier un employé
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee employee = getEmployeeById(id); // Vérifier si l'employé existe
        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        return employeeRepository.save(employee);
    }

    // Supprimer un employé
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }
}
