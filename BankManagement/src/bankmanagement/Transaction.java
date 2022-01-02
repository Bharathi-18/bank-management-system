package bankmanagement;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.Scanner;

public class Transaction {

    void transaction()
    {
        String senderID,receiverID,pass,res,tempBalance,amount,upd;
        double senderBalance,receiverBalance,amnt;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID : ");
        senderID = sc.nextLine();
        
        try
        {   
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB","root","bharathi18");
            String sendstmnt = "select * from customerdetails where cust_id = "+senderID;
            Statement s =con.createStatement(); 
            ResultSet rs = s.executeQuery(sendstmnt);
            rs.next();
            res = rs.getString(9);
            tempBalance = rs.getString("cust_balance");
            senderBalance = Double.parseDouble(tempBalance);
            String sendcustName = rs.getString("cust_name");
            String sendtbleName = "trns"+senderID;
            System.out.print("Enter password : ");
            pass = sc.nextLine();
            int i = 1;
            while(i<=2)
            {
                if(res.equals(pass))
                {
                    System.out.print("Enter receiver customer ID : ");
                    receiverID = sc.nextLine();
                    String receivetbleName = "trns"+receiverID;
                    System.out.print("Enter amount for transaction : ");
                    amount = sc.nextLine();
                    amnt = Double.parseDouble(amount);
                    System.out.print("Description(if any) : ");
                    String description = sc.nextLine();  
                    String receivestmnt = "select * from customerdetails where cust_id = "+receiverID;
                    Statement sr =con.createStatement(); 
                    ResultSet r = sr.executeQuery(receivestmnt);
                    r.next();
                    tempBalance = r.getString("cust_balance"); 
                    receiverBalance = Double.parseDouble(tempBalance);
                    String receivercustName = r.getString("cust_name");
                    senderBalance -= amnt;
                    String sblnce = ""+senderBalance;
                    if(senderBalance > 1000)
                    {
                        receiverBalance += amnt;
                        String rblnce = ""+receiverBalance;
                        upd = "update customerdetails set cust_balance = "+senderBalance+" where cust_id = "+senderID;
                        s.executeUpdate(upd);
                        upd = "update customerdetails set cust_balance = "+receiverBalance+" where cust_id = "+receiverID;
                        s.executeUpdate(upd);
                        PreparedStatement ps = con.prepareStatement("insert into "+sendtbleName+" values(?,?,?,?,?,?,?,?)");
                        ps.setString(1,senderID);
                        ps.setString(2,sendcustName);
                        ps.setString(3,"Debit");
                        ps.setString(4,amount);
                        ps.setString(5,sblnce);
                        ps.setString(6,description);
                        ps.setString(7,"Transferred to");
                        ps.setString(8,receiverID);
                        ps.executeUpdate();
                        ps = con.prepareStatement("insert into "+receivetbleName+" values(?,?,?,?,?,?,?,?)");
                        ps.setString(1,receiverID);
                        ps.setString(2,receivercustName);
                        ps.setString(3,"Credit");
                        ps.setString(4,amount);
                        ps.setString(5,rblnce);
                        ps.setString(6,description);
                        ps.setString(7,"Transferred from");
                        ps.setString(8,senderID);
                        ps.executeUpdate();
                    }
                    else
                    {
                        System.out.println("Insufficient Balance!!!!!");
                        System.out.println("Transaction Failed  :( ");
                        
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
