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
		this.areaChart.setAnimated(false);
		this.areaChart.getData().add(serie1);
		this.areaChart.getData().add(serie2);
		this.firstCoin.getItems().addAll(Coin.values());
		this.secondCoin.getItems().addAll(Coin.values());
		
		this.firstCoin.setOnAction((e) -> {
			this.coin1 = this.firstCoin.getSelectionModel().getSelectedItem();
       });
		this.secondCoin.setOnAction((e) -> {
			this.coin2 = this.secondCoin.getSelectionModel().getSelectedItem();
       });
	}
	
	@FXML
	public void launchHandler(final ActionEvent action) {
		 ScheduledExecutorService scheduledExecutorService;
	        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		 scheduledExecutorService.scheduleAtFixedRate(() -> {
	            Platform.runLater(() -> {
	            	serie1.getData().add(new XYChart.Data<Integer,Double>(numberOfPoints++, controller.getPrice(coin1)));
	                serie2.getData().add(new XYChart.Data<Integer,Double>(numberOfPoints++, controller.getPrice(coin2)));
	            });
	        }, 0, 1, TimeUnit.SECONDS);
		
		 this.launch.setVisible(false);
	}
	

}
