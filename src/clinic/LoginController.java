package clinic;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    static Stage primaryStage;
    Alert warning = new Alert(Alert.AlertType.WARNING);
    Alert error = new Alert(Alert.AlertType.ERROR);
    Alert accesDenail = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton normalUser;

    @FXML
    private RadioButton admin;

    @FXML
    void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        // من خلال هذا المتغير نحدد فيما اذا كان المستخدم موجود في القاعدة ام لا
        boolean isLoggedIn = false;

        // اظهار رسالة تنبيه للمستخدم اذا لم يقوم بملئ حقول الادخال بالبيانات
        warning.setTitle("تحذير");
        warning.setHeaderText("");
        warning.setContentText("رجاءا قم بملى  حقول الادخال");

        // اظهار رسالة خطا في حال لم تكن معلومات المستخدم صحيحية
        error.setTitle("خطا");
        error.setHeaderText("");
        error.setContentText("خطا في اسم المستخدم او كلمة المرور");

        // اظهار رسالة خطا في حال كان المستخدم غير مصرح بالدخول كادمن
        if (userName.getText().isEmpty() || password.getText().isEmpty()) {
            warning.showAndWait(); // اظهار رسالة التنبيه
            return;
        }

        // احضار المعلومات من القاعدة باستخدم الدالة الموجودة في كلاس قاعدة البيانات   
        ResultSet resultSet = Database.getUserInfo();

        while (resultSet.next()) {

            if (userName.getText().equals(resultSet.getString("user_name"))
                    && password.getText().equals(resultSet.getString("pass_word"))) {
                isLoggedIn = true;
                break;
            }
        }

        if (isLoggedIn) {
            // فتح الواجهة بعد التسجيل بشكل صحيح
            MainView.setRoot("chosse", 950, 760);

        } else {
            error.showAndWait(); // اظهار رسالة الخطا
        }

        resultSet.close();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        primaryStage = MainView.getPrimaryStage();
        primaryStage.close();
    }
}
