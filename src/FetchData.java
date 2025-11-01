import java.sql.*;
import java.util.Scanner;

public class FetchData {
    private static final String url= "jdbc:mysql://localhost:3306/jdbc_db";
    private static final String userName= "root";
    private static final String password= "Arman@2007";
    public static void main(String[] args) throws Exception {
        FetchData fd=new FetchData();
        fd.insertData();
        fd.fetchData();

    }
    public void insertData() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your name: ");
        String name = sc.nextLine();
        System.out.println("enter your email id:");
        String email_id = sc.nextLine();
        System.out.println("enter your phone number");
        String phone = sc.nextLine();
        System.out.println("enter your gender");
        String gender = sc.nextLine();
        System.out.println("enter your city");
        String city = sc.nextLine();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db", "root", "Arman@2007");
        PreparedStatement ps = con.prepareStatement("insert into information values(?,?,?,?,?)");
        ps.setString(1, name);
        ps.setString(2, email_id);
        ps.setString(3, phone);
        ps.setString(4, gender);
        ps.setString(5, city);
        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Data Added Successfully !!!");
        } else {
            System.out.println("by");
        }
    }
    public void fetchData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
           e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            String query = "select * from information";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email_id = rs.getString("email_id");
                String phone = rs.getString("phone");
                String gender = rs.getString("gender");
                String city = rs.getString("city");

                System.out.println("Name: " + name);
                System.out.println("id: " + email_id);
                System.out.println("phone: " + phone);
                System.out.println("gender: " + gender);
                System.out.println("city: " + city);
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
