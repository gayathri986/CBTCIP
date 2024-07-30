package banky;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    public User(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void register(){
        scanner.nextLine();
        System.out.println("Enter the full Name: ");
        String full_name = scanner.nextLine();
        System.out.println("Enter the Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the  Phonenumber:");
        long phno = scanner.nextLong();
        System.out.println("Enter the address:");
        String address=scanner.next();
        System.out.println("Enter the gender:");
        String gender=scanner.next();
        System.out.println("Enter the type of account");
        String typeOfAccount=scanner.next();
        
        long accountnum;
        Random random=new Random();
        accountnum=random.nextLong(100000010,1000000000);
        
        String ifsccode="SBI00012345";
        int password;
        password=random.nextInt(1000,9999);
        
        int balance=0;
        
        if(user_exist(email)) {
            System.out.println("User Already Exists for this Email Address!!");
            return;
        }
        String register_query = "INSERT INTO User(name,emailid,phno,address,gender,typeofaccount,accountnum,ifsccode,password,accountbalance) VALUES(?, ?, ?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setLong(3, phno);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5,gender);
            preparedStatement.setString(6, typeOfAccount);
            preparedStatement.setLong(7,accountnum);
            preparedStatement.setString(8,ifsccode);
            preparedStatement.setInt(9,password);
            preparedStatement.setInt(10,balance);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration Successfull!");
            } else {
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String login(){
        scanner.nextLine();
        System.out.println("Enter the Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter the Password: ");
        String password = scanner.nextLine();
        String login_query = "SELECT * FROM user WHERE emailid = ? AND password = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(login_query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean user_exist(String email){
        String query = "SELECT * FROM user WHERE emailid = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}