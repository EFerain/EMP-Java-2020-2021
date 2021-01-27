/*
    INTERFACE
*/

package be.heh.epm.application.ports.in;

public interface GetSalariedEmployeeUseCase
{
    public void execute(SalariedEmployeeValidating EmployeeSalariedValidating);
}
