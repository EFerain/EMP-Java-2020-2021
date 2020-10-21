package be.heh.epm.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class TimeCard
{
    // ======== Attributes ========
    @Getter @Setter private LocalDate date;
    @Getter private double time;

    // ======== Constructor ========
    public TimeCard()
    {
        // VOID
    }

    public TimeCard(LocalDate date, double time)
    {
        this.date = date;
        this.time = time;
    }

    // ======== Methods ========
    // VOID
}
