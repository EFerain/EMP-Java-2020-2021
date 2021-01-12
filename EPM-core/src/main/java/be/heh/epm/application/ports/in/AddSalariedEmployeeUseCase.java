/*
    INTERFACE
*/

package be.heh.epm.application.ports.in;

public interface AddSalariedEmployeeUseCase
{
    public void execute(SalariedEmployeeValidating EmployeeSalariedValidating);
}
