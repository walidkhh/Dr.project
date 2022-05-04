package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

public class AllPatientsController implements Initializable {

    Alert isPatientFound = new Alert(Alert.AlertType.ERROR);

    Alert deletePatient = new Alert(Alert.AlertType.INFORMATION);
    Alert msgDeletePatient = new Alert(Alert.AlertType.ERROR);

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
    private TableColumn<AllPatientsHelper, String> address;

    @FXML
    private TableColumn<AllPatientsHelper, String> sickCondition;

    @FXML
    private TableColumn<AllPatientsHelper, String> notes;

    @FXML
    private TableColumn<AllPatientsHelper, String> idcolumn;

    ObservableList<AllPatientsHelper> data = FXCollections.observableArrayList();

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
                    resultSet.getString("id")
            ));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            patientsInfo();
        } catch (SQLException ex) {
            Logger.getLogger(AllPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // ربط اعمدة الجدول مع المتغيرات في كلاس المرضى
        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientName.setCellFactory(TextFieldTableCell.forTableColumn());

        patientName.setOnEditCommit((TableColumn.CellEditEvent<AllPatientsHelper, String> t) -> t.getTableView()
                .getItems()
                .get(t.getTablePosition().getRow())
                .setName(t.getNewValue()));

        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        sickCondition.setCellValueFactory(new PropertyValueFactory<>("sickCondition"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // اضافة البيانات الى الجدول
        patientsInfo.setItems(data);
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
                    resultSet.getString("notes")));

            while (resultSet.next()) {
                data.add(new AllPatientsHelper(
                        resultSet.getString("p_name"),
                        resultSet.getString("p_phone_number"),
                        resultSet.getString("p_address"),
                        resultSet.getString("p_status"),
                        resultSet.getString("notes")
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
        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        sickCondition.setCellValueFactory(new PropertyValueFactory<>("sickCondition"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));

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
    }

    // حذف المريض المحدد
    @FXML
    void deleteSelectedPatient(MouseEvent event) throws ClassNotFoundException, SQLException {

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
}
