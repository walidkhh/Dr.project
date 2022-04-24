package clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:mysql://localhost/unknown"; // حدد اسم القاعدة
    private static final String USER = "root";
    private static final String PASSWORD = "";

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

        // اضف استعلام استرجاع معلومات كل المرض
        return statement.executeQuery("SELECT اكمل الاستعلام ");
    }
    
    // بقية الدوال تضاف لاحقا

}
