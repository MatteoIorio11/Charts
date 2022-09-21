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
	
	public List<Map.Entry<Long, Double>> getPrice(final Coin inputCoin) {
		var entry = new AbstractMap.SimpleEntry<Long, Double>(Instant.now().getEpochSecond() - NOW,Request.price(inputCoin));
		this.prices.add(entry);
		return this.prices;
	}
	
	public void clear() {
		this.prices.clear();
	}
	
}
