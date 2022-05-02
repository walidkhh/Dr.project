package clinic;

import java.io.IOException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FormController {

    @FXML
    private TextArea docId;

    @FXML
    private TextField addressid;

    @FXML
    private TextField paitentNameId;

    @FXML
    private TextField ageId;

    @FXML
    private TextArea aboudPaitentId;

    @FXML
    private DatePicker dateId;

    @FXML
    private TextField phoneNumId;

    @FXML
    private TextField rigesterId;
    static ObservableList<String> data = FXCollections.observableArrayList();

    @FXML
    void print(ActionEvent event) throws IOException {

        data.addAll(
                docId.getText(),
                addressid.getText(),
                paitentNameId.getText(),
                ageId.getText(),
                aboudPaitentId.getText(),
                phoneNumId.getText(),
                rigesterId.getText(),
                dateId.getValue().toString());

        MainView.setRoot("racheta", 1000, 760);

    }

    public static ObservableList<String> getData() {
        return data;
    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

}
