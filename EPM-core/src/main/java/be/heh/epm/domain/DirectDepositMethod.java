package be.heh.epm.domain;

import lombok.Getter;

public class DirectDepositMethod implements PaymentMethod
{
    // ======== Attributes ========
    @Getter private String bank;
    @Getter private String account;

    // ======== Constructor ========
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

    @Override
    public void pay(PayCheck payCheck)
    {
        payCheck.setField("Disposition", "Bank");
    }
}
