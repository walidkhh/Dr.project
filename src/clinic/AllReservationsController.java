package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AllReservationsController implements Initializable {

    Alert isPatientFound = new Alert(Alert.AlertType.ERROR);
    Alert fillText = new Alert(Alert.AlertType.WARNING);
    Alert reservation = new Alert(Alert.AlertType.INFORMATION);
    static LocalDate pReservationDate;

    @FXML
    private TextField searchFiled;

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
    private TextField tfReservationCost;

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
    private TableColumn<ReservationHelper, Integer> reservationCost;

    @FXML
    private TableColumn<ReservationHelper, LocalDate> reservationDate;

    @FXML
    private TableColumn<ReservationHelper, Integer> idColumn;

    static ObservableList<ReservationHelper> data = FXCollections.observableArrayList();

    private ObservableList<ReservationHelper> masterData = FXCollections.observableArrayList();

    public void reservationInfo() throws SQLException, ClassNotFoundException {

        ResultSet resultSet;

        // ارجاع بيانات جميع المرضى 
        resultSet = Database.getReservationInfo();
        while (resultSet.next()) {

            // اضافة البيانات لعرضها لاحقا في الجدول
            data.add(new ReservationHelper(
                    resultSet.getString("booking_number"),
                    resultSet.getString("p_name"),
                    resultSet.getString("p_age"),
                    resultSet.getString("p_gender"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("booking_date"),
                    resultSet.getString("booking_type"),
                    resultSet.getInt("booking_cost"),
                    resultSet.getInt("id")
            ));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        reservationTypeList.getItems().addAll("عبر الهاتف", "حضور");
        genderList.getItems().addAll("ذكر", "أنثى");

        reservationNumber.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // اضافة البيانات الى الجدول
        reservationTable.setItems(data);
    }

    @FXML
    void addBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        if (reservationNumber.getText().isEmpty() || pName.getText().isEmpty()
                || gender.getText().isEmpty() || age.getText().isEmpty()
                || phoneNumber.getText().isEmpty()) {

            fillText.setTitle("تنبيه");
            fillText.setHeaderText("");
            fillText.setContentText("رجاء قم بملئ حقول الادخال");
            fillText.showAndWait();
        } else {
            reservation.setTitle("تاكيد");
            reservation.setHeaderText("");
            reservation.setContentText("تم الحجز بنجاح");
            reservation.showAndWait();
            AllReservationsController.getReservationInfo(
                    tfReservationNumber.getText(),
                    tfPName.getText(),
                    genderList.getValue(),
                    tfAge.getText(),
                    tfPhoneNumber.getText(),
                    reservationDatePicker.getValue().toString(),
                    reservationTypeList.getValue(),
                    Integer.parseInt(tfReservationCost.getText()));

            Database.addReservation(
                    tfReservationNumber.getText(),
                    tfPName.getText(),
                    genderList.getValue(),
                    tfAge.getText(),
                    tfPhoneNumber.getText(),
                    reservationDatePicker.getValue().toString(),
                    reservationTypeList.getValue(),
                    Integer.parseInt(tfReservationCost.getText()));

            // مسح محتويات حقول الادخال
            clearTextField();
        }

    }

    @FXML
    void deleteBtn(ActionEvent event) {

    }

    @FXML
    void editBtn(ActionEvent event) {

    }

    public static void getReservationInfo(String pReservatioNumber, String name, String pGender,
            String pAge, String pPhoneNumber, String pReservationDate, String pReservationType, int pReservationCost) {

        data.add(new ReservationHelper(
                pReservatioNumber,
                name,
                pGender,
                pAge,
                pPhoneNumber,
                pReservationDate,
                pReservationType,
                pReservationCost
        ));
    }

    @FXML
    void searchBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
// حذف البيانات السابقة بعد الضغط على زر البحث
        data.clear();
        reservationTable.setItems(data);

        ResultSet resultSet;

        // ارجاع اسم المريض من القاعدة
        resultSet = Database.searchReservationInfo(searchFiled.getText());
        boolean isFound = resultSet.next();
        // التاكد بان الاسم موجود في القاعدة
        if (isFound) {

            data.add(new ReservationHelper(
                    resultSet.getString("booking_number"),
                    resultSet.getString("p_name"),
                    resultSet.getString("p_age"),
                    resultSet.getString("p_gender"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("booking_date"),
                    resultSet.getString("booking_type"),
                    resultSet.getInt("booking_cost")
            ));

            while (resultSet.next()) {
                data.add(new ReservationHelper(
                        resultSet.getString("booking_number"),
                        resultSet.getString("p_name"),
                        resultSet.getString("p_age"),
                        resultSet.getString("p_gender"),
                        resultSet.getString("p_phone_number"),
                        resultSet.getString("booking_date"),
                        resultSet.getString("booking_type"),
                        resultSet.getInt("booking_cost")
                ));
            }

            // عرض رسالة خطا في حال اسم المريض غير موجود 
        } else if (isFound == false && !searchFiled.getText().isEmpty()) {
            isPatientFound.setTitle("خطا");
            isPatientFound.setHeaderText("");
            isPatientFound.setContentText("اسم المريض غير موجود");
            isPatientFound.showAndWait();
        }

        // ربط اعمدة الجدول مع المتغيرات في كلاس المرضى
        reservationNumber.setCellValueFactory(new PropertyValueFactory<>("reservationNumber"));
        pName.setCellValueFactory(new PropertyValueFactory<>("name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        reservationType.setCellValueFactory(new PropertyValueFactory<>("reservationType"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));
        reservationCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        // اضافة البيانات الى الجدول
        reservationTable.setItems(data);

        // التاكد من قيمة الحقل فارغة لارجاع البيانات السابقة
        if (searchFiled.getText().isEmpty()) {
            reservationInfo();
        }
    }

    private void clearTextField() {

        tfReservationNumber.clear();
        tfPName.clear();
        tfAge.clear();
        tfPhoneNumber.clear();
        tfReservationCost.clear();

    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("reservation", 960, 770);

    }
}
