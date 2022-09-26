package controller;

import java.time.Instant;
import model.LinearInterpolation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import model.Coin;
import model.CoinExtractor;
import model.Request;
import model.Status;

public class Controller {

	private final static long NOW = Instant.now().getEpochSecond();
	private final List<Map.Entry<Long, Double>> prices = new LinkedList<>();
	public Controller() {
		
	}
	
	public Double getPrice(final Coin inputCoin) {
		return Request.price(inputCoin);
	}
	
	public void interpolate(final List<Double> points, final Coin coin, final int value){
		double[] xs = new double[LinearInterpolation.ACCURACY];
		double[] ys = new double[LinearInterpolation.ACCURACY];
		int numbOfPoints = Integer.valueOf(value); 
		int[] interpo = new int[numbOfPoints];
		System.out.println(coin.getName());
		for(int i = 0; i < LinearInterpolation.ACCURACY; i++) {
			xs[i] = i;
			ys[i] = getPrice(coin); 	
			System.out.println(xs[i]);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("ERROR : " + e.getMessage());
			}
		}
		try {
		points.addAll(LinearInterpolation.interpolation(xs, ys, interpo));				
		}catch(Exception e) {
			System.out.println("ERROR : " + e.getMessage());
		}				
	}
	
	public List<Coin> values(){
		return CoinExtractor.getCoins();
	}
	
	public void clear() {
		this.prices.clear();
	}
	
}
