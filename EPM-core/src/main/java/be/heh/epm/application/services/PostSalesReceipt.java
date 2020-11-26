package be.heh.epm.application.services;

import java.time.LocalDate;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.SalesReceipt;
import lombok.Getter;

public class PostSalesReceipt
{
    // ======== Attributes ========
    private int empId;
    @Getter private LocalDate date;
    @Getter private double salesAmount;

    // ======== Constructor ========
    public PostSalesReceipt(int empId, LocalDate date, double salesAmount)
    {
        this.empId = empId;
        this.date = date;
        this.salesAmount = salesAmount;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        Employee employee = Context.empId.getEmployee(empId);

        if(employee != null)
        {
            if(employee.getPayClassification() instanceof CommissionClassification)
            {
                SalesReceipt salesReceipt = new SalesReceipt(date, salesAmount);

                CommissionClassification commissionClassification = (CommissionClassification)employee.getPayClassification();
                commissionClassification.addSalesReceipt(salesReceipt);

                Context.empId.saveEmployee(employee.getEmpId(), employee);
            }
            // Alternative 1 : The selected employee not commissioned
            else
            {
                throw new IllegalStateException("The employee isn't a commissionned one");
            }
        }
        // Alternative 2 : An error in the transaction structure
        else
        {
            throw new NullPointerException("___Employee is NULL___!");
        }
    }
}
