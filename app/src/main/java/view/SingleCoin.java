package view;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Coin;
import model.Status;



public class SingleCoin implements Initializable{

	private final static long MILLI_SECONDS = 5000;

	
	@FXML private ComboBox<Coin> coins;
	@FXML private Button launch;
	@FXML private StackedAreaChart<Integer, Double> areaChart;
	private final XYChart.Series<Integer, Double> serie = new XYChart.Series<>();
	private final Controller controller;
	private Coin actualCoin = null;
	private int numberOfPoints = 0;
	public SingleCoin(final Controller controller) {
		this.controller = controller;

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(Coin.values());
		this.areaChart.setAnimated(false);
		areaChart.getData().add(serie);
		this.coins.setOnAction((e) -> {
			this.actualCoin = this.coins.getSelectionModel().getSelectedItem();
       });
		
	}
	
	@FXML
	public void launchHandler(final ActionEvent action) {
		 ScheduledExecutorService scheduledExecutorService;
	        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		 scheduledExecutorService.scheduleAtFixedRate(() -> {
	            // Update the chart
	            Platform.runLater(() -> {
	                // put random number with current time
	                serie.getData().add(new XYChart.Data<Integer,Double>(numberOfPoints++, controller.getPrice(actualCoin)));
	            });
	        }, 0, 1, TimeUnit.SECONDS);
		
		 this.launch.setVisible(false);

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
				

		while(!status.getStatus()) {
			
			this.controller.getPrice(actualCoin);
			this.areaChart.getData().add(serie);
			try {
				Thread.sleep(SingleCoin.MILLI_SECONDS);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}				
					
			System.out.println(this.serie.getData().size());	
			this.areaChart.getData().remove(serie);

		}
		
		
	}
	
	
}

