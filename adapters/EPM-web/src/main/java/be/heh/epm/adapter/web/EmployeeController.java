package be.heh.epm.adapter.web;

import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.in.AddSalariedEmployeeUseCase;
import be.heh.epm.common.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@WebAdapter
@RestController
public class EmployeeController
{
    private AddSalariedEmployeeUseCase addSalariedEmployee;

    public EmployeeController(AddSalariedEmployeeUseCase addSalariedEmployee){
        this.addSalariedEmployee = addSalariedEmployee;
    }

    @CrossOrigin
    @PostMapping("/employees/salaried")
    ResponseEntity<Void> newEmployee(@Valid @RequestBody SalariedEmployeeValidating newEmployee) {


        addSalariedEmployee.execute(newEmployee);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEmployee.getEmpId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
