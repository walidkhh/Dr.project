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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AllPatientsController implements Initializable {

    @FXML
    private TextField searchName;

    @FXML
    private  TableView<AllPatientsHelper> patientsInfo;

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

     ObservableList<AllPatientsHelper> data = FXCollections.observableArrayList();

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ResultSet resultSet;
        try {
            resultSet = Database.getPatientsInfo();
            while (resultSet.next()) {
                data.add(new AllPatientsHelper(
                        resultSet.getString("p_name"),
                        resultSet.getString("p_phone_number"),
                        resultSet.getString("p_address"),
                        resultSet.getString("p_status"),
                        resultSet.getString("notes")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AllPatientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        patientName.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        sickCondition.setCellValueFactory(new PropertyValueFactory<>("sickCondition"));
        notes.setCellValueFactory(new PropertyValueFactory<>("notes"));
        patientsInfo.setItems(data);
    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

}
