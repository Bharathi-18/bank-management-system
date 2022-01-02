package bankmanagement;

import java.util.Scanner;

public class Change extends Verify{
    Scanner sc = new Scanner(System.in);
    void changedetails(){
        switch (option) {
            case 1:
                System.out.print("Enter new phone number : ");
                long phne = sc.nextLong();
                phone(phne);
                break;
            case 2:
                System.out.print("Enter new emailID : ");
                String mail = sc.nextLine();
                email(mail);
                break;
            case 3:
                System.out.print("Enter new address : ");
                String addrs = sc.nextLine();
                address(addrs);
                break;
            case 4:
                System.out.print("Enter new password : ");
                String pass = sc.nextLine();
                System.out.print("Retype password : ");
                String pass1 = sc.nextLine();
                if(pass.equals(pass1))
                {
                    password(pass);
                }
                else
                {
                    System.out.println("Password mismatch!!!!\nUnable to change password");
                }   break;
            default:
                break;
        }
    }
}
