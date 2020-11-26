package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;

public class ChgEmpAddress
{
    // ======== Attributes ========
    private int empId;
    private String address;

    // ======== Constructor ========
    public ChgEmpAddress(int empId, String address)
    {
        this.empId = empId;
        this.address = address;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setAddress(address);
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
