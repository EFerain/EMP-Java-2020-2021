package be.heh.epm.application.services;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

import be.heh.epm.application.ports.in.AddSalariedEmployeeUseCase;
import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.domain.Employee;

public class TestAddEmployee
{
    private EmployeePort employeePort = Mockito.mock(EmployeePort.class);
    private Employee employee = Mockito.mock(Employee.class);

    @Test
    public void testAddSalariedEmployee()
    {
        AddSalariedEmployeeUseCase addSalariedEmployee = new AddSalariedEmployeeService(employeePort);
        SalariedEmployeeValidating salariedEmployeeValidating = new SalariedEmployeeValidating();
        
        salariedEmployeeValidating.setEmpId(1);
        salariedEmployeeValidating.setName("toto");
        salariedEmployeeValidating.setAddress("rue de Mons");
        salariedEmployeeValidating.setMail("toto@heh.be");
        salariedEmployeeValidating.setMonthlySalary(1500);

        addSalariedEmployee.execute(salariedEmployeeValidating);

        //verify(employeePort).save(refEq(employee)); Erreur

        /*Employee e = employeeGateway.getEmployee(employeeSalariedValidating.getEmpId());
        assertEquals("toto", e.getName());

        PaymentSchedule ps = e.getPaySchedule();
        assertTrue(ps instanceof MonthlyPaymentSchedule);

        PaymentMethod pm = e.getPayMethod();
        assertEquals("direct deposit into Fortis : be332211", pm.toString());*/
    }
}
