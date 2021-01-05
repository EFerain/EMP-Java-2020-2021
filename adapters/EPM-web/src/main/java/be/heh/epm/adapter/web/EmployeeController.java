package be.heh.epm.adapter.web;

import be.heh.epm.adapter.web.model.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

        @GetMapping(value="/Employees")
        public String ShowAllEmployees() {
            return "Vue des employés";
        }

    @GetMapping(value="/Employee/{id}")
    public Employee ShowUniqueEmployee(@PathVariable int id) {
            Employee employee = new Employee(id, "Michel", "michel@gmail.com");
        return employee;
    }

    }

