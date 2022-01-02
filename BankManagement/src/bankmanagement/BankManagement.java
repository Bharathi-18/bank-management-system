package bankmanagement;
                                   
import java.util.Scanner;

public class BankManagement{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------WELCOME TO BHARATH BANK-----------------------------------");
        System.out.println("1.New user\n2.Existing User");
        System.out.print("Enter your choice : ");
        int choice = sc.nextInt();
        if(choice==1)
        {
            CustomerDetails cd = new CustomerDetails();
            cd.createUser();
        }
        else
        {
            System.out.println("Select an operation:\n1. Check Balance\n2. Deposit money\n3. Withdraw money\n4. Transfer money\n5. Transaction Report\n6. Edit account details\n");
            System.out.print("Enter your choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    {
                        CheckBalance cb = new CheckBalance();
                        cb.checkBalance();
                        break;
                    }
                case 2:
                    {
                        Deposit d = new Deposit();
                        d.deposit();
                        break;
                    }
                case 3:
                    {
                        Withdraw w = new Withdraw();
                        w.withdraw();
                        break;
                    }

                case 4:
                    {
                        Transaction t = new Transaction();
                        t.transaction();
                        break;
                    }
                case 5:
                    {
                        TransactionReport treport = new TransactionReport();
                        treport.printData();
                        break;
                    }
                case 6:
                    {
                        Change c = new Change();
                        c.changedetails();
                        break;
                    }
                default:
                    break;
            }
        }
    }  
} 