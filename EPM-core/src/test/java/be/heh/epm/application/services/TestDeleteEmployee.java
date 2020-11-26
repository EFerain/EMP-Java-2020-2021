package be.heh.epm.application.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.application.ports.out.InMemoryEmployeeGateway;
import be.heh.epm.domain.Employee;

public class TestDeleteEmployee
{
    // ======== Before ========
    @Before
    public void setUp() throws Exception
    {
        Context.empId = new InMemoryEmployeeGateway();
    }

    // ======== Test ========
    @Test
    public void addHourlyEmployee()
    {
        AddSalariedEmployee salariedEmployee = new AddSalariedEmployee(1, "Banana", "Mons", "banana@heh.be", 1000.0);
        salariedEmployee.execute();

        Employee employee = Context.empId.getEmployee(1);
        assertNotNull(employee);

        DeleteEmployee deletedEmployee = new DeleteEmployee(1);
        deletedEmployee.execute();

        employee = Context.empId.getEmployee(1);
        assertNull(employee);
    }
}
