package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainPage {
	@FXML private Button singleCoint;
	@FXML private Button twoCoints;
	
	private final Controller controller;
	
	public MainPage(final Controller controller) {
		this.controller = controller;
	}
}
