package view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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
  private final static long MILLI_SECONDS = 5000;
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


  private synchronized void displayChart(final Coin coin1, final Coin coin2, Runnable r) {
    System.out.println("Attempt display");
    this.actualCoinFirst = coin1;
    this.actualCoinSecond = coin2;
    final Status status = new Status();
    final Window view = this.firstCoin.getScene().getWindow();

    this.firstCoin.setOnAction((e) -> {
      final Coin newCoin = this.firstCoin.getSelectionModel().getSelectedItem();
      if (!newCoin.equals(this.actualCoinFirst)) {
        actualCoinFirst = newCoin;
        this.controller.clear();
      }
    });
    this.secondCoin.setOnAction((e) -> {
      final Coin newCoin = this.secondCoin.getSelectionModel().getSelectedItem();
      if (!newCoin.equals(this.actualCoinSecond)) {
        actualCoinSecond = newCoin;
        this.controller.clear();
      }
    });
    view.setOnCloseRequest(e -> {
      status.changeStatus();
      this.controller.clear();
    });


    while (!status.getStatus()) {
      this.controller.getPrice(actualCoinFirst);
      this.controller.getPrice(actualCoinSecond);
      this.areaChart.getData().add(serieFirst);
      this.areaChart.getData().add(serieSecond);
      try {
        Thread.sleep(TwoCoins.MILLI_SECONDS);
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      System.out.println(this.serieFirst.getData().size());
      System.out.println(this.serieSecond.getData().size());
      this.areaChart.getData().remove(serieFirst);
      this.areaChart.getData().remove(serieSecond);
    }
  }
}

