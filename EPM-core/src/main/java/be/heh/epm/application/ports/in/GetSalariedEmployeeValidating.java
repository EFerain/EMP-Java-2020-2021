package be.heh.epm.application.ports.in;

import be.heh.epm.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class GetSalariedEmployeeValidating extends SelfValidating
{
    // ======== Employee attributes ========
    // ==== empId ====
    @NotNull
    @Getter
    private int empId;

    // ==== name ====
    @NotNull
    @Getter
    private String name;

    // ==== address ====
    @NotNull
    @Getter
    private String address;

    // ==== mail ====
    @NotNull
    @Email
    @Getter
    private String mail;

    // ==== monthlySalary ====
    @NotNull
    @Getter
    private double monthlySalary;

    // ======== Constructor ========
    public GetSalariedEmployeeValidating(int empId, String name, String address, String mail, double monthlySalary)
    {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.monthlySalary = monthlySalary;

        this.validateSelf();
    }
}
