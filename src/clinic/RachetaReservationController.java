package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RachetaReservationController implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private Label tfAge;

    @FXML
    private Label tfReservationNumber;

    @FXML
    private Label tfPhoneNumber;

    @FXML
    private Label tfPName;

    @FXML
    private Label tfReservationCost;

    @FXML
    private Label reservationTypeList;

    @FXML
    private Label genderList;

    @FXML
    private Label reservationDatePicker;
    
     static ObservableList<String> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
          data = ReservationController.getReservationInfo();

        tfReservationNumber.setText(data.get(0));
        tfPName.setText(data.get(1));   
        tfPhoneNumber.setText(data.get(2));
        tfReservationCost.setText(data.get(3));
        genderList.setText(data.get(4));
        reservationTypeList.setText(data.get(5));
        reservationDatePicker.setText(data.get(6));
        tfAge.setText(data.get(7));
        
        
    }
    
    @FXML
    void print(ActionEvent event) throws IOException {
        PrinterJob job = PrinterJob.createPrinterJob();

        boolean isShowPageSetup = job.showPageSetupDialog(MainView.getPrimaryStage());

        if (isShowPageSetup) {
            boolean isPrinted = job.printPage(root);
            if (isPrinted) {
                job.endJob();
            }

        }
          data.clear(); // مسح كل المحتويات

        MainView.setRoot("reservations", 1000, 790); // العودة الى الواجهة السابقة
    }
     

}
