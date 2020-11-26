package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;

public class ChgEmpName
{
    // ======== Attributes ========
    private int empId;
    private String name;

    // ======== Constructor ========
    public ChgEmpName(int empId, String name)
    {
        this.empId = empId;
        this.name = name;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setName(name);
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}