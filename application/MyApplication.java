package application;


import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import preloader.MyPreloader;

/**
 * Application class.
 */
public class MyApplication extends Application {

    @Override
    public void init() {
        try {
            Preloader.ProgressNotification pn;
            for (int i = 1; i <= 100; i++) {
                    Thread.sleep(50);
                    pn = new Preloader.ProgressNotification(i/100.0);
                    notifyPreloader(pn);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        primaryStage.setTitle("My Appplication");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        LauncherImpl.launchApplication(MyApplication.class, MyPreloader.class, args);

        //System.setProperty("javafx.preloader", "preloader.MyPreloader");  //Another option to call the preloader
        //launch(args);
    }
}