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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AllReservationsController implements Initializable {

    @FXML
    private TextField searchFiled;

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
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        // اضافة البيانات الى الجدول
        reservationTable.setItems(data);

    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("reservation", 960, 770);

    }

    public static void getReservationInfo(String pReservatioNumber, String name, String pGender,
            String pAge, String pPhoneNumber, LocalDate pReservationDate, String pReservationType) {

        data.add(new ReservationHelper(
                pReservatioNumber,
                name,
                pGender,
                pAge,
                pPhoneNumber,
                pReservationDate,
                pReservationType
        ));
    }
    
    
    @FXML
    void searchBtn(ActionEvent event) {
        
        reservationTable.getItems().filtered((t) -> {
            data.add(t);
            
            return true;
        });
        
   
        
    }
}
