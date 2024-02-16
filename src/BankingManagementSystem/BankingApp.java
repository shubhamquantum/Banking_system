package BankingManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    private  static final String url="jdbc:mysql://localhost:3306/banking_system";
    private  static final  String username="root";
    private  static final String password="8771";

    public static void main(String[] args)throws  ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Scanner scanner=new Scanner(System.in);
            User user=new User(connection,scanner);
            Accounts accounts=new Accounts(connection,scanner);
            AccountManager accountManager=new AccountManager(connection,scanner);
            String email;
            long account_number;
            while(true)
            {
                System.out.println("*** WELCOME TO BANKING SYSTEM ***");
                System.out.println();
                System.out.println("1.register");
                System.out.println("2.Login");
                System.out.println("3.exit");
                System.out.println("enter your choice");
                int choice=scanner.nextInt();
                switch(choice)
                {
                    case 1:
                        user.register();
                        //System.out.println();
                        break;
                    case 2:
                        email=user.login();
                        if(email!=null)
                        {
                            System.out.println("User logged in");
                            if(!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("1.open account\n2.exit");
                                if(scanner.nextInt()==1)
                                {
                                    account_number=accounts.open_account(email);
                                    System.out.println("account created successfully");
                                    System.out.println("Your account nuber is :"+account_number);
                                }
                                else {
                                    break;
                                }
                            }
                            account_number=accounts.getAccount_number(email);
                            int ch2=0;
                            while(ch2!=5)
                            {
                                System.out.println();
                                System.out.println("1.Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3 . Transfer Money");
                                System.out.println("4. check balance");
                                System.out.println("5.Log out");
                                System.out.println("enter your choice");
                                ch2=scanner.nextInt();
                                switch(ch2){
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.get_balance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("enter the valid choice");
                                        break;
                                }
                            }
                        }else {
                            System.out.println("incorrect email or password!");
                        }
                    case 3:
                        System.out.println("THANKU FOR USING THE BANKING SYSTEM!!!!");
                        System.out.println("existing system!");
                        return;
                    default:
                        System.out.println("enter the valid choice");
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
