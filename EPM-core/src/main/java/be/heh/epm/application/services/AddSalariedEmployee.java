/*
    Salaried + Monthly
*/

package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;

public class AddSalariedEmployee
{
    // ======== Attributes ========
    protected Employee employee;

    private double monthlySalary;

    // ======== Constructor ========
    public AddSalariedEmployee(int empId, String name, String address, String mail, double monthlySalary)
    {
        this.employee = new Employee(empId, name, address, mail);
        this.monthlySalary = monthlySalary;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        this.employee.setPayClassification(makePaymentClassification());
        this.employee.setPaySchedule(makePaymentSchedule());

        Context.empId.saveEmployee(employee.getEmpId(), employee);
    }

    // Classification
    public PaymentClassification makePaymentClassification()
    {
        return new SalariedClassification(this.monthlySalary);
    }

    // Schedule
    public PaymentSchedule makePaymentSchedule()
    {
        return new MonthlyPaymentSchedule();
    }
}
