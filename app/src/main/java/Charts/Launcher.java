package Charts;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.Loader;
import view.MainPage;
import view.Page;

public class Launcher extends Application {

    private static final int MIN_HEIGHT = 100;
    private static final int MIN_WIDTH = 400;
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Pane pane = Loader.loadPane(Page.MAIN.getPath(), new MainPage(new Controller()));
        final Scene scene = new Scene(pane, 700, 500);
        primaryStage.setTitle("Coin");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(Launcher.MIN_HEIGHT);
        primaryStage.setMinWidth(Launcher.MIN_WIDTH);
        primaryStage.show();
    }
}
