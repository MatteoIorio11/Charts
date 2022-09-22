package model;

import java.util.Objects;

public class QueryBuilder implements Builder {
	private final static String API = "https://api.minerstat.com/v2/coins?";
	private String list ="list=";
	
	public QueryBuilder() {
		
	}
	
	@Override
	public Builder selectCoin(final Coin coin) {
		if(Objects.isNull(coin)) {
			throw new IllegalArgumentException("The coin must be selected");
		}
		if(this.list.equals("list=")) {
			this.list += coin.getName();
		}
		return this;
	}
	
	@Override
	public String build() {
		var out =  API + list;
		list = "list=";
		return out;
	}
	
}
