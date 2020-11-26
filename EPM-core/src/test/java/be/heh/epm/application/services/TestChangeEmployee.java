package be.heh.epm.application.services;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.DirectDepositMethod;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.MailMethod;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PayCheck;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentMethod;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;
import be.heh.epm.domain.TwoWeekPaySchedule;
import be.heh.epm.domain.WeeklyPaymentSchedule;

public class TestChangeEmployee
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    // ==== Name ====
    @Test
    public void chgEmpName()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(1, "Test", "Mons", "test@gmail.com", 1000.0);
        salariedEmployee.execute();

        ChgEmpName changeEmpName = new ChgEmpName(1, "Banana");
        changeEmpName.execute();

        Employee e = Context.empId.getEmployee(1);
        Assert.assertNotNull(e);
        Assert.assertEquals("Banana", e.getName());
    }

    // ==== Address ====
    @Test
    public void chgEmpAddress()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(2, "Test", "Mons", "test@gmail.com", 1000.0);
        salariedEmployee.execute();

        ChgEmpAddress changeEmpAddress = new ChgEmpAddress(2, "Jurbise");
        changeEmpAddress.execute();

        Employee e = Context.empId.getEmployee(2);
        Assert.assertNotNull(e);
        Assert.assertEquals("Jurbise", e.getAddress());
    }

    // ==== Classification ====
    // Hourly
    @Test
    public void chgEmpToHourly()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(3, "Test", "Mons", "test@gmail.com", 1000.0);
        salariedEmployee.execute();

        // Salaried employee 1000/month -> Hourly employee 20/hour
        ChgEmpToHourly changeEmpToHourly = new ChgEmpToHourly(3, 20);
        changeEmpToHourly.execute();

        // Post time card on 23/11/2020 with 8 hours of work
        LocalDate date = LocalDate.of(2020, 11, 23);
        PostTimeCard timeCard = new PostTimeCard(3, date, 8);
        timeCard.execute();

        Employee e = Context.empId.getEmployee(3);
        Assert.assertNotNull(3);
        
        PaymentClassification pc = e.getPayClassification();
        Assert.assertTrue(pc instanceof HourlyClassification);
        
        PaymentSchedule ps = e.getPaySchedule();
        Assert.assertTrue(ps instanceof WeeklyPaymentSchedule);
        
        // Payday on 27/11/2020
        LocalDate PayDay = LocalDate.of(2020, 11, 27);
        PayCheck PayC = new PayCheck(PayDay);
        e.payDay(PayC);

        double pay = PayC.getSalary();
        Assert.assertEquals(160.0, pay, 0.01);
    }

    // Salaried
    @Test
    public void chgEmpToSalaried()
    {
        AddCommissionEmployee commissionEmployee = new AddCommissionEmployee(4, "Test", "Mons", "test@gmail.com", 500.0, 20.0);
        commissionEmployee.execute();

        // Commission employee paid 500 with commission rate of 20% -> Salaried employee 1000/month
        ChgEmpToSalaried changeEmpToSalaried = new ChgEmpToSalaried(4, 1000.0);
        changeEmpToSalaried.execute();

        Employee e = Context.empId.getEmployee(4);
        Assert.assertNotNull(4);
        
        PaymentClassification pc = e.getPayClassification();
        Assert.assertTrue(pc instanceof SalariedClassification);
        
        PaymentSchedule ps = e.getPaySchedule();
        Assert.assertTrue(ps instanceof MonthlyPaymentSchedule);
        
        // Payday on 27/11/2020
        LocalDate PayDay = LocalDate.of(2020, 11, 30);
        PayCheck PayC = new PayCheck(PayDay);
        e.payDay(PayC);

        double pay = PayC.getSalary();
        Assert.assertEquals(1000.0, pay, 0.01);
    }

    // Commission
    @Test
    public void chgEmpToCommission()
    {
        AddHourlyEmployee hourlyEmployee = new AddHourlyEmployee(5, "Test", "Mons", "test@gmail.com", 20.0);
        hourlyEmployee.execute();

        // Hourly employee paid 20/hour -> Commission employee paid 500 with commission rate of 20%
        ChgEmpToCommission changeEmpToCommission = new ChgEmpToCommission(5, 500.0, 20.0);
        changeEmpToCommission.execute();

        // Post sales receipt on 23/11/2020 with sales amount of 1000
        LocalDate date = LocalDate.of(2020, 11, 23);
        PostSalesReceipt salesReceipt = new PostSalesReceipt(5, date, 1000.0);
        salesReceipt.execute();

        Employee e = Context.empId.getEmployee(5);
        Assert.assertNotNull(e);

        PaymentClassification pc = e.getPayClassification();
        Assert.assertTrue(pc instanceof CommissionClassification);

        PaymentSchedule ps = e.getPaySchedule();
        Assert.assertTrue(ps instanceof TwoWeekPaySchedule);

        // Payday on 27/11/2020
        LocalDate PayDay = LocalDate.of(2020, 11, 27);
        PayCheck PayC = new PayCheck(PayDay);
        e.payDay(PayC);

        double pay = PayC.getSalary();
        Assert.assertEquals(700.0, pay, 0.01);
    }

    // ==== Method ====
    // Direct Deposit
    @Test
    public void chgEmpMethod()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(6, "Test", "Mons", "test@gmail.com", 1000.0);
        salariedEmployee.execute();

        // Method : Direct Deposit
        ChgEmpToDirectDepositMethod changeEmpToDirectDeposit = new ChgEmpToDirectDepositMethod(6, "ING", "be80-4444-444");
        changeEmpToDirectDeposit.execute();

        Employee e = Context.empId.getEmployee(6);
        Assert.assertNotNull(e);

        PaymentMethod pc = e.getPayMethod();
        Assert.assertTrue(pc instanceof DirectDepositMethod);
        Assert.assertEquals("direct deposit into ING : be80-4444-444", pc.toString());

        // Method : Direct Deposit -> Mail method
        ChgEmpToMailMethod changeEmpToMailMethod = new ChgEmpToMailMethod(6);
        changeEmpToMailMethod.execute();

        e = Context.empId.getEmployee(6);
        Assert.assertNotNull(e);

        PaymentMethod pc2 = e.getPayMethod();
        Assert.assertTrue(pc2 instanceof MailMethod);
        Assert.assertEquals("mail : test@gmail.com", pc2.toString());
    }
}
