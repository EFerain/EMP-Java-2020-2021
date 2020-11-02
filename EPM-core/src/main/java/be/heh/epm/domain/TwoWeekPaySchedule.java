package be.heh.epm.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

public class TwoWeekPaySchedule implements PaymentSchedule
{
    // ======== Constructor ========
    public TwoWeekPaySchedule()
    {
        // VOID
    }

    // ======== Methods ========
    @Override
    public boolean isDatePay(LocalDate date)
    {
        TemporalField weekOfMonth = WeekFields.of(DayOfWeek.MONDAY, 1).weekOfMonth();
        
        return (date.getDayOfWeek() == DayOfWeek.FRIDAY && date.get(weekOfMonth) % 2 == 0);
    }
}
