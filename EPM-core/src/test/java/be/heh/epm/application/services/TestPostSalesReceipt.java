package be.heh.epm.application.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.CommissionClassification;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.PaymentClassification;

public class TestPostSalesReceipt
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    @Test
    public void testPostSalesReceipt()
    {
        AddCommissionEmployee commissionEmployee = new AddCommissionEmployee(13, "Joe", "Mons", "jim@heh.be", 100.0, 20);
        commissionEmployee.execute();

        LocalDate date = LocalDate.of(2020, 10, 23);
        PostSalesReceipt postSalesReceipt = new PostSalesReceipt(13, date, 500);
        postSalesReceipt.execute();

        Employee employee = Context.empId.getEmployee(13);
        assertNotNull(employee);
        assertEquals("Joe", employee.getName());

        PaymentClassification p = employee.getPayClassification();
        assertTrue(p instanceof CommissionClassification);
    }
}
