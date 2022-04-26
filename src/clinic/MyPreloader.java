/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinic;

import java.io.IOException;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {

    private Stage preloaderStage;
    private Scene scene;
    
     public MyPreloader() {
         
        
    }
      @Override
    public void init() throws Exception {               
                                         
    Parent root1 = FXMLLoader.load(getClass().getResource("Splash_Screen.fxml"));               
    scene = new Scene(root1);                       
                
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         this.preloaderStage = primaryStage;

        // Set preloader scene and show stage.
        preloaderStage.setScene(scene);  
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.show();


    
}
     @Override
    public void handleApplicationNotification  (Preloader.PreloaderNotification info)  {
         
               if (info instanceof Preloader.ProgressNotification) {
         //Splash_ScreenController.label.setText("Loading "+((ProgressNotification) info).getProgress()*100 + "%");
          System.out.println("Value@ :" + ((Preloader.ProgressNotification) info).getProgress());
          // Splash_ScreenController.statProgressBar.setProgress(((ProgressNotification) info).getProgress());
        }
        
               
        
    }
     @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
      
        Preloader.StateChangeNotification.Type type = info.getType();
        switch (type) {
            
            case BEFORE_START:
                // Called after MyApplication#init and before MyApplication#start is called.
                System.out.println("BEFORE_START");
                preloaderStage.hide();
                break;
        }
        
        
    }}


