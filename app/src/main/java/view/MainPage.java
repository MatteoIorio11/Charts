package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainPage implements Initializable{
	@FXML private Button singleCoin;
	@FXML private Button twoCoins;
	
	private final Controller controller;
	
	public MainPage(final Controller controller) {
		this.controller = controller;
	}

	
	public static <X> Stage loadStage(final String path, final String title, final X controller, final double minHeight, final double minWidth) throws IOException {
        final Pane pane;
        final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        final double xSize =  screenBounds.getMaxX() /	 2;
        final double ySize = screenBounds.getMaxY() / 2;
        final Stage newWindow = new Stage();
        newWindow.setMinHeight(minHeight);
        newWindow.setMinWidth(minWidth);
        final var loader = new FXMLLoader(Loader.class.getResource(path));
        loader.setController(controller);

        pane = loader.load();

        final Scene secondScene = new Scene(pane, xSize, ySize);
        newWindow.setTitle(title);
        newWindow.setScene(secondScene);
        return newWindow;
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
    @FXML
    public void btnSingleCoin(final ActionEvent event) throws IOException {
    	var stage = loadStage(Page.SINGLE_COIN.getPath(), "SingleCoin", new SingleCoin(controller), 100, 100);
		stage.show();
    }
    
    @FXML
    public void btnTwoCoins(final ActionEvent event) throws IOException {
        var stage = this.loadStage(Page.TWO_COINS.getPath(), "TwoCoins", this.controller, 100, 100);
        stage.show();
    }
}
