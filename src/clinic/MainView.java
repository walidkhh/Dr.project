package clinic;

import com.sun.javafx.application.LauncherImpl;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {
    private static final int COUNT_LIMIT = 5;
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {

        scene = new Scene(loadFXML("login"),910,700);
        primaryStage = stage;
        primaryStage.setScene(scene);
       // primaryStage.setMaximized(true);
        primaryStage.show();

    }
       @Override
    public void init() throws Exception {       
        
        // Perform some heavy lifting (i.e. database start, check for application updates, etc. )
        for (int i = 1; i <= COUNT_LIMIT; i++) {
            double progress =(double) i/5;
            System.out.println("progress: " +  progress);            
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(500);
        }
        
    }

    static void setRoot(String fxml, int width, int height) throws IOException {

        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
  //      primaryStage.setMaximized(true);
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainView.class, MyPreloader.class, args);
    }
    
    public static Stage getPrimaryStage(){
       return MainView.primaryStage;
    }
    
    
   
}
