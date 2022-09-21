package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Coin;



public class SingleCoin extends Thread implements Initializable{
	@FXML private ComboBox<Coin> coins;
	@FXML private AreaChart<Integer, Double> areaChart;
	private final static long SECONDS = 5000;
	private final Controller controller;
	private Coin actualCoin = null;
	private final Stage view;
	public SingleCoin(final Controller controller, final Stage view) {
		this.controller = controller;
		this.view = view;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(Coin.values());
		this.coins.setOnAction((e) -> {
            this.displayChart(this.coins.getSelectionModel().getSelectedItem());
       });
	}
	
	
	
	private void displayChart(final Coin selectedCoin) {
		this.actualCoin = selectedCoin;
		this.run();
	}
	
	@Override
	public void run() {
		final XYChart.Series<Integer, Double> serie = new XYChart.Series<>();

		try {
			while(true) {
				this.controller.getPrice(actualCoin).stream()
					.forEach((price) -> 
						serie.getData().add(
								new XYChart.Data<Integer, Double>(price.getKey(), price.getValue())));
				
				this.areaChart.getData().addAll(serie);
				
				
				this.view.setOnCloseRequest(e -> {
					return;
				});
				
				this.coins.setOnAction((e) -> {
					final Coin newCoin = this.coins.getSelectionModel().getSelectedItem();
					if(!newCoin.equals(this.actualCoin)) {
						actualCoin = newCoin;
						this.areaChart.getData().clear();
						this.controller.clear();
					}
		        });
				SingleCoin.sleep(SingleCoin.SECONDS);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}

