/*
    ABSTRACT
*/

package be.heh.epm.application.services;

import be.heh.epm.domain.Employee;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;

public abstract class AddEmployee implements Command
{
    // ======== Attributes ========
    protected Employee employee;

    // ======== Constructor ========
    public AddEmployee(int id, String name, String address, String mail)
    {
        this.employee = new Employee(id, name, address, mail);
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        this.employee.setPayClassification(makePaymentClassification());
        this.employee.setPaySchedule(makePaymentSchedule());
    }

    // Classification
    public abstract PaymentClassification makePaymentClassification();

    // Schedule
    public abstract PaymentSchedule makePaymentSchedule();
}
