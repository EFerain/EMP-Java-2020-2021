package be.heh.epm.application.services;

import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;

public class AddSalariedEmployee
{
    // ======== Attributes ========
    private double monthlySalary;

    // ======== Constructor ========
    public AddSalariedEmployee(int empId, String name, String address, String mail, double monthlySalary, EmployeePort employeePort)
    {
        this.monthlySalary = monthlySalary;
    }

    protected PaymentSchedule makePaymentSchedule()
    {
        return new MonthlyPaymentSchedule();
    }

    protected PaymentClassification makePaymentClassification()
    {
        return new SalariedClassification(monthlySalary);
    }
}
