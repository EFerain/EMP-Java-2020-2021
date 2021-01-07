package be.heh.epm.adapter.web;

//import be.heh.epm.adapter.persistence.DataBaseHelper;
import be.heh.epm.adapter.web.model.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController
{
    //DataBaseHelper db = new DataBaseHelper();

    @GetMapping(value="/")
    public String index()
    {
        return "EPM : Employee Payroll Management";
    }

    @GetMapping(value="/Employees")
    public String ShowAllEmployees()
    {
        return "Vue des employés";
    }

    @GetMapping(value="/Employee/{empId}")
    public ResponseEntity getEmployee(@PathVariable(name = "empId") int empId)
    {
        Employee employee = new Employee();
        employee.setId(empId);
        //db.getEmployee(employee);


        if (employee.getId() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(employee);
        }
    }
}