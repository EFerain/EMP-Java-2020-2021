/*
    Salaried + Monthly
*/

package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.AddSalariedEmployeeUseCase;
import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.domain.DirectDepositMethod;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.MonthlyPaymentSchedule;
import be.heh.epm.domain.PaymentClassification;
import be.heh.epm.domain.PaymentMethod;
import be.heh.epm.domain.PaymentSchedule;
import be.heh.epm.domain.SalariedClassification;

public class AddSalariedEmployeeService implements AddSalariedEmployeeUseCase
{
    // ======== Attributes ========
    private EmployeePort employeePort;

    // ======== Constructor ========
    public AddSalariedEmployeeService(EmployeePort employeePort)
    {
        this.employeePort = employeePort;
    }

    // ======== Methods ========
    public void execute(SalariedEmployeeValidating salariedEmployeeValidating)
    {
        PaymentClassification pc = new SalariedClassification(salariedEmployeeValidating.getMonthlySalary());
        PaymentSchedule ps = new MonthlyPaymentSchedule();
        PaymentMethod pm = new DirectDepositMethod("Fortis","be332211");

        Employee employee = new Employee(salariedEmployeeValidating.getName(), salariedEmployeeValidating.getAddress(), salariedEmployeeValidating.getMail());
        employee.setPayClassification(pc);
        employee.setPaySchedule(ps);
        employee.setPayMethod(pm);

        Employee salariedEmployee = employeePort.save(employee);
    }
}
