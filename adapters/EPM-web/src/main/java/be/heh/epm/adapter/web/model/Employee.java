package be.heh.epm.adapter.web.model;


public class Employee
{
    private int empId;
    private String name;
    private String mail;

    public Employee()
    {
        // VOID
    }

    public Employee(int id, String name, String mail)
    {
        this.empId = id;
        this.name = name;
        this.mail = mail;
    }

    public int getId()
    {
        return empId;
    }

    public void setId(int id)
    {
        this.empId = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    @Override
    public String toString()
    {
        return "Employee{"+
                "id=" + empId +
                ", name='"+ name + '\'' +
                ", mail=" + mail + '}';
    }
}

