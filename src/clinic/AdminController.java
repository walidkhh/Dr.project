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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController implements Initializable {

    Alert addUser = new Alert(Alert.AlertType.INFORMATION);
    Alert msgAddError = new Alert(Alert.AlertType.ERROR);

    Alert deleteUser = new Alert(Alert.AlertType.INFORMATION);
    Alert msgDeleteError = new Alert(Alert.AlertType.ERROR);

    Alert editUser = new Alert(Alert.AlertType.INFORMATION);
    Alert msgEditError = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TableView<AdminHelper> adminTable;

    @FXML
    private TableColumn<AdminHelper, String> col_username;

    @FXML
    private TableColumn<AdminHelper, String> col_password;

    @FXML
    private TableColumn<AdminHelper, String> col_prev;

    @FXML
    private TableColumn<AdminHelper, Integer> idColumn;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private TextField textID;

    @FXML
    private ComboBox<String> privileges;
    ObservableList<AdminHelper> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getInfo();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        privileges.getItems().addAll("مسؤول", "مستخدم");
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_prev.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        adminTable.setItems(data);

    }

    // اضافة مستخدم جديد
    @FXML
    void addBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        String privType = privileges.getValue().equals("مسؤول") ? "1" : "0";
        int isAdded = Database.addUser(userName.getText(), password.getText(), privType);

        if (isAdded != 0) {
            addUser.setTitle("تاكيد");
            addUser.setHeaderText("");
            addUser.setContentText("تم الاضافة بنجاح");
            addUser.showAndWait();
            data.add(new AdminHelper(
                    userName.getText(),
                    password.getText(),
                    privileges.getValue()
            ));

            // حذف البيانات الموجودة في الجدول
            data.clear();
            // اضافة البيانات الى الجدول بعد التعديل عليها
            getInfo();
            clearTextField();
        } else {

            msgAddError.setTitle("خطا");
            msgAddError.setHeaderText("");
            msgAddError.setContentText("لم يتم الاضافة بنجاح");
            msgAddError.showAndWait();
        }
    }

    // حذف  محتويات مستخدم معين
    @FXML
    void deleteBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        int isDeleted = Database.deleteUser(Integer.parseInt(textID.getText()));

        if (isDeleted != 0) {
            deleteUser.setTitle("تاكيد");
            deleteUser.setHeaderText("");
            deleteUser.setContentText("تم الحذف بنجاح");
            deleteUser.showAndWait();
            adminTable.getItems().removeAll(adminTable.getSelectionModel().getSelectedItems());
            // حذف البيانات الموجودة في الجدول
            data.clear();
            // اضافة البيانات الى الجدول بعد التعديل عليها
            getInfo();
            clearTextField();
        } else {
            msgDeleteError.setTitle("خطا");
            msgDeleteError.setHeaderText("");
            msgDeleteError.setContentText("لم يتم الحذف بنجاح");
            msgDeleteError.showAndWait();
        }
    }

    // التعديل على محتويات مستخدم معين
    @FXML
    void editBtn(ActionEvent event) throws ClassNotFoundException, SQLException {

        String privType = privileges.getValue().equals("مسؤول") ? "1" : "0";
        int isUpdated = Database.updateUser(userName.getText(),
                password.getText(),
                privType,
                Integer.parseInt(textID.getText()));

        if (isUpdated != 0) {
            editUser.setTitle("تاكيد");
            editUser.setHeaderText("");
            editUser.setContentText("تم التعديل بنجاح");
            editUser.showAndWait();

            // حذف البيانات الموجودة في الجدول
            data.clear();
            // اضافة البيانات الى الجدول بعد التعديل عليها
            getInfo();
            clearTextField();

        } else {
            msgEditError.setTitle("خطا");
            msgEditError.setHeaderText("");
            msgEditError.setContentText("لم يتم التعديل على بيانات المستخدم بنجاح");
            msgEditError.showAndWait();
        }

    }

    // العودة الى الواجهة السابقة
    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    // تحديد سطر معين من الجدول لعرض محتوياته في حقول الادخال
    @FXML
    void getSelectedItems(MouseEvent event) {

        userName.setText(adminTable.getSelectionModel().getSelectedItem().getUsername());
        password.setText(adminTable.getSelectionModel().getSelectedItem().getPassword());
        privileges.setValue(adminTable.getSelectionModel().getSelectedItem().getPrivilege());
        textID.setText(String.valueOf(adminTable.getSelectionModel().getSelectedItem().getId()));

    }

    // اضافة معلومات المستخدمين في الجدول
    private void getInfo() throws SQLException, ClassNotFoundException {
        ResultSet resultSet;

        resultSet = Database.getUserInfo();
        while (resultSet.next()) {
            String privilegeType = resultSet.getString("user_type").equals("1") ? "مسؤول" : "مستخدم";
            data.add(new AdminHelper(resultSet.getString("user_name"),
                    resultSet.getString("pass_word"), privilegeType, resultSet.getInt("id")));
        }

        resultSet.close();
        Database.closeConnection();
    }

    // مسح محتويات حقول الادخال
    private void clearTextField() {
        userName.clear();
        password.clear();
        textID.clear();
    }

}
