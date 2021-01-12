package be.heh.epm.domain;

import lombok.Getter;

public class SalariedClassification implements PaymentClassification
{
    // ======== Attributes ========
    @Getter private double salary;

    // ======== Constructor ========
    public SalariedClassification(double salary)
    {
        this.salary = salary;
    }

    // ======== Methods ========
    @Override
    public double calculatePay(PayCheck payCheck)
    {
        return salary;
    }

    public String toString()
    {
        return "salaried";
    }
}
