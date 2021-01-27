package be.heh.epm.adapter.web;

import be.heh.epm.application.ports.in.DeleteSalariedEmployeeUseCase;
import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.in.AddSalariedEmployeeUseCase;
import be.heh.epm.common.WebAdapter;
import be.heh.epm.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@WebAdapter
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController
{
    // ======== Attributes ========
    private AddSalariedEmployeeUseCase addSalariedEmployee;
    private DeleteSalariedEmployeeUseCase deleteSalariedEmployee;
    // private GetSalariedEmployeeUseCase getSalariedEmployee;
    // private GetAllSalariedEmployeeUseCase getAllSalariedEmployee;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // ======== Constructor ========
    public EmployeeController(AddSalariedEmployeeUseCase addSalariedEmployee,
                              DeleteSalariedEmployeeUseCase deleteSalariedEmployee)
    {
        this.addSalariedEmployee = addSalariedEmployee;
        this.deleteSalariedEmployee = deleteSalariedEmployee;
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

    // Get ALL salaried employees
}
