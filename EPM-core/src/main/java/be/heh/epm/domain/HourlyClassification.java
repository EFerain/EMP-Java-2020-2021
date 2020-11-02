package be.heh.epm.domain;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

public class HourlyClassification implements PaymentClassification
{
    // ======== Attributes ========
    @Getter @Setter private double amount;
    private List<TimeCard> listTimeCards = new ArrayList<TimeCard>();

    // ======== Constructor ========
    public HourlyClassification()
    {
        // VOID
    }

    public HourlyClassification(double amount)
    {
        this.amount = amount;
    }

    // ======== Methods ========
    // ==== calculatePay ====
    @Override
    public double calculatePay(PayCheck pc)
    {
        double money = 0;

        if(!this.listTimeCards.isEmpty())
        {
            for(TimeCard timeCard : this.getWeek())
            {
                // Worked less than 8 hours
                money += timeCard.getTime() * this.amount;

                // Extra time > 8
                if(timeCard.getTime() > 8)
                {
                    money += (timeCard.getTime() - 8) * 0.5 * this.amount;
                }
            }
        }

        return money;
    }

    // ==== addTimeCard ====
    public void addTimeCard(TimeCard tc)
    {
        this.listTimeCards.add(tc);
    }

    // ======== Classe interne ========
    private List<TimeCard> getWeek()
    {
        List<TimeCard> listWeeklyTimecard = new ArrayList<TimeCard>();

        LocalDate latestDate = this.listTimeCards.get(0).getDate();

        //Last time Employee worked
        for(TimeCard timeCard : this.listTimeCards)
        {
            if(latestDate.isBefore(timeCard.getDate()))
            {
                latestDate = timeCard.getDate();
            }
        }

        for(TimeCard timeCard : this.listTimeCards)
        {
            TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

            if(latestDate.getYear() == timeCard.getDate().getYear() && latestDate.get(woy) == timeCard.getDate().get(woy))
            {
                listWeeklyTimecard.add(timeCard);
            }
        }

        return listWeeklyTimecard;
    }
}
