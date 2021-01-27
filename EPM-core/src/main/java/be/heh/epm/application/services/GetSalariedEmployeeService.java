package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.GetSalariedEmployeeUseCase;
import be.heh.epm.application.ports.in.SalariedEmployeeValidating;
import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.common.UseCase;
import be.heh.epm.domain.Employee;

@UseCase
public class GetSalariedEmployeeService implements GetSalariedEmployeeUseCase
{
    // ======== Attributes ========
    private EmployeePort employeePort;

    // ======== Constructor ========
    public GetSalariedEmployeeService(EmployeePort employeePort)
    {
        this.employeePort = employeePort;
    }

    // ======== Methods ========
    public void execute(SalariedEmployeeValidating salariedEmployeeValidating)
    {
        // TODO
    }
}
