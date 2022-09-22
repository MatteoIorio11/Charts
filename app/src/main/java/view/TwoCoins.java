package view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Coin;

public class TwoCoins implements Initializable{
	
	@FXML private ComboBox<Coin> firstCoin;
	@FXML private ComboBox<Coin> secondCoin;
	@FXML private Button launch;
	@FXML private StackedAreaChart<Integer, Double> areaChart;
	private final XYChart.Series<Integer, Double> serie1 = new XYChart.Series<>();
	private final XYChart.Series<Integer, Double> serie2 = new XYChart.Series<>();

	private int numberOfPoints = 0;
	private Coin coin1 = null;
	private Coin coin2 = null;
	private final Controller controller;
	
	public TwoCoins(final Controller controller) {

		this.controller = controller;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	public void launchHandler(final ActionEvent action) {

	}
	

}
