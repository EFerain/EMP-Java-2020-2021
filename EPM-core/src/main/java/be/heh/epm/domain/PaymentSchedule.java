/*
    INTERFACE
*/

package be.heh.epm.domain;

import java.time.LocalDate;

public interface PaymentSchedule
{
    // ======== Method ========
    public boolean isPayDate(LocalDate date);
}
