package be.heh.epm.application.services;

import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.PaymentClassification;
/*
    Hourly + Weekly
*/

import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.WeeklyPaymentSchedule;

public class AddHourlyEmployee extends AddEmployee
{
    // ======== Attributes ========
    private double hourlyRate;

    // ======== Constructor ========
    public AddHourlyEmployee(int id, String name, String address, String mail, double hourlyRate)
    {
        super(id, name, address, mail);
        this.hourlyRate = hourlyRate;
    }

    // ======== Methods ========
    // Classification
    @Override
    public PaymentClassification makePaymentClassification()
    {
        return new HourlyClassification(this.hourlyRate);
    }
    
    // Schedule
    @Override
    public PaymentSchedule makePaymentSchedule()
    {
        return new WeeklyPaymentSchedule();
    }
}
