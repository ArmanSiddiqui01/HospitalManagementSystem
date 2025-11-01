import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertDemo {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter your name: ");
        String name=sc.nextLine();
        System.out.println("enter your email id:");
        String email_id=sc.nextLine();
        System.out.println("enter your phone number");
        String phone=sc.nextLine();
        System.out.println("enter your gender");
        String gender=sc.nextLine();
        System.out.println("enter your city");
        String city=sc.nextLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "Arman@2007");
        PreparedStatement ps= con.prepareStatement("insert into information values(?,?,?,?,?)");
        ps.setString(1,name);
        ps.setString(2,email_id);
        ps.setString(3,phone);
        ps.setString(4,gender);
        ps.setString(5,city);
        int i=ps.executeUpdate();
        if(i>0){
            System.out.println("Data Added Successfully !!!");
        }else{
            System.out.println("by");
        }

    }
}