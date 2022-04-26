package clinic;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AllReservationsController implements Initializable {

    @FXML
    private TableView<ReservationHelper> reservationTable;

    @FXML
    private TableColumn<ReservationHelper, String> reservationNumber;

    @FXML
    private TableColumn<ReservationHelper, String> pName;

    @FXML
    private TableColumn<ReservationHelper, String> gender;

    @FXML
    private TableColumn<ReservationHelper, String> age;

    @FXML
    private TableColumn<ReservationHelper, String> phoneNumber;

    @FXML
    private TableColumn<ReservationHelper, String> reservationType;

    @FXML
    private TableColumn<ReservationHelper, LocalDate> reservationDate;

    static ObservableList<ReservationHelper> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reservationNumber.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
        pName.setCellValueFactory(new PropertyValueFactory<>("pName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        reservationTable.setItems(ReservationController.getReservationInfo());
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("reservation", 960, 770);

    }

}
