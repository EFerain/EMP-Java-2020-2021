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
    void deleteEmployee(int empID);

    // ==== Get Employee ====
    Employee getEmployee(int empID);

    // ==== Get All Employee ====
    ArrayList<Employee> getAllEmployee();
}
