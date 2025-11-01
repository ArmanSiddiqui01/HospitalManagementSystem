package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private final Connection connection;
    private final Scanner scanner;
    public Patient(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void addPatient(){
        System.out.println("Enter patient id: ");
        int id=scanner.nextInt();
        System.out.println("Enter patient name :");
        String name =scanner.next();
        scanner.nextLine();
        System.out.println("Enter patient age :");
        int age =scanner.nextInt();
        System.out.println("Enter patient gender :");
        String gender =scanner.next();
        try{
            String query="insert into patient (id,name ,age ,gender) values(?,?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,age);
            preparedStatement.setString(4,gender);
            int affectedRows=preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add patient!!");
            }

        }catch(SQLException e){
            e.printStackTrace();

        }
    }
    public void viewPatient(){
        String query = "select * from patient";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.println("Patient:");
            System.out.println("+------------+-------------+-----------+-----------+");
            System.out.println("| Patient Id | Name        | Age       | Gender    |");
            System.out.println("+------------+-------------+-----------+-----------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name =resultSet.getString("name");
                int age =resultSet.getInt("age");
                String gender=resultSet.getString("gender");
                System.out.printf("|%-12s|%-13s|%-11s|%-11s|\n ",id,name,age,gender);
                System.out.println("+------------+-------------+-----------+-----------+");
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
    }
    public boolean getPatientId(int id){
        String query="select * from patient where id = ?";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
