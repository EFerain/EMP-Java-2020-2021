/*
    INTERFACE
*/

package be.heh.epm.domain;

public interface PaymentMethod
{
    // ======== Methods ========
    public void pay(PayCheck payCheck);
}
