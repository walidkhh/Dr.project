package clinic;

import static clinic.ChosseController.visible;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class RachetaController implements Initializable {

    @FXML
    private AnchorPane  root;

    @FXML
    private Label infoMidic;

    @FXML
    private Label info;

    @FXML
    private Label location;

    @FXML
    private Label patientName;

    @FXML
    private Label patientAge;

    @FXML
    private Label clinicPhone;

    @FXML
    private Label rigesterNum;

    @FXML
    private Label date;

    static ObservableList<String> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data = FormController.getData();

        info.setText(data.get(0));
        location.setText(data.get(1));
        patientName.setText(data.get(2));
        patientAge.setText(data.get(3));
        infoMidic.setText(data.get(4));
        clinicPhone.setText(data.get(5));
        rigesterNum.setText(data.get(6));
        date.setText(data.get(7));
        
    }
    
    @FXML
    void print(ActionEvent event)throws IOException {
        PrinterJob job = PrinterJob.createPrinterJob();
        boolean isPrinted = job.printPage(root);
        if (isPrinted) {
            job.endJob();
        }
        
        MainView.setRoot("form", 1000, 790);
    }
}
