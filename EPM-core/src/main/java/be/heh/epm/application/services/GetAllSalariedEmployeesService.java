package be.heh.epm.application.services;

import be.heh.epm.application.ports.in.GetAllSalariedEmployeesUseCase;
import be.heh.epm.application.ports.in.GetSalariedEmployeeUseCase;
import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.common.UseCase;
import be.heh.epm.domain.Employee;

import java.util.ArrayList;

@UseCase
public class GetAllSalariedEmployeesService implements GetAllSalariedEmployeesUseCase
{
    // ======== Attributes ========
    private EmployeePort employeePort;

    // ======== Constructor ========
    public GetAllSalariedEmployeesService(EmployeePort employeePort)
    {
        this.employeePort = employeePort;
    }

    // ======== Methods ========
    public ArrayList<Employee> execute()
    {
        return employeePort.getAllEmployee();
    }
}
