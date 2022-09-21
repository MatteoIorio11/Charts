package view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Coin;
import model.Status;



public class SingleCoin implements Initializable{
	@FXML private ComboBox<Coin> coins;
	@FXML private AreaChart<Integer, Double> areaChart;
	private final static long SECONDS = 5;
	private final Controller controller;
	private Coin actualCoin = null;
	public SingleCoin(final Controller controller) {
		this.controller = controller;

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(Coin.values());
		this.coins.setOnAction((e) -> {
			
			var t = new Thread(new Runnable(){

				@Override
				public void run() {
					displayChart(coins.getSelectionModel().getSelectedItem());					
				}
			});
			t.start();
       });
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	private void displayChart(final Coin selectedCoin) {
		this.actualCoin = selectedCoin;
		final Status status = new Status();
		final Window view = this.coins.getScene().getWindow();

		while(!status.getStatus()) {
			final XYChart.Series<Integer, Double> serie = new XYChart.Series<>();
			this.controller.getPrice(actualCoin).stream()
				.forEach((price) -> 
					serie.getData().add(
							new XYChart.Data<Integer, Double>(price.getKey(), price.getValue())));

			this.areaChart.getData().addAll(serie);

			System.out.println("AOO");
			view.setOnCloseRequest(e -> {
				status.changeStatus();
			});
			
			this.coins.setOnAction((e) -> {
				final Coin newCoin = this.coins.getSelectionModel().getSelectedItem();
				if(!newCoin.equals(this.actualCoin)) {
					actualCoin = newCoin;
					this.areaChart.getData().clear();
					this.controller.clear();
				}
	        });
			status.delay(SingleCoin.SECONDS);
			this.areaChart.getData().clear();
	
		}
	}
	
	
}

