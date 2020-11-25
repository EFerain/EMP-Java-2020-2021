package be.heh.epm.application.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;
import be.heh.epm.domain.TwoWeekPaySchedule;
import be.heh.epm.domain.WeeklyPaymentSchedule;

public class TestAddEmployee
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    // ==== Add Hourly Employee (Hourly + Weekly) ====
    @Test
    public void addHourlyEmployee()
    {
        AddHourlyEmployee hourlyEmployee = new AddHourlyEmployee(1, "Peter", "Mons", "peter@gmail.com", 20.0);
        hourlyEmployee.execute();

        Employee e = Context.empId.getEmployee(1);
        assertEquals("Peter", e.getName());

        // Classification
        PaymentClassification pc = e.getPayClassification();
        assertTrue(pc instanceof HourlyClassification);

        // Schedule
        PaymentSchedule ps = e.getPaySchedule();
        assertTrue(ps instanceof WeeklyPaymentSchedule);
    }

    // ==== Add Salaried Employee (Salaried + Monthly) ====
    @Test
    public void addSalariedEmployee()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(2, "Bob", "Home", "bob@gmail.com", 1000.0);
        salariedEmployee.execute();

        Employee e = Context.empId.getEmployee(2);
        assertEquals("Bob", e.getName());

        // Classification
        PaymentClassification pc = e.getPayClassification();
        assertTrue(pc instanceof SalariedClassification);

        // Schedule
        PaymentSchedule ps = e.getPaySchedule();
        assertTrue(ps instanceof MonthlyPaymentSchedule);
    }

    // ==== Add Commission Employee (Commission + TwoWeek) ====
    @Test
    public void addCommissionEmployee()
    {
        AddCommissionEmployee t = new AddCommissionEmployee(3, "Lisa", "Londres", "lisa@gmail.uk", 250.0, 20);
        t.execute();

        Employee e = Context.empId.getEmployee(3);
        assertEquals("Lisa", e.getName());

        // Classification
        PaymentClassification pc = e.getPayClassification();
        assertTrue(pc instanceof CommissionClassification);

        // Schedule
        PaymentSchedule ps = e.getPaySchedule();
        assertTrue(ps instanceof TwoWeekPaySchedule);
    }
}
