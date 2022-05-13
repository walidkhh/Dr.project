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
    private static final Alert addMsg = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert dontAddMsg = new Alert(Alert.AlertType.ERROR);
    static LocalDate pReservationDate;

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
                    resultSet.getString("book_number"),
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

        try {
            reservationInfo();
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

            int res = Database.addReservation(
                    tfReservationNumber.getText(),
                    tfPName.getText(),
                    genderList.getValue(),
                    tfAge.getText(),
                    tfPhoneNumber.getText(),
                    reservationDatePicker.getValue().toString(),
                    reservationTypeList.getValue(),
                    Integer.parseInt(tfReservationCost.getText()));

            if (res != 0) {
                addMsg.setTitle("تاكيد");
                addMsg.setHeaderText("");
                addMsg.setContentText("تم الحجز بنجاح");
                addMsg.showAndWait();
                data.clear();
                reservationInfo();
                // مسح محتويات حقول الادخال
                clearTextField();
            } else {
                dontAddMsg.setTitle("خطا");
                dontAddMsg.setHeaderText("");
                dontAddMsg.setContentText("لم يتم الحجز بنجاح");
                dontAddMsg.showAndWait();
            }
        }

    }

    @FXML
    void deleteBtn(ActionEvent event) {

    }

    @FXML
    void editBtn(ActionEvent event) {

    }

    @FXML
    void btnSearch(ActionEvent event) throws SQLException, ClassNotFoundException {
// حذف البيانات السابقة بعد الضغط على زر البحث
        data.clear();
        reservationTable.setItems(data);

        ResultSet resultSet;

        // ارجاع اسم المريض من القاعدة
        resultSet = Database.searchReservationInfo(searchName.getText());
        boolean isFound = resultSet.next();
        // التاكد بان الاسم موجود في القاعدة
        if (isFound) {

            data.add(new ReservationHelper(
                    resultSet.getString("book_number"),
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
                        resultSet.getString("book_number"),
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
        } else if (isFound == false && !searchName.getText().isEmpty()) {
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
        if (searchName.getText().isEmpty()) {
            reservationInfo();
        }
    }

     @FXML
    void getSelectedItem(MouseEvent event) {
        
        idtext.setText(String.valueOf(reservationTable.getSelectionModel().getSelectedItem().getId()));
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
        data.clear();
        MainView.setRoot("chosse", 960, 770);

    }
}
