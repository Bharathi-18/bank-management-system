package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class Verify {
        public int option;
    String custID,pass;
    Statement s = null;
    ResultSet rs = null;
    Verify()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID : ");
        custID = sc.nextLine();

        try
        {   
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
            String stmnt = "select * from customerdetails where cust_id = "+custID;
            s =con.createStatement(); 
            rs = s.executeQuery(stmnt);
            rs.next(); 
            String res = rs.getString(9);
            System.out.print("Enter password : ");
            pass = sc.nextLine();
            int i = 1;
            while(i<=2)
            {
                if(res.equals(pass))
                {
                    System.out.print("Edit account details\n\n1. Change phone number\n2. Change email address\n3. Change Address\n4. Change password\n\nEnter your choice : ");
                    option = sc.nextInt();
                    System.out.println();
                    break;
                }
                System.out.println("Password is wrong");
                System.out.println("Attempt left : "+(3-i));
                System.out.print("Enter password : ");
                pass = sc.nextLine();
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }
    public void phone(long phne){
        try
        {
            String upd = "update customerdetails set cust_phne = "+phne+" where cust_id = "+custID;
            s.executeUpdate(upd);
        }
        catch(Exception e)
        {
            System.out.println("Exception");
        }
    }
    public void password(String pswd){
        try
        {
            String upd = "update customerdetails set password = '"+pswd+"' where cust_id = "+custID;
            s.executeUpdate(upd);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    public void email(String mail){
        try
        {
            String upd = "update customerdetails set email = '"+mail+"' where cust_id = "+custID;
            s.executeUpdate(upd);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
    public void address(String adrs){
        try
        {
            String upd = "update customerdetails set cust_add = '"+adrs+"' where cust_id = "+custID;
            s.executeUpdate(upd);
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
