package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminpagesController implements Initializable{
        @FXML
    private TableView<ModelTable> TableView;


    @FXML
    private TableColumn<ModelTable, String> col_username;

    @FXML
    private TableColumn<ModelTable, String> col_password;

    @FXML
    private TableColumn<ModelTable, String> col_prev;
    

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<?> privileges;

    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void backTo(MouseEvent event) throws IOException {
        MainView.setRoot("chosse", 950, 760);
    }

    @FXML
    void deleteButton(ActionEvent event) {

    }

    @FXML
    void editbutton(ActionEvent event) {

    }
    ObservableList<ModelTable> oblist= FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            try {
                Connection con = Database.getConnection();
                
                ResultSet rs = con.createStatement().executeQuery("select * from login  ");
                while(rs.next()){
                    oblist.add(new ModelTable(rs.getString("user_name"), rs.getString("pass_word"), rs.getString("user_type")));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(AdminpagesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminpagesController.class.getName()).log(Level.SEVERE, null, ex);
            }
                col_username.setCellValueFactory(new PropertyValueFactory<>("Username"));
                col_password.setCellValueFactory(new PropertyValueFactory<>("Password"));
                col_prev.setCellValueFactory(new PropertyValueFactory<>("Prev"));
                
                TableView.setItems(oblist);
                        
    }  
    

}
