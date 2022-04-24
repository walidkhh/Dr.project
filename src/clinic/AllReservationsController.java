package clinic;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class AllReservationsController {

     @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("reservation",960,770);
    }

}
