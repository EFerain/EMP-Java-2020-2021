package be.heh.epm.domain;

import java.time.LocalDate;

public class TimeCard
{
    // ======== Attributes ========
    private LocalDate date;
    private double hours;

    // ======== Constructor ========
    public TimeCard(LocalDate date, double hours)
    {
        this.date = date;
        this.hours = hours;
    }

    // ======== Methods ========
    // ==== getDate ====
    public LocalDate getDate()
    {
        return date;
    }

    // ==== getHours ====
    public double getHours()
    {
        return hours;
    }
}
