package be.heh.epm.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class HourlyClassification implements PaymentClassification
{
    // ======== Attributes ========
    @Getter private double hourlyRate;
    private Map<LocalDate, TimeCard> timeCardMap = new HashMap<>();

    // ======== Constructor ========
    public HourlyClassification(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    // ======== Methods ========
    // ==== for TimeCard ====
    // TimeCard 
    public TimeCard getTimeCard(LocalDate date)
    {
        return timeCardMap.get(date);
    }

    // addTimeCard
    public void addTimeCard(TimeCard timeCard)
    {
        timeCardMap.put(timeCard.getDate(),timeCard);
    }

    // ==== calculatePay ====
    @Override
    public double calculatePay(PayCheck payCheck)
    {
        double totalPay = 0;

        for(TimeCard timeCard : timeCardMap.values())
        {
            if(isInPayPeriod(timeCard, payCheck.getDate()))
            {
                totalPay += calculatePayForTimeCard(timeCard);
            }
            
        }

        return totalPay;
    }

    // ==== calculatePayForTimeCard ====
    private double calculatePayForTimeCard(TimeCard timeCard)
    {
        double hours = timeCard.getHours();
        double overtime = Math.max(0.0, hours-8.0);
        double straightTime = hours - overtime;

        return straightTime*hourlyRate + overtime*hourlyRate*1.5;
    }

    // ==== isInPayPeriod ====
    private boolean isInPayPeriod(TimeCard card, LocalDate payPeriod)
    {
        LocalDate payPeriodEndDate = payPeriod;
        LocalDate payPeriodStartDate = payPeriod.minusDays(5);

        return card.getDate().isAfter(payPeriodStartDate) || card.getDate().isEqual(payPeriodStartDate) &&
                card.getDate().isBefore(payPeriodEndDate) || card.getDate().isEqual(payPeriodEndDate);
    }

    // ==== toString ====
    @Override
    public String toString()
    {
        return "hourly";
    }
}
