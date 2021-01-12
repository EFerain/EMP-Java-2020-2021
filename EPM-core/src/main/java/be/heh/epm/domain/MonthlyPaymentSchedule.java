package be.heh.epm.domain;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class MonthlyPaymentSchedule implements PaymentSchedule
{
    // ======== Constructor ========
    public MonthlyPaymentSchedule()
    {
        // VOID
    }

    // ======== Methods ========
    // ==== Know the date ====
    // GET last day of the month
    private LocalDate getLastDayOfMonth(LocalDate date)
    {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    // IS last day of the month (true or false)
    private boolean isLastDayOfMonth(LocalDate date)
    {
        return getLastDayOfMonth(date).equals(date);
    }

    // ==== isPayDate ====
    @Override
    public boolean isPayDate(LocalDate date)
    {
        return isLastDayOfMonth(date);
    }

    // ==== toString ====
    public String toString()
    {
        return "monthly";
    }
}
