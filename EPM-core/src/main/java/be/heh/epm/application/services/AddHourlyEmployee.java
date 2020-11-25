/*
    Hourly + Weekly
*/

package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.WeeklyPaymentSchedule;

public class AddHourlyEmployee
{
    // ======== Attributes ========
    protected Employee hourlyEmployee;
    
    private double hourlyRate;

    // ======== Constructor ========
    public AddHourlyEmployee(int empId, String name, String address, String mail, double hourlyRate)
    {
        this.hourlyEmployee = new Employee(empId, name, address, mail);
        this.hourlyRate = hourlyRate;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        this.hourlyEmployee.setPayClassification(makePaymentClassification());
        this.hourlyEmployee.setPaySchedule(makePaymentSchedule());

        Context.empId.saveEmployee(hourlyEmployee.getEmpId(), hourlyEmployee);
    }

    // Classification
    public PaymentClassification makePaymentClassification()
    {
        return new HourlyClassification(this.hourlyRate);
    }

    // Schedule
    public PaymentSchedule makePaymentSchedule()
    {
        return new WeeklyPaymentSchedule();
    }
}
