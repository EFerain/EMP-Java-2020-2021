package be.heh.epm.application.services;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.heh.epm.domain.Employee;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PayCheck;
import be.heh.epm.domain.PaymentMethod;
import be.heh.epm.domain.PaymentSchedule;

public class TestAddEmployee
{
    private Employee employee;
    private PayCheck pc;

    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        employee = new Employee(100, "toto", "av maistriau", "toto@gmail.com");
        LocalDate payDate = LocalDate.of(2019, 10, 2);
        pc = new PayCheck(payDate);
    }

    // ======== Test ========
    // ==== Add Hourly Employee (Hourly + Weekly) ====
    @Test
    public void addHourlyEmployee()
    {
        
    }

    // ==== Add Salaried Employee (Salaried + Monthly) ====
    @Test
    public void addSalariedEmployee()
    {
        
    }

    // ==== Add Commission Employee (Commission + TwoWeek) ====
    @Test
    public void addCommissionEmployee()
    {
        
    }
}
