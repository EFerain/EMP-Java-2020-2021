package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.DirectDepositMethod;
import be.heh.epm.domain.Employee;

public class ChgEmpToDirectDepositMethod
{
    // ======== Attributes ========
    private int empId;
    private String bank;
    private String account;

    // ======== Constructor ========
    public ChgEmpToDirectDepositMethod(int empId, String bank, String account)
    {
        this.empId = empId;
        this.bank = bank;
        this.account = account;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setPayMethod(new DirectDepositMethod(bank, account));
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
