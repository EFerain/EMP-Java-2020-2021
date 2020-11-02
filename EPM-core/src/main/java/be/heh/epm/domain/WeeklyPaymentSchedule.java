package be.heh.epm.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklyPaymentSchedule implements PaymentSchedule
{
    // ======== Constructor ========
    public WeeklyPaymentSchedule()
    {
        // VOID
    }

    // ======== Methods ========
    @Override
    public boolean isDatePay(LocalDate date)
    {
        return (date.getDayOfWeek() == DayOfWeek.FRIDAY);
    }
}
