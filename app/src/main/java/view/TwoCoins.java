package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Window;
import model.Coin;
import model.Status;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TwoCoins implements Initializable {


  @FXML private ComboBox<Coin> firstCoin;
  @FXML private ComboBox<Coin> secondCoin;
  @FXML private Button launch;
  @FXML private LineChart<String, Double> areaChart;
  private final XYChart.Series<String, Double> serieFirst = new XYChart.Series<>();
  private final XYChart.Series<String, Double> serieSecond = new XYChart.Series<>();
  private final Controller controller;
  private Coin actualCoinFirst = null;
  private Coin actualCoinSecond = null;
  private int numberOfPoints = 0;
  public TwoCoins(final Controller controller) {
    this.controller = controller;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Initializing.........");
    this.firstCoin.getItems().addAll(Coin.values());
    this.secondCoin.getItems().addAll(Coin.values());
    areaChart.getData().add(serieFirst);
    areaChart.getData().add(serieSecond);
    this.firstCoin.setOnAction((e) -> this.actualCoinFirst = this.firstCoin.getSelectionModel().getSelectedItem());
    this.secondCoin.setOnAction((e) -> this.actualCoinSecond = this.secondCoin.getSelectionModel().getSelectedItem());
    System.out.println("Initialized");
  }

  @FXML
  public void launchHandler(final ActionEvent action) {
    ScheduledExecutorService scheduledExecutorService;
    scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      // Update the chart
      Platform.runLater(() -> {
    	 numberOfPoints++;
    	var firstCoin = controller.getPrice(actualCoinFirst);
    	var secondCoin = controller.getPrice(actualCoinSecond);
    	serieFirst.setName(actualCoinFirst.getName());
    	serieSecond.setName(actualCoinSecond.getName());
    	System.out.println("FIRST COIN : "+ firstCoin + " SECOND COIN :" + secondCoin);
        serieFirst.getData().add(new XYChart.Data<String,Double>(String.valueOf(numberOfPoints), firstCoin));
        serieSecond.getData().add(new XYChart.Data<String,Double>(String.valueOf(numberOfPoints), secondCoin));
        if(serieFirst.getData().size() % 10 == 0) {
        	serieFirst.getData().remove(0);
        }
        if(serieSecond.getData().size() % 10 == 0) {
        	serieSecond.getData().remove(0);
        }
      });
    }, 0, 1, TimeUnit.SECONDS);
    this.launch.setVisible(false);
    this.launch.getScene().getWindow().setOnCloseRequest((e) -> {
    	scheduledExecutorService.shutdown();	
    });
    
  }
}