package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.MailMethod;

public class ChgEmpToMailMethod
{
    // ======== Attributes ========
    private int empId;
    private String mail;

    // ======== Constructor ========
    public ChgEmpToMailMethod(int empId)
    {
        this.empId = empId;
        mail = Context.empId.getEmployee(empId).getMail();
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setPayMethod(new MailMethod(mail));
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
