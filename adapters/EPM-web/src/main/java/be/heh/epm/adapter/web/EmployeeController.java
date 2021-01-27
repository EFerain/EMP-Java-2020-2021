package be.heh.epm.adapter.web;

import be.heh.epm.application.ports.in.*;
import be.heh.epm.common.WebAdapter;
import be.heh.epm.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;


@WebAdapter
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController
{
    // ======== Attributes ========
    private AddSalariedEmployeeUseCase addSalariedEmployee;
    private DeleteSalariedEmployeeUseCase deleteSalariedEmployee;
    private GetSalariedEmployeeUseCase getSalariedEmployee;
    private GetAllSalariedEmployeesUseCase getAllSalariedEmployees;

    // ======== Constructor ========
    public EmployeeController(AddSalariedEmployeeUseCase addSalariedEmployee,
                              DeleteSalariedEmployeeUseCase deleteSalariedEmployee,
                              GetSalariedEmployeeUseCase getSalariedEmployee,
                              GetAllSalariedEmployeesUseCase getAllSalariedEmployees)
    {
        this.addSalariedEmployee = addSalariedEmployee;
        this.deleteSalariedEmployee = deleteSalariedEmployee;
        this.getSalariedEmployee = getSalariedEmployee;
        this.getAllSalariedEmployees = getAllSalariedEmployees;
    }

    // ======== Mapping ========
    // Test
    @GetMapping("/")
    ResponseEntity<String> test()
    {
        return ResponseEntity.ok("ALLO ?");
    }

    // Add salaried employee
    @PostMapping("/employees/salaried")
    ResponseEntity<Void> newEmployee(@Valid @RequestBody SalariedEmployeeValidating newEmployee)
    {
        addSalariedEmployee.execute(newEmployee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEmployee.getEmpId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    // Delete 1 salaried employee
    @DeleteMapping("/employees/delete")
    ResponseEntity<Void> deleteEmployee(@Valid @RequestBody Integer deleteEmployeeId)
    {
        deleteSalariedEmployee.execute(deleteEmployeeId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(deleteEmployeeId)
                .toUri();

        return null;
    }

    // Get 1 salaried employee
    @GetMapping("/employees/get")
    ResponseEntity<Employee> getEmployee(@Valid @RequestBody Integer getEmployeeId)
    {
        Employee emp = getSalariedEmployee.execute(getEmployeeId);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(getEmployeeId)
                .toUri();

        return ResponseEntity.ok(emp);
    }

    // Get ALL salaried employees
    @GetMapping("/employees/getall")
    ResponseEntity<ArrayList<Employee>> getAllEmployees()
    {
        ArrayList<Employee> employees = getAllSalariedEmployees.execute();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.ok(employees);
    }
}
