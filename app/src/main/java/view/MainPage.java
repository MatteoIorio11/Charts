package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainPage implements Initializable{
	@FXML private BorderPane mainPane;
	@FXML private Button singleCoint;
	@FXML private Button twoCoints;
	
	private final Controller controller;
	
	public MainPage(final Controller controller) {
		this.controller = controller;
	}
	private <X> void loadPage(final Page page, final X controller) {
        Pane pane;
        try {
            pane = Loader.loadPane(page.getPath(), controller);
            this.mainPane.setCenter(pane);
        } catch (IOException e) {
            final Alert errorMessage = new Alert(AlertType.ERROR);
            errorMessage.setContentText(e.getMessage());
            errorMessage.showAndWait();
        }
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
}
