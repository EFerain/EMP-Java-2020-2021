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
    public boolean isPayDate(LocalDate date)
    {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return dayOfWeek.equals(DayOfWeek.FRIDAY);
    }

    @Override
    public String toString()
    {
        return "monthly";
    }
}
