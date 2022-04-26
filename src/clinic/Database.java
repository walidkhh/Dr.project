package clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/my_clinic"; // حدد اسم  القاعدة حسب الموجودة عندك
    private static final String USER = "root";
    private static final String PASSWORD = "1+2*$#"; // 34634877 حدد كلمة المرور حسب الموجودة عندك

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // دالة ارجاع معلومات المستخدم
    public static ResultSet getUserInfo() throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();

        return statement.executeQuery("SELECT user_name , pass_word, user_type FROM login");
    }

    // دالة ارجاع معلومات المرضى واضافتها الى صفحة جميع المرضى
    public static ResultSet getPatientsInfo() throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM adding_pateints;");

    }

    // دالة اضافة بيانات المرضى
    public static int addPatient(String pName, String phoneNumber, String pAddress, String pStatus, String notes) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO adding_pateints(p_name,p_phone_number, p_address, p_status, notes) "
                + "VALUES(?,?,?,?,?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pName);
        pstmt.setString(2, phoneNumber);
        pstmt.setString(3, pAddress);
        pstmt.setString(4, pStatus);
        pstmt.setString(5, notes);

        return pstmt.executeUpdate();
    }
}
