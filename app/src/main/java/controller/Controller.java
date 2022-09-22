package controller;

import java.time.Instant;
import java.time.LocalTime;
import java.util.AbstractMap;
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
	
	public void clear() {
		this.prices.clear();
	}
	
}
