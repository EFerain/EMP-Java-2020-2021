/*
    Salaried + Monthly
*/

package be.heh.epm.application.services;

import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;

public class AddSalariedEmployee extends AddEmployee
{
    // ======== Attributes ========
    private double monthlySalary;

    // ======== Constructor ========
    public AddSalariedEmployee(int id, String name, String address, String mail, double monthlyRate)
    {
        super(id, name, address, mail);
        this.monthlySalary = monthlyRate;
    }

    // ======== Methods ========
    // Classification
    @Override
    public PaymentClassification makePaymentClassification()
    {
        return new SalariedClassification(this.monthlySalary);
    }
    
    // Schedule
    @Override
    public PaymentSchedule makePaymentSchedule()
    {
        return new MonthlyPaymentSchedule();
    }
}
