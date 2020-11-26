package be.heh.epm.application.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.HourlyClassification;
import be.heh.epm.domain.PaymentClassification;

public class TestPostTimeCard
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    @Test
    public void testPostTimeCard()
    {
        AddHourlyEmployee hourlyEmployee = new AddHourlyEmployee(12, "Jim", "Mons", "jim@heh.be", 20.0);
        hourlyEmployee.execute();

        LocalDate date = LocalDate.of(2020, 10, 23);
        PostTimeCard postTimeCard = new PostTimeCard(12, date, 10);
        postTimeCard.execute();

        Employee employee = Context.empId.getEmployee(12);
        assertNotNull(employee);
        assertEquals("Jim", employee.getName());

        PaymentClassification p = employee.getPayClassification();
        assertTrue(p instanceof HourlyClassification);
    }
}
