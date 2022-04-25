package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddPatientController implements Initializable {
 
    @FXML
    private TextField patientName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField adderss;

    @FXML
    private TextField sickCondition;

    @FXML
    private TextArea notes;
    

    
    @FXML
    void addPaitent(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        
        Database.addPatient(patientName.toString(), phoneNumber.toString() ,adderss.toString(),
                sickCondition.toString(), notes.toString());
    }
    
    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
}
