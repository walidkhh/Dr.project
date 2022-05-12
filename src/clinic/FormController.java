package clinic;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FormController {

    private static final Alert fillText = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextArea drName;

    @FXML
    private TextField addressid;

    @FXML
    private TextField paitentNameId;

    @FXML
    private TextField ageId;

    @FXML
    private TextArea aboudPaitentId;

    @FXML
    private DatePicker dateId;

    @FXML
    private TextField phoneNumId;

    @FXML
    private TextField rigesterId;

    @FXML
    private TextArea drSpe;

    @FXML
    private TextArea drCerti;

    static ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    void print(ActionEvent event) throws IOException {

        if (drName.getText().isEmpty() || addressid.getText().isEmpty()
                || paitentNameId.getText().isEmpty()
                || ageId.getText().isEmpty()
                || aboudPaitentId.getText().isEmpty() || phoneNumId.getText().isEmpty() || rigesterId.getText().isEmpty()) {

            // اظهار رسالة تنبيه للمستخدم في حال كانت الحقول فارغة
            fillText.setTitle("تنبيه");
            fillText.setHeaderText("");
            fillText.setContentText("رجاء قم بملئ حقول الادخال");
            fillText.showAndWait();
        } else {
            data.addAll(
                    drName.getText(),
                    drSpe.getText(),
                    drCerti.getText(),
                    addressid.getText(),
                    paitentNameId.getText(),
                    ageId.getText(),
                    aboudPaitentId.getText(),
                    phoneNumId.getText(),
                    rigesterId.getText()//,
                   // dateId.getValue().toString()
                    );

            MainView.setRoot("racheta", 1000, 760);
        }

    }

    public static ObservableList<String> getData() {
        return data;
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

}
