/*
    INTERFACE
*/

package be.heh.epm.application.ports.out;

import java.util.ArrayList;

import be.heh.epm.domain.Employee;

public interface EmployeePort
{
    // ======== Methods ========
    // ==== Save ====
    Employee save(Employee employee);

    // ==== Delete ====
    void deleteEmployee(int empId);

    // ==== Get Employee ====
    Employee getEmployee(int empId);

    // ==== Get All Employee ====
    ArrayList<Employee> getAllEmployee();
}
