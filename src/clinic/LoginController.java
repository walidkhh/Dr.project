package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
static Stage primaryStage ;
    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void login(ActionEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
       primaryStage =  MainView.getPrimaryStage();
       primaryStage.close();
    }
}
