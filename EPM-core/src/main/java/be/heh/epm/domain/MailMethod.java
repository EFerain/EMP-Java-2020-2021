package be.heh.epm.domain;

import lombok.Getter;

public class MailMethod implements PaymentMethod
{
    // ======== Attributes ========
    @Getter private String mail;

    // ======== Constructor ========
    public MailMethod(String mail)
    {
        this.mail = mail;
    }

    // ======== Methods ========
    @Override
    public String toString()
    {
        return String.format("mail : %s", mail);
    }

    @Override
    public void pay(PayCheck payCheck)
    {
        payCheck.setField("Disposition", "Mail");
    }
}
