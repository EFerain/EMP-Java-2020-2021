package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.WeeklyPaymentSchedule;

public class ChgEmpToHourly
{
    // ======== Attributes ========
    private int empId;
    private double hourlyRate;

    // ======== Constructor ========
    public ChgEmpToHourly(int empId, double hourlyRate)
    {
        this.empId = empId;
        this.hourlyRate = hourlyRate;
    }

    // ======== Methods ========
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            employee.setPayClassification(new HourlyClassification(hourlyRate));
            employee.setPaySchedule(new WeeklyPaymentSchedule());
        }
        // Alternative 1 : Transaction errors
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
