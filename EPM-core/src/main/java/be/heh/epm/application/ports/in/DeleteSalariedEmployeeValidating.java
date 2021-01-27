package be.heh.epm.application.ports.in;

import be.heh.epm.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class DeleteSalariedEmployeeValidating extends SelfValidating
{
    // ======== Employee attributes ========
    // ==== empId ====
    @NotNull
    @Getter
    private int empId;

    // ======== Constructor ========
    public DeleteSalariedEmployeeValidating(int empId)
    {
        this.empId = empId;

        this.validateSelf();
    }
}
