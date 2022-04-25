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
    private Button control;
    public static boolean disable = true;
    
    public static void isDisabled(boolean isDisabled){
        disable = isDisabled;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        control.setDisable(disable);
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

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("login",900,770);
    }
}
