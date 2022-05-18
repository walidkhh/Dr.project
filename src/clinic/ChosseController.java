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

        btnVisible.setVisible(visible); // اظهار او اخفاء زر معلومات المستخدمين
    }

    // فتح واجهة  المرضى
    @FXML
    void allPatients(ActionEvent event) throws IOException {
        MainView.setRoot("patientsInfo", 1100, 760);
    }

    // فتح واجهة الحجز
    @FXML
    void reservation(ActionEvent event) throws IOException {
        MainView.setRoot("reservations", 950, 760);
    }

    //العودة الى الواجهة السابقة
    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("login", 900, 770);
    }
}
