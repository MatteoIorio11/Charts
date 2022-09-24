package view;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Coin;

public class InterpolationView implements Initializable{

	@FXML private Button launch;
	@FXML private TextField pointsArea;
	@FXML private LineChart<String, Double> lineChart;
	@FXML private ComboBox<Coin> coins;
	private final XYChart.Series<String, Double> serie = new XYChart.Series<>();
	private final Controller controller;
	private Coin actualCoin = null;
	public InterpolationView(final Controller controller) {
		this.controller = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.coins.getItems().addAll(this.controller.values());
		lineChart.getData().add(serie);
		this.coins.setOnAction((e) -> {
			this.actualCoin = this.coins.getSelectionModel().getSelectedItem();
       });
	}
	
	@FXML
	public void launchHandler(final ActionEvent event) {
		if(pointsArea.getText().equals("")) {
			return;
		}else {
			if(Objects.isNull(actualCoin)) {
				return;
			}
			String value = pointsArea.getText();
			try {
				int numb = Integer.valueOf(value);
				List<Double> prices = this.controller.interpolate(actualCoin, numb);
				Stream.iterate(0,  (i) -> i + 1).limit(prices.size()).forEach((i) -> {
					serie.getData().add(new XYChart.Data<String, Double>(String.valueOf(i), prices.get(i)));
				});
			}catch(Exception e) {
				return;
			}
		}
	}
	
	
}
