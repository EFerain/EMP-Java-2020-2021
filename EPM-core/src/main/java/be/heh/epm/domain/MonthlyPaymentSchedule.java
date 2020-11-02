package be.heh.epm.domain;

import java.time.LocalDate;

public class MonthlyPaymentSchedule implements PaymentSchedule
{
    // ======== Constructor ========
    public MonthlyPaymentSchedule()
    {
        // VOID
    }

    // ======== Methods ========
    @Override
    public boolean isDatePay(LocalDate date)
    {
        return (date.getDayOfMonth() == date.lengthOfMonth());

        // TODO jours ouvrables
    }
}
