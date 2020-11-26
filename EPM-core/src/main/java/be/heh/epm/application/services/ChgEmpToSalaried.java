package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.SalariedClassification;

public class ChgEmpToSalaried
{
    // ======== Attributes ========
    private int empId;
    private double amount;

    // ======== Constructor ========
    public ChgEmpToSalaried(int empId, double amount)
    {
        this.empId = empId;
        this.amount = amount;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setPayClassification(new SalariedClassification(amount));
            employee.setPaySchedule(new MonthlyPaymentSchedule());
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
