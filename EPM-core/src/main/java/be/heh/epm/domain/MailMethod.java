package be.heh.epm.domain;

import lombok.Getter;
import lombok.Setter;

public class MailMethod implements PaymentMethod
{
    // ======== Attributes ========
    @Getter @Setter private String mail;

    // ======== Constructor ========
    public MailMethod()
    {
        // VOID
    }

    public MailMethod(String mail)
    {
        this.mail = mail;
    }

    // ======== Methods ========
    @Override
    public String toString()
    {
        return "mail : " + this.mail;
    }
}
