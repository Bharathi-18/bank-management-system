package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class DataBaseCon {
    void connection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
