package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.DeleteSalariedEmployeeUseCase;
import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.common.UseCase;
import be.heh.epm.domain.Employee;

@UseCase
public class DeleteSalariedEmployeeService implements DeleteSalariedEmployeeUseCase
{
    // ======== Attributes ========
    private EmployeePort employeePort;

    // ======== Constructor ========
    public DeleteSalariedEmployeeService(EmployeePort employeePort)
    {
        this.employeePort = employeePort;
    }

    // ======== Methods ========
    public void execute(Integer salariedEmployeeValidatingId)
    {
        employeePort.deleteEmployee(salariedEmployeeValidatingId);
    }
}
