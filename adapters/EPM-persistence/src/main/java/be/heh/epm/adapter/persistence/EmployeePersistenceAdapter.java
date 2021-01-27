package be.heh.epm.adapter.persistence;

import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;

import be.heh.epm.application.ports.out.EmployeePort;
import be.heh.epm.common.PersistenceAdapter;
import be.heh.epm.domain.Employee;
import be.heh.epm.domain.SalariedClassification;

@PersistenceAdapter
public class EmployeePersistenceAdapter implements EmployeePort
{
    private String url = "jdbc:postgresql://127.0.0.1:5432/epmGr6";
    private String user = "postgres";
    private String password = "Test123*";
    private Connection connection = null;
    private Statement stmt;

    // ======== Constructeur ========
    public EmployeePersistenceAdapter()
    {
        Connect();
    }

    // ======== Connect ========
    private Connection Connect()
    {
        try
        {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connect to the database successfully");

            stmt = this.connection.createStatement();
            String sql = "CREATE TABLE employees" +
                    "(empId SERIAL PRIMARY KEY ," +
                    "name TEXT NOT NULL UNIQUE, " +
                    "address CHAR(50), " +
                    "mail VARCHAR)";
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(SQLException e)
        {
            System.out.println("Unable to connect to database\n" + e.getMessage());
        }

        return this.connection;
    }

    // ======== Methods ========
    // ==== Get ====
    // Get employee
    @Override
    public Employee getEmployee(int empId)
    {
        Employee employee = new Employee();

        String sql = "SELECT * FROM public.employees WHERE empid = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                employee.setEmpId(rs.getInt("empid"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setMail(rs.getString("mail"));
            }
            else
            {
                System.out.println("Enabled to find the user with id : " + empId);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Ou ici ?\n" + e.getMessage());
        }

        return employee;
    }

    // Get ALL employees
    @Override
    public ArrayList<Employee> getAllEmployee()
    {
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM public.employees";

        try (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Employee employee = new Employee();

                employee.setEmpId(rs.getInt("empid"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setMail(rs.getString("mail"));

                employees.add(employee);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Ou ici ?\n" + e.getMessage());
        }

        return employees;
    }

    // ==== Save ====
    @Override
    public Employee save(Employee employee)
    {
        String sql = "INSERT INTO public.employees(name, address, mail) VALUES (?, ?, ?)";

        try (PreparedStatement ps = this.connection.prepareStatement(sql, new String[] { "empid" }))
        {
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getAddress());
            ps.setString(3, employee.getMail());

            int id = ps.executeUpdate();

            if(id > 0)
            {
                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if(rs.next())
                    {
                        employee.setEmpId(rs.getInt("empid"));
                    }
                }
                catch (SQLException e)
                {
                    System.out.println("Error 1 :\n" + e.getMessage());
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error 2 :\n" + e.getMessage());
        }

        return employee;
    }

    // ==== Delete ====
    @Override
    public void deleteEmployee(int empId)
    {
        //Check if a user have this id
        String sql = "SELECT * FROM public.employees WHERE empid = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ps.setInt(1, empId);

            ResultSet rs = ps.executeQuery();

            if(!rs.next())
            {
                System.out.println("Enabled to find the employee with id : " + empId);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error :\n" + e.getMessage());
        }

        sql = "DELETE FROM public.employees WHERE empid = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Error :\n" + se);
        }
    }
}