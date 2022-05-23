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
import javafx.scene.layout.VBox;

public class RachetaController implements Initializable {

    @FXML
    private VBox root;

    @FXML
    private Label patientName;

    @FXML
    private Label patientAge;

    @FXML
    private Label infoMidic;
    
    @FXML
     private Label date;
    static ObservableList<String> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data = FormController.getData();

        patientName.setText(data.get(0));
        patientAge.setText(data.get(1));
        infoMidic.setText(data.get(2));
        date.setText(data.get(3));
    }

    @FXML
    void print(ActionEvent event) throws IOException {
        PrinterJob job = PrinterJob.createPrinterJob();

       // boolean isShowPageSetup = job.showPageSetupDialog(MainView.getPrimaryStage());

        //if (isShowPageSetup) {
            boolean isPrinted = job.printPage(root);
            if (isPrinted) {
                job.endJob();
            }

        //}
          data.clear(); // مسح كل المحتويات

        MainView.setRoot("form", 1000, 790); // العودة الى الواجهة السابقة
    }
}
