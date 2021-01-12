package be.heh.epm.application.ports.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class SalariedEmployeeValidating
{
    // ======== Employee attributes ========
    // ==== empId ====
    //@NotNull
    @Getter
    @Setter
    private int empId;

    // ==== name  ====
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String name;

    // ==== address ====
    @NotNull
    @NotEmpty
    @Getter
    @Setter
    private String address;

    // ==== mail ====
    @NotNull
    @NotEmpty
    @Email
    @Getter
    @Setter
    private String mail;

    // ==== monthlySalary ====
    @NotNull
    @Getter
    @Setter
    private double monthlySalary;
}
