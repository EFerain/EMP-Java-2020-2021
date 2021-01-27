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
        return null;
    }

    /*
    @Override
    public Employee getEmployee(int empId)
    {
        try
        {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE EMPID = ?",
                    new Object[]{empId},
                    (rs, rowNum) -> {
                        Employee e = new Employee();
                        e.setEmpId(rs.getInt("EMPID"));
                        e.setName(rs.getString("NAME"));
                        e.setAddress(rs.getString("ADDRESS"));
                        e.setMail(rs.getString("MAIL"));

                        return e;
                    });
            logger.info("Recovery of the employee by id {} in the database", empId);

            return employee;
        }
        catch (EmptyResultDataAccessException e)
        {
            logger.error("Employee with id {} was not found", empId);

            return null;
        }
    }
    */

    // Get ALL employees
    @Override
    public ArrayList<Employee> getAllEmployee()
    {
        return null;
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
                    System.out.println("Là ?\n" + e.getMessage());
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Ou ici ?\n" + e.getMessage());
        }

        return employee;
    }

    // ==== Delete ====
    @Override
    public void deleteEmployee(int empId)
    {
        String sql = "DELETE FROM public.employees WHERE empid = ?";

        try (PreparedStatement ps = this.connection.prepareStatement(sql))
        {
            ps.setInt(1, empId);
            ps.executeUpdate();
        }
        catch (SQLException se)
        {
            System.out.println("Failed to delete the employee\n" + se);
        }
    }
}