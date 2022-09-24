package controller;

import java.time.Instant;
import model.LinearInterpolation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import model.Coin;
import model.CoinExtractor;
import model.Request;

public class Controller {

	private final static long NOW = Instant.now().getEpochSecond();
	private final List<Map.Entry<Long, Double>> prices = new LinkedList<>();
	public Controller() {
		
	}
	
	public Double getPrice(final Coin inputCoin) {
		return Request.price(inputCoin);
	}
	
	public List<Double> interpolate(final Coin coin, final int value){
		try {
		int numbOfPoints = Integer.valueOf(value);
		double[] xs = new double[LinearInterpolation.ACCURACY];
		double[] ys = new double[LinearInterpolation.ACCURACY];
		
		Stream.iterate(0, (i) -> i + 1)
			.limit(LinearInterpolation.ACCURACY)
			.forEach((i) -> {
				double price = this.getPrice(coin);
				ys[i] = Double.valueOf(String.valueOf(i));
				xs[i] = price;
			});
		
		int[] interpo = new int[numbOfPoints];
		return LinearInterpolation.interpolation(xs, ys, interpo);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public List<Coin> values(){
		return CoinExtractor.getCoins();
	}
	
	public void clear() {
		this.prices.clear();
	}
	
}
