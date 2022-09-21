package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import model.Coin;



public class SingleCoin extends Thread implements Initializable{
	@FXML private ChoiceBox<Coin> coins;
	@FXML private AreaChart<Integer, Double> areaChart;
	private final static long SECONDS = 5000;
	private final Controller controller;
	public SingleCoin(final Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(Coin.values());
	}
	
	
	/*
	@Override
	@FXML
	public void selectCoinHandler(final ActionEvent action) {
		this.displayChart(this.coins.getValue());
	}
	*/
	
	private void displayChart(final Coin selectedCoin) {
		final XYChart.Series<Integer, Double> serie = new XYChart.Series<>();
		serie.setName(selectedCoin.getName());
			try {
				while(true) {
					this.controller.getPrice(selectedCoin).stream()
						.forEach((price) -> 
							serie.getData().add(
									new XYChart.Data<Integer, Double>(price.getKey(), price.getValue())));
					SingleCoin.sleep(SingleCoin.SECONDS);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}

