package com.exemple.monexam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET : Liste des employés
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // POST : Ajouter un employé
    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            return new ResponseEntity<>(message("email.exists"), HttpStatus.BAD_REQUEST);
        }
        employeeRepository.save(employee);
        return new ResponseEntity<>(message("employee.added"), HttpStatus.CREATED);
    }

    // GET : Récupérer un employé par ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, message("employee.notfound")));
        return ResponseEntity.ok(employee);
    }

    // DELETE : Supprimer un employé
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Employé supprimé", HttpStatus.OK);
    }

    private String message(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    @Autowired
    private MessageSource messageSource;
}
