/*
    Interface
*/

package be.heh.epm.application.ports.out;

import java.util.ArrayList;

import be.heh.epm.domain.Employee;

public interface EmployeeGateway
{
    // ======== Methods ========
    // ==== Save ====
    public void saveEmployee(int empId, Employee employee);

    // ==== Delete ====
    public void deleteEmployee(int empId);

    // ==== Get Employee ====
    public Employee getEmployee(int empId);

    // ==== Get All Employees ====
    public ArrayList<Employee> getAllEmployees();
}
