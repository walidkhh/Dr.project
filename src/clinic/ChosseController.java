package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ChosseController implements Initializable {

    @FXML
    private Button btnVisible;
    public static boolean visible = false;

    public static void isVisibled(boolean isVisibled) {
        visible = isVisibled;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnVisible.setVisible(visible);
    }

    @FXML
    void addPatients(ActionEvent event) throws IOException {
        MainView.setRoot("addPatient", 950, 760);
    }

    @FXML
    void allPatients(ActionEvent event) throws IOException {
        MainView.setRoot("allPatients", 950, 760);
    }

    @FXML
    void reservation(ActionEvent event) throws IOException {
        MainView.setRoot("reservation", 950, 760);
    }

    // back to login page
    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("login", 900, 770);
    }

    //open admi page
    @FXML
    void userInfo(ActionEvent event) throws IOException {
        MainView.setRoot("adminpages", 950, 760);
    }
}
