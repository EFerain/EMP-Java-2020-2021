package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;

public class DeleteEmployee
{
    // ======== Attributes ========
    private int empId;

    // ======== Constructor ========
    public DeleteEmployee(int empId)
    {
        this.empId = empId;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        Context.empId.deleteEmployee(empId);
    }
}
