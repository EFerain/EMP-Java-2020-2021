package be.heh.epm.adapter.web.model;


public class Employee {
    private int id;
    private String name;
    private String mail;

    public Employee(int id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Employee{"+
                "id=" + id +
                ", name='"+ name + '\'' +
                ", mail=" + mail + '}';
    }
}

