package be.heh.epm.application.services;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.PayCheck;

public class TestPayDay
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    // ==== Salaried employee ====
    // OK
    @Test
    public void salariedPayDay()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(1, "Bob", "Mons", "bob@gmail.com", 1000.0);
        salariedEmployee.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 30);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(1);
        Assert.assertNotNull(payCheck);
        Assert.assertEquals(datePayDay, payCheck.getDate());
        Assert.assertEquals(1000.0, payCheck.getSalary(), 0.01);
    }

    // Wrong
    @Test
    public void wrongSalariedPayDay()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(2, "Bob", "Mons", "bob@gmail.com", 1000.0);
        salariedEmployee.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 27);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(2);
        Assert.assertNull(payCheck);
    }

    // ==== Hourly employee ====
    // OK
    @Test
    public void hourlyPayDay()
    {
        LocalDate date = LocalDate.of(2020, 11, 23);

        AddHourlyEmployee hourlyEmployee = new AddHourlyEmployee(3, "Bob", "Mons", "bob@gmail.com", 20.0);
        hourlyEmployee.execute();

        PostTimeCard timeCard = new PostTimeCard(3, date, 8);
        timeCard.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 27);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(3);
        Assert.assertNotNull(payCheck);
        Assert.assertEquals(datePayDay, payCheck.getDate());
        Assert.assertEquals(160.0, payCheck.getSalary(), 0.01);
    }

    // Wrong
    @Test
    public void wrongHourlyPayDay()
    {
        LocalDate date = LocalDate.of(2020, 11, 23);

        AddHourlyEmployee hourlyEmployee = new AddHourlyEmployee(4, "Bob", "Mons", "bob@gmail.com", 20.0);
        hourlyEmployee.execute();

        PostTimeCard timeCard = new PostTimeCard(4, date, 8);
        timeCard.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 24);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(4);
        Assert.assertNull(payCheck);
    }

    // ==== Commission employee ====
    // OK
    @Test
    public void commissionPayDay()
    {
        LocalDate date = LocalDate.of(2020, 11, 19);

        AddCommissionEmployee commissionEmployee = new AddCommissionEmployee(5, "Ben", "Mons", "ben@heh.be", 500.0, 20.0);
        commissionEmployee.execute();

        PostSalesReceipt salesReceipt = new PostSalesReceipt(5, date, 1000.0);
        salesReceipt.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 20);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(5);
        Assert.assertNotNull(payCheck);
        Assert.assertEquals(datePayDay, payCheck.getDate());
        Assert.assertEquals(700.0, payCheck.getSalary(), 0.01);
    }

    // Wrong
    @Test
    public void wrongCommissionPayDay()
    {
        LocalDate date = LocalDate.of(2020, 11, 19);

        AddCommissionEmployee commissionEmployee = new AddCommissionEmployee(6, "Ben", "Mons", "ben@heh.be", 500.0, 20.0);
        commissionEmployee.execute();

        PostSalesReceipt salesReceipt = new PostSalesReceipt(6, date, 1000.0);
        salesReceipt.execute();

        LocalDate datePayDay = LocalDate.of(2020, 11, 27);
        PayDay payDay = new PayDay(datePayDay);
        payDay.execute();

        PayCheck payCheck = payDay.getPayCheck(6);
        Assert.assertNull(payCheck);
    }
}
