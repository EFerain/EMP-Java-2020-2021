package be.heh.epm.application.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import be.heh.epm.application.ports.in.Context;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.PayCheck;

public class PayDay
{
    // ======== Attributes ========
    private LocalDate date;
    private Map<Integer, PayCheck> payCheckList = new HashMap<Integer, PayCheck>();

    // ======== Constructor ========
    public PayDay(LocalDate date)
    {
        this.date = date;
    }

    // ======== Methods ========
    // Execute
    public void execute()
    {
        for(Employee employee : Context.empId.getAllEmployees())
        {
            if(employee.getPaySchedule().isDatePay(date))
            {
                PayCheck payCheck = new PayCheck(date);
                employee.payDay(payCheck);
                payCheckList.put(employee.getEmpId(), payCheck);
            }
        }
    }

    // Get payCheck
    public PayCheck getPayCheck(int empId)
    {
        return this.payCheckList.get(empId);
    }
}
