package clinic;

import static clinic.PatientController.formData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FormController implements Initializable {

    private static final Alert fillText = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField paitentNameId;

    @FXML
    private TextField ageId;

    @FXML
    private TextArea aboudPaitentId;

    @FXML
    private DatePicker dateId;

    static ObservableList<String> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (!formData.isEmpty()) {
            paitentNameId.setText(formData.get(0));
            ageId.setText(formData.get(1));
        }
        
        formData.clear();

    }

    @FXML
    void print(ActionEvent event) throws IOException {

        if (paitentNameId.getText().isEmpty()
                || ageId.getText().isEmpty()
                || aboudPaitentId.getText().isEmpty()) {

            // اظهار رسالة تنبيه للمستخدم في حال كانت الحقول فارغة
            fillText.setTitle("تنبيه");
            fillText.setHeaderText("");
            fillText.setContentText("رجاء قم بملئ حقول الادخال");
            fillText.showAndWait();
        } else {
            data.addAll(
                    paitentNameId.getText(),
                    ageId.getText(),
                    aboudPaitentId.getText(),
                    dateId.getValue().toString()
            );

            MainView.setRoot("racheta", 1000, 760);
        }

    }

    public static ObservableList<String> getData() {
        return data;
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("patientsInfo", 950, 760);
    }
}
