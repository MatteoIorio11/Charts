package view;

public enum Page {

	MAIN("/pages/main.fxml"),
	SINGLE_COIN("/pages/SingleCoin/singleCoin.fxml"),
	TWO_COINS("/pages/TwoCoins/twoCoins.fxml");
	
	private String name;
	
	Page(final String name){
		this.name = name;
	}
	
	public String getPath() {
		return this.name;
	}
}
