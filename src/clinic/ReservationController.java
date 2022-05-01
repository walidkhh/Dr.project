package clinic;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ReservationController implements Initializable {

    @FXML
    private TextField reservationNumber;

    @FXML
    private TextField pName;

    @FXML
    private TextField gender;

    @FXML
    private TextField age;

    @FXML
    private TextField phoneNumber;

    @FXML
    private DatePicker reservationDate;

    @FXML
    private ComboBox<String> reservationType;

    static LocalDate pReservationDate;
    Alert reservation = new Alert(Alert.AlertType.INFORMATION);

    static ObservableList<ReservationHelper> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservationType.getItems().addAll("عبر الهاتف", "حضور");
    }

    @FXML
    void reservation(ActionEvent event) throws IOException {

        reservation.setTitle("تاكيد");
        reservation.setHeaderText("");
        reservation.setContentText("تم الحجز بنجاح");
        reservation.showAndWait();

        // تمرير البيانات الى واجهة اظهار الحجوزات لاضافتها في الجدول
        AllReservationsController.getReservationInfo(
                reservationNumber.getText(),
                pName.getText(),
                gender.getText(),
                age.getText(),
                phoneNumber.getText(),
                reservationDate.getValue(),
                reservationType.getValue());

        // مسح محتويات حقول الادخال
        clearTextField();
    }

    @FXML
    void showAllReservations(ActionEvent event) throws IOException {
        MainView.setRoot("allReservations", 950, 760);
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    private void clearTextField() {

        pName.clear();
        reservationNumber.clear();
        gender.clear();
        age.clear();
        phoneNumber.clear();

    }
}
