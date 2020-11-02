package be.heh.epm.domain;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommissionClassification implements PaymentClassification
{
    // ======== Attributes ========
    private double amount;
    private List<SalesReceipt> listSalesReceipt = new ArrayList<SalesReceipt>();

    // ======== Constructor ========
    public CommissionClassification()
    {
        // VOID
    }

    public CommissionClassification(double amount)
    {
        this.amount = amount;
    }

    // ======== Methods ========
    @Override
    public double calculatePay(PayCheck pc)
    {
        double money = this.amount;
        LocalDate latestDate;

        if(!this.listSalesReceipt.isEmpty())
        {
            latestDate = this.listSalesReceipt.get(0).getDate();

            //Last time Employee worked
            for(SalesReceipt salesReceipt : this.listSalesReceipt)
            {
                if(latestDate.isBefore(salesReceipt.getDate()))
                {
                    latestDate = salesReceipt.getDate();
                }
            }

            for(SalesReceipt salesReceipt : this.listSalesReceipt)
            {
                TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

                if(latestDate.getYear() == salesReceipt.getDate().getYear() && 
                ((latestDate.get(woy) % 2 == 1 && latestDate.get(woy) - salesReceipt.getDate().get(woy) < 2)
                || (latestDate.get(woy) % 2 == 0 && latestDate.get(woy) == salesReceipt.getDate().get(woy))))
                {
                    money += salesReceipt.getSalesAmount();
                }
            }
        }

        return money;
    }

    public void addSalesReceipt(SalesReceipt sr)
    {
        this.listSalesReceipt.add(sr);
    }
}
