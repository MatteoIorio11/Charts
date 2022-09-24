package controller;

import java.time.Instant;
import model.LinearInterpolation;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Coin;
import model.Request;

public class Controller {

	private final static long NOW = Instant.now().getEpochSecond();
	private final List<Map.Entry<Long, Double>> prices = new LinkedList<>();
	public Controller() {
		
	}
	
	public Double getPrice(final Coin inputCoin) {
		return Request.price(inputCoin);
	}
	
	public List<Double> interpolate(final double[] xs, final double[] ys, final int[] points){
		return LinearInterpolation.interpolation(xs, ys, points);
	}
	
	public void clear() {
		this.prices.clear();
	}
	
}
