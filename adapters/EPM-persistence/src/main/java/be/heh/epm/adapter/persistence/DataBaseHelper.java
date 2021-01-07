package be.heh.epm.adapter.persistence;

import be.heh.epm.domain.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseHelper
{
    // ======== Attributes ========
    // DB
    private String url = "jdbc:postgresql://localhost:5432/epmdb";
    private String user = "postgres";
    private String password = "Test123*";
    private Connection connection = null;

    // ======== Constructor ========
    public DataBaseHelper()
    {
        Connect();
    }

    // ======== CONNECTION ========
    private Connection Connect()
    {
        try
        {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connected to database successfully");
        }
        catch (SQLException e)
        {
            System.out.println("Cannot connect to database\n" + e.getMessage());
        }
        
        return this.connection;
    }

    // ======== Methods ========
    // ==== Get Employee ====
    public Employee getEmployee(Employee employee)
    {
        String sql = "SELECT * FROM Employee WHERE name = ?";

        try(PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ps.setString(1, employee.getName());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                employee.setEmpId(rs.getInt("empId"));
                employee.setName(rs.getString("name"));
            }

            if(employee.getName() == null)
            {
                System.out.println("User not found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return employee;
    }

    // ==== Get All Employees ====
    public ArrayList<Employee> getAllEmployees()
    {
        ArrayList<Employee> listEmployees = new ArrayList<>();

        String sql = "SELECT * FROM employees";

        try
        (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            Employee employee = null;
            ResultSet rs = ps.executeQuery();

            if(!rs.next())
            {
                return null;
            }
            else
            {
                do
                {
                    listEmployees.add(employee = new Employee(rs.getInt("empId"), rs.getString("name"), rs.getString("address"), rs.getString("mail")));
                }
                while(rs.next());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return listEmployees;
    }

    /*
    // ==== Add Employee ====
    public Employee addEmployee(Employee employee)
    {
        String sql = "INSERT INTO epmschema.Employee(name, address, mail) VALUES (?, ?, ?)";

        try(PreparedStatement ps = this.connection.prepareStatement(sql, new String[] { "empId" }))
        {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getMail());

            int empId = ps.executeUpdate();

            if (empId > 0)
            {
                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if (rs.next())
                    {
                        employee.setEmpId(rs.getInt("empId"));
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return employee;
    }
    */

    // ==== Delete Employee ====

    // ==== Update Employee ====
}

