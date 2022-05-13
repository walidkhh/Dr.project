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
    private static final String PASSWORD = ""; //1234   حدد كلمة المرور حسب الموجودة عندك  

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // دالة ارجاع معلومات المستخدم
    public static ResultSet getUserInfo() throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();

        return statement.executeQuery("SELECT * FROM login");
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

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // التعديل على بيانات المريض 
    public static int updatePatient(String pName, String phoneNumber, String pAddress,
            String pStatus, String notes, int id) throws ClassNotFoundException, SQLException {

        String query = "UPDATE adding_pateints SET  p_name = ? ,p_phone_number = ? ,p_address = ?"
                + ", p_status = ? , p_status, notes = ?  WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, pName);
        pstmt.setString(2, phoneNumber);
        pstmt.setString(3, pAddress);
        pstmt.setString(4, pStatus);
        pstmt.setString(5, notes);
        pstmt.setInt(6, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // حذف مريض ما 
    public static int deletePatient(int id) throws ClassNotFoundException, SQLException {

        String query = "DELETE FROM adding_pateints  WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // دالة البحث عن مريض معين حسب الاسم
    public static ResultSet searchPatientsInfo(String patientName) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM adding_pateints WHERE p_name = ?";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, patientName);
        return pstmt.executeQuery();

    }

    // اضافة مستخدم جديد
    public static int addUser(String username, String password, String privilegeType) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO login(user_name , pass_word, user_type) "
                + "VALUES(?,?,?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, privilegeType);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // حذف مستخدم 
    public static int deleteUser(int id) throws ClassNotFoundException, SQLException {

        String query = "DELETE FROM login  WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // التعديل على بيانات المستخدم 
    public static int updateUser(String username, String password, String privilegeType, int id) throws ClassNotFoundException, SQLException {

        String query = "UPDATE login SET  user_name = ? , pass_word = ? , user_type = ? WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, privilegeType);
        pstmt.setInt(4, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // اضافة حجز
    public static int addReservation(String bookingNumber, String pName, String pAge, String pGender,
            String phoneNumber, String bookingDate, String bookingType, int bookingCost) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO booking(book_number,p_name,p_age,p_gender, p_phone_number, booking_date, booking_type, booking_cost) "
                + "VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, Integer.parseInt(bookingNumber));
        pstmt.setString(2, pName);
        pstmt.setString(3, pAge);
        pstmt.setString(4, pGender);
        pstmt.setString(5, phoneNumber);
        pstmt.setString(6, bookingDate);
        pstmt.setString(7, bookingType);
        pstmt.setInt(8, bookingCost);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // حذف حجز محدد
    public static int deleteReservation(int id) throws ClassNotFoundException, SQLException {

        String query = "DELETE FROM booking  WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setInt(1, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // التعديل على بيانات الحجز 
    public static int updateReservation(String bookNumber, String name, String gender, String age,
            String phoneNumber, String bookingDate, String bookingType, int bookingCost,
            int id) throws ClassNotFoundException, SQLException {

        String query = "UPDATE booking SET  book_number = ? , p_name = ? , p_age = ?"
                + ", p_gender = ? , p_phone_number = ? , booking_date = ?, booking_type = ? , booking_cost = ?"
                + " WHERE id = ? ";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, bookNumber);
        pstmt.setString(2, name);
        pstmt.setString(3, age);
        pstmt.setString(4, gender);
        pstmt.setString(5, phoneNumber);
        pstmt.setString(6, bookingDate);
        pstmt.setString(7, bookingType);
        pstmt.setInt(8, bookingCost);
        pstmt.setInt(9, id);

        int res = pstmt.executeUpdate();
        pstmt.close();
        return res;
    }

    // ارجاع كل الحجوزات
    public static ResultSet getReservationInfo() throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM booking;");
    }

    // البحث عن حجز معين
    public static ResultSet searchReservationInfo(String patientName) throws ClassNotFoundException, SQLException {

        String query = "SELECT * FROM booking WHERE p_name = ?";

        PreparedStatement pstmt = getConnection().prepareStatement(query);
        pstmt.setString(1, patientName);
        return pstmt.executeQuery();
    }

}
