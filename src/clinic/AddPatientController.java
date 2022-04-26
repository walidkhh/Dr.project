package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddPatientController implements Initializable {

    private static final Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private static final Alert error = new Alert(Alert.AlertType.ERROR);
    private static final Alert fillText = new Alert(Alert.AlertType.WARNING);
    @FXML
    private TextField patientName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField address;

    @FXML
    private TextField sickCondition;

    @FXML
    private TextArea notes;

    @FXML
    void addPaitent(ActionEvent event) throws ClassNotFoundException, SQLException {

        // التاكد بان حقول الادخال غير فارغة
        if (patientName.getText().isEmpty() || phoneNumber.getText().isEmpty()
                || address.getText().isEmpty() || sickCondition.getText().isEmpty()
                || notes.getText().isEmpty()) {

            // اظهار رسالة تنبيه للمستخدم في حال كانت الحقول فارغة
            fillText.setTitle("تنبيه");
            fillText.setHeaderText("");
            fillText.setContentText("رجاء قم بملئ حقول الادخال");
            fillText.showAndWait();

        } else {

            // ارسال المدخلات الى قاعدة البيانات وارجاع النتيجة الى المتغير
            int res = Database.addPatient(patientName.getText(), phoneNumber.getText(), address.getText(),
                    sickCondition.getText(), notes.getText());

            // التاكد بان قيمة المتغير لاتساوي صفر دلالة على اضافة البيانات بشكل صحيح
            if (res != 0) {
                
                // اظهار رسالة تاكد اضافة البيانات بنجاح
                confirmation.setTitle("تاكيد");
                confirmation.setHeaderText("");
                confirmation.setContentText("تم اضافة البيانات بنجاح");
                confirmation.showAndWait();
                
                // استدعاء الدالة لمسح محتوى حقول الادخال
                clearTextField();
            } else {
                
                // اظهار رسالة خطا في حال لم يتم الاضافة بشكل صحيح
                error.setTitle("خطا");
                error.setHeaderText("");
                error.setContentText("لم يتم اضافة البيانات بنجاح");
                error.showAndWait();
                // استدعاء الدالة لمسح محتوى حقول الادخال
                clearTextField();
            }
        }
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    // دالة مسح محتوى حقول الادخال
    private void clearTextField() {
        patientName.clear();
        phoneNumber.clear();
        address.clear();
        sickCondition.clear();
        notes.clear();
    }

}
