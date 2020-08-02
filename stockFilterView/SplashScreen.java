package stockFilterView;

import java.io.File;
import java.net.URISyntaxException;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader.ProgressNotification;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SplashScreen extends Application {
	private static final int COUNT_LIMIT = 4;

	public SplashScreen() {
	}
	public void start(Stage stage) throws Exception {
		Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("MainUI.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		  

	}

	public void init() throws Exception {
		Media media = null;
		try {
			media = new Media(getClass().getResource("/stockFilterView/hello-i-like-money.mp3").toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} 
		MediaPlayer musicplayer = new MediaPlayer(media);
		for(int i = 1; i <= 4; ++i) {
			double progress = (double)i / 4;
			if(progress==0.5) {
				musicplayer.play();
			}
			System.out.println("progress: " + progress);

			LauncherImpl.notifyPreloader(this, new ProgressNotification(progress));
			Thread.sleep(2000);
		}

	}

	public static void main(String[] args) {
		LauncherImpl.launchApplication(SplashScreen.class, MyPreloader.class, args);
	}
}
