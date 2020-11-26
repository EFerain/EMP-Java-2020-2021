package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.TwoWeekPaySchedule;

public class ChgEmpToCommission
{
    // ======== Attributes ========
    private int empId;
    private double amount;
    private double commissionRate;

    // ======== Constructor ========
    public ChgEmpToCommission(int empId, double amount, double commissionRate)
    {
        this.empId = empId;
        this.amount = amount;
        this.commissionRate = commissionRate;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setPayClassification(new CommissionClassification(amount, commissionRate));
            employee.setPaySchedule(new TwoWeekPaySchedule());
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
