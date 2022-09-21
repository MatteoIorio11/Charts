package controller;

import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Coin;
import model.Request;

public class Controller {
	
	private final List<Map.Entry<Integer, Double>> prices = new LinkedList<>();
	private final static int NOW = LocalTime.now().getSecond();
	public Controller() {
		
	}
	
	public List<Map.Entry<Integer, Double>> getPrice(final Coin inputCoin) {
		this.prices.add(
				new AbstractMap.SimpleEntry<>(LocalTime.now().getSecond() - NOW,Request.price(inputCoin)));
		return List.copyOf(this.prices);
	}
	
}
