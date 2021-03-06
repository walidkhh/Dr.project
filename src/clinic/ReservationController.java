package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ReservationController implements Initializable {

    private static final Alert isPatientFound = new Alert(Alert.AlertType.ERROR);
    private static final Alert fillText = new Alert(Alert.AlertType.WARNING);

    private static final Alert addReservation = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert msgAddError = new Alert(Alert.AlertType.ERROR);

    private static final Alert deleteReservation = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert msgDeleteError = new Alert(Alert.AlertType.ERROR);

    private static final Alert editReservation = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert msgEditError = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TextField searchName;

    @FXML
    private TextField idtext;

    @FXML
    private TextField tfReservationNumber;

    @FXML
    private TextField tfPName;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private ComboBox<String> reservationTypeList;

    @FXML
    private ComboBox<String> genderList;

    @FXML
    private DatePicker reservationDatePicker;

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

    @FXML
    private TableColumn<ReservationHelper, Integer> idColumn;

    static ObservableList<ReservationHelper> data = FXCollections.observableArrayList();
    static ObservableList<AllPatientsHelper> sentPatient = FXCollections.observableArrayList();

    public void reservationInfo() throws SQLException, ClassNotFoundException {

        ResultSet resultSet;

        // ?????????? ???????????? ???????? ???????????? 
        resultSet = Database.getReservationInfo();
        while (resultSet.next()) {

            // ?????????? ???????????????? ???????????? ?????????? ???? ????????????
            data.add(new ReservationHelper(
                    resultSet.getString("booking_number"),
                    resultSet.getString("p_name"),
                    resultSet.getString("p_gender"),
                    resultSet.getString("p_age"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("booking_date"),
                    resultSet.getString("booking_type"),
                    resultSet.getInt("id")
            ));
        }
        resultSet.close();
        //   Database.closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            reservationInfo();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        reservationTypeList.getItems().addAll("?????? ????????????", "????????");
        genderList.getItems().addAll("??????", "????????");

        reservationNumber.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // ?????????? ???????????????? ?????? ????????????
        reservationTable.setItems(data);
    }

    @FXML
    void addBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        if (reservationNumber.getText().isEmpty() || pName.getText().isEmpty()
                || gender.getText().isEmpty() || age.getText().isEmpty()
                || phoneNumber.getText().isEmpty()) {

            fillText.setTitle("??????????");
            fillText.setHeaderText("");
            fillText.setContentText("???????? ???? ???????? ???????? ??????????????");
            fillText.showAndWait();
        } else {

            int res = Database.addReservation(
                    tfReservationNumber.getText(),
                    tfPName.getText(),
                    genderList.getValue(),
                    tfAge.getText(),
                    tfPhoneNumber.getText(),
                    reservationDatePicker.getValue().toString(),
                    reservationTypeList.getValue()
                   );

            if (res != 0) {
                addReservation.setTitle("??????????");
                addReservation.setHeaderText("");
                addReservation.setContentText("???? ?????????? ??????????");
                addReservation.showAndWait();
                data.clear();
                reservationInfo();
                // ?????? ?????????????? ???????? ??????????????
                clearTextField();
            } else {
                msgAddError.setTitle("??????");
                msgAddError.setHeaderText("");
                msgAddError.setContentText("???? ?????? ?????????? ??????????");
                msgAddError.showAndWait();
            }
        }

    }

    @FXML
    void deleteBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        int isDeleted = Database.deleteReservation(Integer.parseInt(idtext.getText()));

        if (isDeleted != 0) {
            deleteReservation.setTitle("??????????");
            deleteReservation.setHeaderText("");
            deleteReservation.setContentText("???? ?????????? ??????????");
            deleteReservation.showAndWait();
            reservationTable.getItems().removeAll(reservationTable.getSelectionModel().getSelectedItems());
            clearTextField();
        } else {
            msgDeleteError.setTitle("??????");
            msgDeleteError.setHeaderText("");
            msgDeleteError.setContentText("???? ?????? ?????????? ??????????");
            msgDeleteError.showAndWait();
        }
    }

    @FXML
    void editBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        int isUpdated = Database.updateReservation(tfReservationNumber.getText(), tfPName.getText(),
                genderList.getValue(), tfAge.getText(), tfPhoneNumber.getText(), reservationDatePicker.getValue().toString(),
                reservationTypeList.getValue(),
                Integer.parseInt(idtext.getText()));

        if (isUpdated != 0) {
            editReservation.setTitle("??????????");
            editReservation.setHeaderText("");
            editReservation.setContentText("???? ?????????????? ??????????");
            editReservation.showAndWait();

            // ?????? ???????????????? ???????????????? ???? ????????????
            data.clear();
            // ?????????? ???????????????? ?????? ???????????? ?????? ?????????????? ??????????
            reservationInfo();
            clearTextField();
        } else {
            msgEditError.setTitle("??????");
            msgEditError.setHeaderText("");
            msgEditError.setContentText("???? ?????? ?????????????? ?????? ???????????? ???????????????? ??????????");
            msgEditError.showAndWait();
        }

    }

    @FXML
    void btnSearch(ActionEvent event) throws SQLException, ClassNotFoundException {
// ?????? ???????????????? ?????????????? ?????? ?????????? ?????? ???? ??????????
        data.clear();
        reservationTable.setItems(data);

        ResultSet resultSet;

        // ?????????? ?????? ???????????? ???? ??????????????
        resultSet = Database.searchReservationInfo(searchName.getText());
        boolean isFound = resultSet.next();
        // ???????????? ?????? ?????????? ?????????? ???? ??????????????
        if (isFound) {

            data.add(new ReservationHelper(
                    resultSet.getString("booking_number"),
                    resultSet.getString("p_name"),
                    resultSet.getString("p_gender"),
                    resultSet.getString("p_age"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("booking_date"),
                    resultSet.getString("booking_type")
            ));

            while (resultSet.next()) {
                data.add(new ReservationHelper(
                        resultSet.getString("booking_number"),
                        resultSet.getString("p_name"),
                        resultSet.getString("p_gender"),
                        resultSet.getString("p_age"),
                        resultSet.getString("p_phone_number"),
                        resultSet.getString("booking_date"),
                        resultSet.getString("booking_type")
                ));

            }

            resultSet.close();
            // ?????? ?????????? ?????? ???? ?????? ?????? ???????????? ?????? ?????????? 
        } else if (isFound == false && !searchName.getText().isEmpty()) {
            isPatientFound.setTitle("??????");
            isPatientFound.setHeaderText("");
            isPatientFound.setContentText(" ?????????? ?????? ??????????");
            isPatientFound.showAndWait();
        }

        // ?????? ?????????? ???????????? ???? ?????????????????? ???? ???????? ????????????
        reservationNumber.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        // ?????????? ???????????????? ?????? ????????????
        reservationTable.setItems(data);

        // ???????????? ???? ???????? ?????????? ?????????? ???????????? ???????????????? ??????????????
        if (searchName.getText().isEmpty()) {
            reservationInfo();
        }
    }

    @FXML
    void getSelectedItem(MouseEvent event) {

        idtext.setText(String.valueOf(reservationTable.getSelectionModel().getSelectedItem().getId()));
        tfReservationNumber.setText(reservationTable.getSelectionModel().getSelectedItem().getReservationNumber());
        tfPName.setText(reservationTable.getSelectionModel().getSelectedItem().getName());
        tfAge.setText(reservationTable.getSelectionModel().getSelectedItem().getAge());
        tfPhoneNumber.setText(reservationTable.getSelectionModel().getSelectedItem().getPhoneNumber());

        reservationTypeList.setValue(reservationTable.getSelectionModel().getSelectedItem().getReservationType());
        genderList.setValue(reservationTable.getSelectionModel().getSelectedItem().getGender());
        reservationDatePicker.setValue(LocalDate.parse(reservationTable.getSelectionModel().getSelectedItem().getReservationDate()));
    }

    private void clearTextField() {

        tfReservationNumber.clear();
        tfPName.clear();
        tfAge.clear();
        tfPhoneNumber.clear();

    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        data.clear();
        MainView.setRoot("chosse", 960, 770);

    }

    public static ObservableList<AllPatientsHelper> getReservationInfo() {
        return sentPatient;
    }

    @FXML
    void addPatient(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
   
        Database.addPatient(tfPName.getText(), tfPhoneNumber.getText(), 
                " ", " ", " ", tfAge.getText(), genderList.getValue(), reservationDatePicker.getValue().toString() );
    }
}
