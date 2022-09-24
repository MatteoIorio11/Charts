package model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CoinExtractor {

	private static final String API ="https://api.minerstat.com/v2/coins?";
	public static List<Coin> getCoins() {
		 try {

	            URL url = new URL(CoinExtractor.API);

	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");
	            conn.connect();

	            //Check if connect is made
	            int responseCode = conn.getResponseCode();

	            // 200 OK
	            if (responseCode != 200) {
	                throw new RuntimeException("HttpResponseCode: " + responseCode);
	            } else {

	                StringBuilder informationString = new StringBuilder();
	                Scanner scanner = new Scanner(url.openStream());
	                while (scanner.hasNext()) {
	                    informationString.append(scanner.nextLine());
	                }
	                //Close the scanner
	                scanner.close();

	                

	                JSONParser parse = new JSONParser();
	                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
	                List<Coin> coins = new LinkedList<>();
	                
					for(int i = 0; i < dataObject.size(); i++) {
		                JSONObject countryData = (JSONObject) dataObject.get(i);
		                coins.add(new Coin(String.valueOf(countryData.get("coin"))));
					}
	                
	                return coins;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Collections.emptyList();
	        }
	}
}
