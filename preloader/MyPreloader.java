package preloader;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Preloader class.
 */
public class MyPreloader extends Preloader {

    private Stage stage;
    private ProgressBar progressBar;
    private Label progressMsg, title;
    private StackPane progressPane;
    private BorderPane root;

    @Override
    public void start(Stage stage) {
        this.root = new BorderPane();
        this.root.getStylesheets().add("/preloader/preloader.css");

        this.stage = stage;
        this.stage.setTitle("My Preloader");
        this.stage.setScene(new Scene(this.root, 550, 350));
        this.stage.initStyle(StageStyle.TRANSPARENT);

        this.title = new Label("Stock Scanner");
        this.title.setId("title");
        this.root.setTop(this.title);

        this.progressBar = new ProgressBar();
        this.progressBar.setPrefWidth(300);
        this.progressMsg = new Label("0%");
        this.progressPane = new StackPane(this.progressBar, this.progressMsg);
        this.root.setCenter(this.progressPane);

        this.stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        if (info.getType() == StateChangeNotification.Type.BEFORE_START) {
            this.stage.hide();
        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if (info instanceof Preloader.ProgressNotification) {
            Preloader.ProgressNotification pn = (Preloader.ProgressNotification) info;
            double progress = pn.getProgress();
            this.progressBar.setProgress(progress);
            if (progress >= 0.5) {
                this.progressMsg.setStyle("-fx-text-fill: white");
            }
            this.progressMsg.setText((int) (progress * 100) + "%");
        }
    }
}