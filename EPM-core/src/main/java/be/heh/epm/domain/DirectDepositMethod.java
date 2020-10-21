package be.heh.epm.domain;

import lombok.Getter;
import lombok.Setter;

public class DirectDepositMethod implements PaymentMethod
{
    // ======== Attributes ========
    @Getter @Setter private String bank;
    @Getter @Setter private String account;

    // ======== Constructor ========
    public DirectDepositMethod()
    {
        // VOID
    }

    public DirectDepositMethod(String bank, String account)
    {
        this.bank = bank;
        this.account = account;
    }

    // ======== Methods ========
    @Override
    public String toString()
    {
        return "direct deposit into " + this.bank + " : " + this.account;
    }
}
