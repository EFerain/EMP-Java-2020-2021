package be.heh.epm.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class SalesReceipt
{
    // ======== Attributes ========
    @Getter @Setter private LocalDate date;
    @Getter @Setter private double salesAmount;

    // ======== Constructor ========
    public SalesReceipt(LocalDate date, double salesAmount)
    {
        this.date = date;
        this.salesAmount = salesAmount;
    }
}
