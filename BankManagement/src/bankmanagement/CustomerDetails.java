package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class CustomerDetails {
    
    void createUser()
    {
        String custID,custName,custAccType,custAdd,custGender,custEmail,custBalance,custPhne,pswd,pswd1;
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------NEW USER REGISTRATION------------------------------------");
        System.out.print("Enter customer ID : ");
        custID = sc.nextLine();
        System.out.print("Enter customer Name : ");
        custName = sc.nextLine();
        System.out.print("Enter customer Account Type : ");
        custAccType = sc.nextLine();
        System.out.print("Enter customer Balance (Minimum Balance - 1000) : ");
        custBalance = sc.nextLine();
        System.out.print("Enter customer Phone Number : ");
        custPhne = sc.nextLine();
        System.out.print("Enter customer Address : ");
        custAdd = sc.nextLine();
        System.out.print("Enter customer Gender : ");
        custGender = sc.nextLine();
        System.out.print("Enter customer Email : ");
        custEmail = sc.nextLine();
        System.out.print("Create password : ");
        pswd = sc.nextLine();
        System.out.print("Retype password : ");
        pswd1 = sc.nextLine();
        if(pswd.equals(pswd1))
        {
            try
            {   
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
                PreparedStatement p = con.prepareStatement("insert into customerdetails values(?,?,?,?,?,?,?,?,?)");
                p.setString(1,custID);
                p.setString(2,custName);
                p.setString(3,custAccType);
                p.setString(4,custBalance);
                p.setString(5,custPhne);
                p.setString(6,custAdd);
                p.setString(7,custGender);
                p.setString(8,custEmail);
                p.setString(9, pswd);
                p.executeUpdate();
                String tbleName = "trns"+custID;
                String tble = "create table " + tbleName +"(custid varchar(15),cust_name varchar(50),type varchar(10),amount varchar(15),balance varchar(20),description varchar(100),mode varchar(20) ,transactionID varchar(15))";
                p = con.prepareStatement(tble);
                p.executeUpdate();
            }
            catch(ClassNotFoundException | SQLException e)
            {
                System.out.println(e);
            }
        }
        else
        {
            System.out.println("Password Mismatch");
        }
    }
    
}
