/*
    INTERFACE
*/

package be.heh.epm.application.ports.in;

import be.heh.epm.domain.Employee;

public interface GetSalariedEmployeeUseCase
{
    public Employee execute(Integer EmployeeSalariedValidatingId);
}
