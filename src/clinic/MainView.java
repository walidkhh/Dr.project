package clinic;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {

        scene = new Scene(loadFXML("login"),890,750);
        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    static void setRoot(String fxml, int width, int height) throws IOException {

        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getPrimaryStage(){
       return MainView.primaryStage;
    }

}
