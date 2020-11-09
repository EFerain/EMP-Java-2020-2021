/*
    Commission + TwoWeek
*/

package be.heh.epm.application.services;

import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.TwoWeekPaySchedule;

public class AddCommissionEmployee extends AddEmployee
{
    // ======== Attributes ========
    private double monthlySalary;
    private double commissionRate;

    // ======== Constructor ========
    public AddCommissionEmployee(int id, String name, String address, String mail, double monthlySalary, double commissionRate)
    {
        super(id, name, address, mail);
        this.monthlySalary = monthlySalary;
        this.commissionRate = commissionRate;
    }

    // ======== Methods ========
    // Classification
    @Override
    public PaymentClassification makePaymentClassification()
    {
        return new CommissionClassification(this.monthlySalary, this.commissionRate);
    }
    
    // Schedule
    @Override
    public PaymentSchedule makePaymentSchedule()
    {
        return new TwoWeekPaySchedule();
    }
}
