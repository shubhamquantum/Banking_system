package BankingManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner scanner;
    public AccountManager(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;
    }
    public void credit_money(long account_number)throws SQLException {
        scanner.nextLine();
        System.out.println("enter Amount: ");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("enter Security Pin :");
        String security_pin=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(account_number!=0)
            {
                PreparedStatement preparedStatement=connection.prepareStatement("select * from accounts where account_number=? and security_pin=?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                   // double current_balance=resultSet.getDouble("balance");
                        String credit_query="update accounts set balance=balance+? where account_number=?";
                        PreparedStatement preparedStatement1=connection.prepareStatement(credit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setDouble(2,account_number);
                        int rowaff=preparedStatement1.executeUpdate();
                        if(rowaff>0)
                        {
                            System.out.println("Rs. "+amount+" credited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("Transaction Failed !");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient Balance");
                    }
                }else {
                    System.out.println("Invalid pin!");
                }
        }catch(SQLException e){
            e.printStackTrace();
        }connection.setAutoCommit(true);
    }
    public void debit_money(long account_number)throws SQLException {
        scanner.nextLine();
        System.out.println("enter Amount: ");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("enter Security Pin :");
        String security_pin=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(account_number!=0)
            {
                PreparedStatement preparedStatement=connection.prepareStatement("select * from accounts where account_number=? and security_pin=?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    double current_balance=resultSet.getDouble("balance");
                    if(amount<=current_balance)
                    {
                        String debit_query="update accounts set balance=balance-? where account_number=?";
                        PreparedStatement preparedStatement1=connection.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setDouble(2,account_number);
                        int rowaff=preparedStatement1.executeUpdate();
                        if(rowaff>0)
                        {
                            System.out.println("Rs. "+amount+" debited successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("Transaction Failed !");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("Insufficient Balance");
                    }
                }else {
                    System.out.println("Invalid pin!");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }connection.setAutoCommit(true);
    }
    public void transfer_money(long sender_account_number)throws SQLException
    {
        scanner.nextLine();
        System.out.println("Enter Receiver  account number:");
        long receiver_account_number=scanner.nextLong();
        System.out.println("enter amount:");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("enter security pin:");
        String security_pin=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(sender_account_number!=0&&receiver_account_number!=0)
            {
                PreparedStatement preparedStatement=connection.prepareStatement("select * from accounts where account_number=? and security_pin=?");
                preparedStatement.setLong(1,sender_account_number);
                preparedStatement.setString(2,security_pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next())
                {
                    double current_balance=resultSet.getDouble("balance");
                    if(amount<=current_balance)
                    {
                        String debit_query="update accounts set balance =balance- ? where account_number=?";
                        String credit_query="update accounts set balance =balance+ ? where account_number=?";
                        PreparedStatement credit=connection.prepareStatement(credit_query);
                        credit.setDouble(1,amount);
                        credit.setLong(2,receiver_account_number);
                        PreparedStatement debit=connection.prepareStatement(debit_query);
                        debit.setDouble(1,amount);
                        debit.setLong(2,sender_account_number);
                        int rowaff1=credit.executeUpdate();
                        int rowaff2=debit.executeUpdate();
                        if(rowaff1>0&&rowaff2>0)
                        {
                            System.out.println("transaction successfully ");
                            System.out.println("Rs."+amount+"transferred successfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }
                        else {
                            System.out.println("insufficient balance");
                        }
                    }
                    else{
                        System.out.println("invalid pin !");
                    }
                }
                else {
                    System.out.println("Invalid account_number!");
                }
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }
    public void get_balance(long account_number)
    {
        scanner.nextLine();
        System.out.println("enter the security pin:");
        String security_pin=scanner.nextLine();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select balance from accounts where security_pin=? and account_number=?");
            preparedStatement.setString(1,security_pin);
            preparedStatement.setLong(2,account_number);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next())
            {
                double balance =resultSet.getDouble("balance");
                System.out.println(" Balance : "+balance);
            }
            else {
                System.out.println("Invalid pin");
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
