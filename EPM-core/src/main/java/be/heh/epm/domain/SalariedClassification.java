package be.heh.epm.domain;

public class SalariedClassification implements PaymentClassification
{
    // ======== Attributes ========
    private double amount;

    // ======== Constructor ========
    public SalariedClassification(double amount)
    {
        this.amount = amount;
    }

    // ======== Methods ========
    @Override
    public double calculatePay(PayCheck pc)
    {
        return amount;
    }
}
