package view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Coin;
import model.Status;



public class SingleCoin implements Initializable{

	private final static long MILLI_SECONDS = 5000;

	
	@FXML private ComboBox<Coin> coins;
	@FXML private StackedAreaChart<Long, Double> areaChart;
	private final XYChart.Series<Long, Double> serie = new XYChart.Series<>();
	private final Controller controller;
	private Coin actualCoin = null;
	public SingleCoin(final Controller controller) {
		this.controller = controller;

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(Coin.values());
		this.areaChart.setAnimated(false);
		this.coins.setOnAction((e) -> {
			this.actualCoin = this.coins.getSelectionModel().getSelectedItem();
       });
		
	}
	
	@FXML
	public void launchHandler(final ActionEvent action) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				displayChart(actualCoin, this);
			}
			
		});

	}
	
	private synchronized void 	displayChart(final Coin selectedCoin, Runnable r) {
		this.actualCoin = selectedCoin;
		final Status status = new Status();
		final Window view = this.coins.getScene().getWindow();
		
		this.coins.setOnAction((e) -> {
			final Coin newCoin = this.coins.getSelectionModel().getSelectedItem();
			if(!newCoin.equals(this.actualCoin)) {
				actualCoin = newCoin;
				this.controller.clear();
			}
        });
		view.setOnCloseRequest(e -> {
			status.changeStatus();
			this.controller.clear();
		});
				

		try {
			while(!status.getStatus()) {
				
				this.controller.getPrice(actualCoin).stream().forEach( price -> 
					serie.getData().add(new XYChart.Data<Long, Double>(price.getKey(), price.getValue())));
				this.areaChart.getData().add(serie);
				
				r.wait(SingleCoin.MILLI_SECONDS);				
						
				System.out.println(this.serie.getData().size());	
				this.areaChart.getData().remove(serie);

			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
}

