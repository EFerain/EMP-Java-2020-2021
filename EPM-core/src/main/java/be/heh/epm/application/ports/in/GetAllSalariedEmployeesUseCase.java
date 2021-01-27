/*
    INTERFACE
*/

package be.heh.epm.application.ports.in;

import be.heh.epm.domain.Employee;

import java.util.ArrayList;

public interface GetAllSalariedEmployeesUseCase
{
    public ArrayList<Employee> execute();
}
