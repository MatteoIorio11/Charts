package view;

public enum Page {

	MAIN("main.fxml"),
	SINGLE_COIN("singleCoin.fxml"),
	TWO_COINS("twoCoins.fxml");
	
	private String name;
	
	Page(final String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
