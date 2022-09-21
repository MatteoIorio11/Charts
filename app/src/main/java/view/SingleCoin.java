package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.ChoiceBox;

public class SingleCoin implements Initializable{
	@FXML private ChoiceBox coins;
	@FXML private AreaChart areaChart;
	private final Controller controller;
	public SingleCoin(final Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
