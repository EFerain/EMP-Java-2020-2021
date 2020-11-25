/*
    Commission + TwoWeek
*/

package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.TwoWeekPaySchedule;

public class AddCommissionEmployee
{
    // ======== Attributes ========
    protected Employee commissionEmployee;

    private double monthlySalary;
    private double commissionRate;

    // ======== Constructor ========
    public AddCommissionEmployee(int empId, String name, String address, String mail, double monthlySalary, double commissionRate)
    {
        this.commissionEmployee = new Employee(empId, name, address, mail);
        this.monthlySalary = monthlySalary;
        this.commissionRate = commissionRate;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        this.commissionEmployee.setPayClassification(makePaymentClassification());
        this.commissionEmployee.setPaySchedule(makePaymentSchedule());

        Context.empId.saveEmployee(commissionEmployee.getEmpId(), commissionEmployee);
    }

    // Classification
    public PaymentClassification makePaymentClassification()
    {
        return new CommissionClassification(this.monthlySalary, this.commissionRate);
    }

    // Schedule
    public PaymentSchedule makePaymentSchedule()
    {
        return new TwoWeekPaySchedule();
    }
}
