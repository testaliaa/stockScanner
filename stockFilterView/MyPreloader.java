package stockFilterView;

import javafx.application.Preloader;
import javafx.application.Preloader.PreloaderNotification;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyPreloader extends Preloader {
    private Stage preloaderStage;
    private Scene scene;

    public MyPreloader() {
    }
    public void init() throws Exception {
        Parent root1 = (Parent)FXMLLoader.load(this.getClass().getResource("StockSplashScreen.fxml"));
        
        root1.getStylesheets().add("/stockFilterView/preloader.css");

        this.scene = new Scene(root1);
    }

    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        this.preloaderStage.setScene(this.scene);
        this.preloaderStage.initStyle(StageStyle.UNDECORATED);
        this.preloaderStage.show();
    }

    public void handleApplicationNotification(PreloaderNotification info) {
    	
        if (info instanceof ProgressNotification) {
            System.out.println("Value@ :" + ((ProgressNotification)info).getProgress());
           
            FXMLDocumentController.statProgressBar.setProgress(((ProgressNotification)info).getProgress());
        }

    }

    public void handleStateChangeNotification(StateChangeNotification info) {
        Type type = info.getType();
        switch(type) {
        case BEFORE_START:
            System.out.println("BEFORE_START");
            this.preloaderStage.hide();
        default:
        }
    }
}
