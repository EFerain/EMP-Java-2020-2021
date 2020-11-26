package be.heh.epm.application.services;

import java.time.LocalDate;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.TimeCard;
import lombok.Getter;

public class PostTimeCard
{
    // ======== Attributes ========
    private int empId;
    @Getter private LocalDate date;
    @Getter private double hours;

    // ======== Constructor ========
    public PostTimeCard(int empId, LocalDate date, double hours)
    {
        this.empId = empId;
        this.date = date;
        this.hours = hours;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            if(employee.getPayClassification() instanceof HourlyClassification)
            {
                TimeCard timeCard = new TimeCard(date, hours);

                HourlyClassification hourlyClassification = (HourlyClassification)employee.getPayClassification();
                hourlyClassification.addTimeCard(timeCard);

                Context.empId.saveEmployee(employee.getEmpId(), employee);
            }
            // Alternative 1 : The selected employee is not hourly
            else
            {
                throw new IllegalStateException("The employee isn't paid per hour");
            }
        }
        // Alternative 2 : An error in the transaction structure
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
