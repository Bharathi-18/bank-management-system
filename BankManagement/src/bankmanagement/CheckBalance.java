package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class CheckBalance {
    void checkBalance()
    {
        String customerID,pass;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID : ");
        customerID = sc.nextLine();

        try
        {   
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
            PreparedStatement p = null;
            String stmnt = "select * from customerdetails where cust_id = "+customerID;
            Statement s =con.createStatement(); 
            ResultSet rs = s.executeQuery(stmnt);
            rs.next();
            String res = rs.getString(9);
            System.out.print("Enter password : ");
            pass = sc.nextLine();
            int i = 1;
            while(i<=2)
            {
                if(res.equals(pass))
                {
                    System.out.println("Your Balance : "+rs.getString("cust_balance"));
                    break;
                }
                System.out.println("Password is wrong");
                System.out.println("Attempt left : "+(3-i));
                System.out.print("Enter password : ");
                pass = sc.nextLine();
                i++;
            }
            if(i==3)
                System.out.println("Login Failure !!!!!!!!\nYour password credentials is invalid");
        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
