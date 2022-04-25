package clinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/my_clinic"; // حدد اسم  القاعدة حسب الموجودة عندك
    private static final String USER = "root";
    private static final String PASSWORD = "1+2*$#"; // حدد كلمة المرور حسب الموجودة عندك

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

        // اضف استعلام استرجاع معلومات كل المرضى
        return statement.executeQuery("SELECT اكمل الاستعلام ");
    }
    
    // بقية الدوال تضاف لاحقا

}
