package model;

public interface Builder {

	Builder selectCoin(Coin coin);

	Builder peekRandom();

	String build();

}