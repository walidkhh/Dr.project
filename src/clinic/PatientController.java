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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

public class PatientController implements Initializable {

    private static final Alert isPatientFound = new Alert(Alert.AlertType.ERROR);

    private static final Alert deletePatient = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert msgDeletePatient = new Alert(Alert.AlertType.ERROR);

    private static final Alert editUser = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert msgEditError = new Alert(Alert.AlertType.ERROR);

    private static final Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert error = new Alert(Alert.AlertType.ERROR);
    private static final Alert fillText = new Alert(Alert.AlertType.WARNING);

    @FXML
    private TextField idtext;

    @FXML
    private TextField searchName;

    @FXML
    private TableView<AllPatientsHelper> patientsInfo;

    @FXML
    private TableColumn<AllPatientsHelper, String> patientName;

    @FXML
    private TableColumn<AllPatientsHelper, String> phoneNumber;

    @FXML
    private TableColumn<AllPatientsHelper, String> ageColumn;

    @FXML
    private TableColumn<AllPatientsHelper, String> genderColumn;

    @FXML
    private TableColumn<AllPatientsHelper, String> address;

    @FXML
    private TableColumn<AllPatientsHelper, String> sickCondition;

    @FXML
    private TableColumn<AllPatientsHelper, String> notes;

    @FXML
    private TableColumn<AllPatientsHelper, String> idcolumn;

    @FXML
    private TableColumn<AllPatientsHelper, String> pDateColumn;

    @FXML
    private TextField txtName;

    @FXML
    private TextField tfAge;

    @FXML
    private ComboBox<String> tfGender;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextArea txtSickCondition;

    @FXML
    private TextArea txtNotes;

    @FXML
    private TextField tfDate;

    ObservableList<AllPatientsHelper> data = FXCollections.observableArrayList();
    static ObservableList<String> formData = FXCollections.observableArrayList();

    @FXML
    void backTo(MouseEvent event) throws IOException {

        MainView.setRoot("chosse", 950, 760);
    }

    public void patientsInfo() throws SQLException, ClassNotFoundException {

        ResultSet resultSet;

        // ارجاع بيانات جميع المرضى 
        resultSet = Database.getPatientsInfo();
        while (resultSet.next()) {

            // اضافة البيانات لعرضها لاحقا في الجدول
            data.add(new AllPatientsHelper(
                    resultSet.getString("p_name"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("p_address"),
                    resultSet.getString("p_status"),
                    resultSet.getString("notes"),
                    resultSet.getString("id"),
                    resultSet.getString("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("p_date")
            ));

        }
        resultSet.close();
        //Database.closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tfGender.getItems().addAll("ذكر", "أنثى");

        try {
            patientsInfo();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ربط اعمدة الجدول مع المتغيرات في كلاس المرضى
        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        sickCondition.setCellValueFactory(new PropertyValueFactory<>("sickCondition"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        pDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // اضافة البيانات الى الجدول
        patientsInfo.setItems(data);

    }

    @FXML
    void addBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        // التاكد بان حقول الادخال غير فارغة
        if (txtName.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() /*|| txtAddress.getText().isEmpty() || txtSickCondition.getText().isEmpty()
                || txtNotes.getText().isEmpty()*/) {

            // اظهار رسالة تنبيه للمستخدم في حال كانت الحقول فارغة
            fillText.setTitle("تنبيه");
            fillText.setHeaderText("");
            fillText.setContentText("رجاء قم بملئ حقول الادخال");
            fillText.showAndWait();

        } else {

            LocalDate date = LocalDate.now();
            // ارسال المدخلات الى قاعدة البيانات وارجاع النتيجة الى المتغير
            int res = Database.addPatient(txtName.getText(), txtPhoneNumber.getText(), txtAddress.getText(),
                    txtSickCondition.getText(), txtNotes.getText(), tfAge.getText(), tfGender.getValue(), date.toString());

            //  التاكد بان قيمة المتغير لاتساوي صفر دلالة على اضافة البيانات بشكل صحيح
            if (res != 0) {

                // اظهار رسالة تاكد اضافة البيانات بنجاح
                confirmation.setTitle("تاكيد");
                confirmation.setHeaderText("");
                confirmation.setContentText("تم اضافة البيانات بنجاح");
                confirmation.showAndWait();

                data.clear();
                patientsInfo();
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
    void deleteBtn(ActionEvent event) throws ClassNotFoundException, SQLException {
        int isDeleted = Database.deletePatient(Integer.parseInt(idtext.getText()));

        if (isDeleted != 0) {
            deletePatient.setTitle("تاكيد");
            deletePatient.setHeaderText("");
            deletePatient.setContentText("تم الحذف بنجاح");
            deletePatient.showAndWait();
            patientsInfo.getItems().removeAll(patientsInfo.getSelectionModel().getSelectedItems());
            idtext.clear();

        } else {
            msgDeletePatient.setTitle("خطا");
            msgDeletePatient.setHeaderText("");
            msgDeletePatient.setContentText("لم يتم الحذف بنجاح");
            msgDeletePatient.showAndWait();
        }
    }

    @FXML
    void editBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        int isUpdated = Database.updatePatient(txtName.getText(), txtPhoneNumber.getText(), txtAddress.getText(),
                txtSickCondition.getText(), txtNotes.getText(), Integer.parseInt(idtext.getText()), tfAge.getText(), tfGender.getValue(), tfDate.getText());

        if (isUpdated != 0) {
            editUser.setTitle("تاكيد");
            editUser.setHeaderText("");
            editUser.setContentText("تم التعديل بنجاح");
            editUser.showAndWait();

            // حذف البيانات الموجودة في الجدول
            data.clear();
            // اضافة البيانات الى الجدول بعد التعديل عليها
            patientsInfo();
            clearTextField();
        } else {
            msgEditError.setTitle("خطا");
            msgEditError.setHeaderText("");
            msgEditError.setContentText("لم يتم التعديل على البيانات بنجاح");
            msgEditError.showAndWait();
        }

    }

    @FXML
    void btnSearch(ActionEvent event) throws ClassNotFoundException, SQLException {

        // حذف البيانات السابقة بعد الضغط على زر البحث
        data.clear();
        patientsInfo.setItems(data);

        ResultSet resultSet;

        // ارجاع اسم المريض من القاعدة
        resultSet = Database.searchPatientsInfo(searchName.getText());
        boolean isFound = resultSet.next();
        // التاكد بان الاسم موجود في القاعدة
        if (isFound) {

            data.add(new AllPatientsHelper(
                    resultSet.getString("p_name"),
                    resultSet.getString("p_phone_number"),
                    resultSet.getString("p_address"),
                    resultSet.getString("p_status"),
                    resultSet.getString("notes"),
                    resultSet.getString("age"),
                    resultSet.getString("gender"),
                    resultSet.getString("p_date")));

            while (resultSet.next()) {
                data.add(new AllPatientsHelper(
                        resultSet.getString("p_name"),
                        resultSet.getString("p_phone_number"),
                        resultSet.getString("p_address"),
                        resultSet.getString("p_status"),
                        resultSet.getString("notes"),
                        resultSet.getString("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("p_date")
                ));
            }

            resultSet.close();
            // عرض رسالة خطا في حال اسم المريض غير موجود 
        } else if (isFound == false && !searchName.getText().isEmpty()) {
            isPatientFound.setTitle("خطا");
            isPatientFound.setHeaderText("");
            isPatientFound.setContentText("اسم المريض غير موجود");
            isPatientFound.showAndWait();
        }

        // ربط اعمدة الجدول مع المتغيرات في كلاس المرضى
        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        sickCondition.setCellValueFactory(new PropertyValueFactory<>("sickCondition"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        pDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // اضافة البيانات الى الجدول
        patientsInfo.setItems(data);

        // التاكد من قيمة الحقل فارغة لارجاع البيانات السابقة
        if (searchName.getText().isEmpty()) {
            patientsInfo();
        }
    }

    @FXML
    void getSelectedPatient(MouseEvent event) {

        idtext.setText(patientsInfo.getSelectionModel().getSelectedItem().getId());
        txtName.setText(patientsInfo.getSelectionModel().getSelectedItem().getName());
        txtPhoneNumber.setText(patientsInfo.getSelectionModel().getSelectedItem().getPhoneNumber());
        txtAddress.setText(patientsInfo.getSelectionModel().getSelectedItem().getAddress());
        txtSickCondition.setText(patientsInfo.getSelectionModel().getSelectedItem().getSickCondition());
        txtNotes.setText(patientsInfo.getSelectionModel().getSelectedItem().getNotes());
        tfAge.setText(patientsInfo.getSelectionModel().getSelectedItem().getAge());
        tfGender.setValue(patientsInfo.getSelectionModel().getSelectedItem().getGender());
        tfDate.setText(patientsInfo.getSelectionModel().getSelectedItem().getDate());
    }

    // دالة مسح محتوى حقول الادخال
    private void clearTextField() {
        txtName.clear();
        txtPhoneNumber.clear();
        txtAddress.clear();
        txtSickCondition.clear();
        txtNotes.clear();
        tfAge.clear();

    }

    // طباعة 
    @FXML
    void print(ActionEvent event) throws IOException {

        formData.addAll(
                txtName.getText(),
                tfAge.getText()
        );

        MainView.setRoot("form", 1000, 760);
    }
}
