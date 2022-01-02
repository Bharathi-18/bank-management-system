package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class TransactionReport {

    public void printData() {
        String custID,pass;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID : ");
        custID = sc.nextLine();
        try
        {   
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
            String sendstmnt = "select * from customerdetails where cust_id = "+custID;
            Statement s =con.createStatement(); 
            ResultSet rs = s.executeQuery(sendstmnt);
            rs.next(); 
            String res = rs.getString(9);
            System.out.print("Enter password : ");
            pass = sc.nextLine();
            int i = 1;
            while(i<=2)
            {
                if(res.equals(pass))
                {
                    String transactionReportID = "trns"+custID;
                    String report = "select * from "+transactionReportID;
                    rs = s.executeQuery(report);
                    System.out.println("\n\nTransaction Report \n");
                    System.out.println("------------------------------------------------------------------------------------");

                    int transactionCount = 1;
                    while(rs.next())
                    {
                        System.out.println("Transaction Count : "+transactionCount);
                        System.out.println("Transaction Type : "+rs.getString("type"));
                        System.out.println("Transaction amount : "+rs.getString("amount"));    
                        System.out.println("Balance : "+rs.getString("balance"));    
                        System.out.println("Description : "+rs.getString("description"));                    
                        System.out.println("Mode  : "+rs.getString("mode"));
                        System.out.println("Transaction ID  : "+rs.getString("TransactionID"));
                        transactionCount++;
                        System.out.println("------------------------------------------------------------------------------------");
                    }
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
