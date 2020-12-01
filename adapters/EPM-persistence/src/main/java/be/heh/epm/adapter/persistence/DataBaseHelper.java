package be.heh.epm.adapter.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHelper
{
    // ======== Attributes ========
    // DB
    private String url = "jdbc:postgresql://localhost:5432/epmdb";
    private String user = "postgres";
    private String password = "Test123*";
    private Connection connection = null;
    private Statement stmt;

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
}
