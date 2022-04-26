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

    static String pReservatioNumber,
            name,
            pGender,
            pAge,
            pPhoneNumber,
            pReservationType;
    static LocalDate pReservationDate;

    Alert reservation = new Alert(Alert.AlertType.INFORMATION);

    static ObservableList<ReservationHelper> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reservationType.getItems().addAll("عبر الهاتف", "حضور");
    }

    @FXML
    void reservation(ActionEvent event) throws IOException {

        pReservatioNumber = reservationNumber.getText();
        name = pName.getText();
        pGender = gender.getText();
        pAge = age.getText();
        pPhoneNumber = phoneNumber.getText();
        pReservationDate = reservationDate.getValue();
        pReservationType = reservationType.getValue();

        reservation.setTitle("تاكيد");
        reservation.setHeaderText("");
        reservation.setContentText("تم الحجز بنجاح");
        reservation.showAndWait();
    }

    @FXML
    void showAllReservations(ActionEvent event) throws IOException {
        MainView.setRoot("allReservations", 950, 760);
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    public static ObservableList getReservationInfo() {

        data.add(new ReservationHelper(
                pReservatioNumber,
                name,
                pGender,
                pAge,
                pPhoneNumber,
                pReservationDate,
                pReservationType
        ));

        return data;
    }
}
