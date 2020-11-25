/*
    DB simulation
*/

package be.heh.epm.application.ports.out;

import java.util.ArrayList;
import java.util.HashMap;

import be.heh.epm.domain.Employee;

public class InMemoryEmployeeGateway implements EmployeeGateway
{
    // ======== Attributes ========
    private HashMap<Integer, Employee> employee = new HashMap<Integer, Employee>();

    // ======== Constructor ========
    public InMemoryEmployeeGateway()
    {
        // VOID
    }

    // ======== Methods ========
    // ==== Save ====
    @Override
    public void saveEmployee(int empId, Employee employee)
    {
        this.employee.put(empId, employee);
    }

    // ==== Delete ====
    @Override
    public void deleteEmployee(int empID)
    {
        this.employee.remove(empID);
    }

    // ==== Get Employee ====
    @Override
    public Employee getEmployee(int empId)
    {
        return this.employee.get(empId);
    }

    // ==== Get All Employees ====
    @Override
    public ArrayList<Employee> getAllEmployee()
    {
        return new ArrayList<>(employee.values());
    }
}
