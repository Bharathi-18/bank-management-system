package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class Deposit {
    
    void deposit()
    {
        String customerID,pass,res,tempBalance,upd,trnsname,amount;
        double amnt,balance;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID : ");
        customerID = sc.nextLine();
        try
        {   
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
            String stmnt = "select * from customerdetails where cust_id = "+customerID;
            Statement s =con.createStatement(); 
            ResultSet rs = s.executeQuery(stmnt);
            rs.next();
            res = rs.getString(9);
            tempBalance = rs.getString("cust_balance");
            String custName = rs.getString("cust_name");
            balance = Double.parseDouble(tempBalance);
            String tbleName = "trns"+customerID;
            System.out.print("Enter password : ");
            pass = sc.nextLine();
            int i = 1;
            while(i<=2)
            {
                if(res.equals(pass))
                {
                    System.out.print("Enter amount to deposit : ");
                    amount = sc.nextLine();
                    amnt = Double.parseDouble(amount);
                    System.out.print("Description(if any) : ");
                    String description = sc.nextLine();
                    balance += amnt;
                    String blnce = ""+balance;
                    upd = "update customerdetails set cust_balance = "+balance+" where cust_id = "+customerID;
                    s.executeUpdate(upd);
                    System.out.println("Your Balance : "+balance);
                    PreparedStatement ps = con.prepareStatement("insert into "+tbleName+" values(?,?,?,?,?,?,?,?)");
                    ps.setString(1,customerID);
                    ps.setString(2,custName);
                    ps.setString(3,"Credit");
                    ps.setString(4,amount);
                    ps.setString(5,blnce);
                    ps.setString(6,description);
                    ps.setString(7,"Deposit");
                    ps.setString(8,customerID);
                    ps.executeUpdate();
                    break;
                }
                System.out.println("Password is wrong");
                System.out.println("Attempt left : "+(3-i));
                System.out.print("Enter password : ");
                pass = sc.nextLine();
                i++;
            }

        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println(e);
        }
    }
    
}
