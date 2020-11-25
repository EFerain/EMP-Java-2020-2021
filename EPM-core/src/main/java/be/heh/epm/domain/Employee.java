package be.heh.epm.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class Employee
{
    // ======== Attributes ========
    @Getter @Setter private int empId;
    @Getter @Setter private String name;
    @Getter @Setter private String address;
    @Getter @Setter private String mail;

    @Getter @Setter private PaymentClassification payClassification;
    @Getter @Setter private PaymentMethod payMethod;
    @Getter @Setter private PaymentSchedule paySchedule;

    // ======== Constructor ========
    public Employee()
    {
        // VOID
    }

    public Employee(int empId, String name, String address, String mail)
    {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.mail = mail;
    }

    // ======== Getters & Setters ========
    // Lombok @Getter @Setter

    // ======== Methods ========
    public void payDay(PayCheck payCheck)
    {
        double salary = payClassification.calculatePay(payCheck);
        payCheck.setPay(salary);
    }

    public boolean isDatePay(LocalDate date)
    {
        return paySchedule.isDatePay(date);
    }
}