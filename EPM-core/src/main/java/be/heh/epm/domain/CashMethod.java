package be.heh.epm.domain;

public class CashMethod implements PaymentMethod
{
    // ======== Constructor ========
    public CashMethod()
    {
        // VOID
    }

    // ======== Methods ========
    @Override
    public String toString()
    {
        return "cash";
    }
}
