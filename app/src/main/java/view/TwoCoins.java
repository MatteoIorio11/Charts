package view;

import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
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
  private final static long MILLI_SECONDS = 5000;


  @FXML private ComboBox<Coin> firstCoin;
  @FXML private ComboBox<Coin> secondCoin;
  @FXML private StackedAreaChart<Integer, Double> areaChart;
  private final XYChart.Series<Integer, Double> serieFirst = new XYChart.Series<>();
  private final XYChart.Series<Integer, Double> serieSecond = new XYChart.Series<>();
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
    this.areaChart.setAnimated(false);
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
        // put random number with current time
        serieFirst.getData().add(new XYChart.Data<Integer,Double>(numberOfPoints++, controller.getPrice(actualCoinFirst)));
        serieSecond.getData().add(new XYChart.Data<Integer,Double>(numberOfPoints++, controller.getPrice(actualCoinSecond)));
      });
    }, 0, 1, TimeUnit.SECONDS);
  }
}