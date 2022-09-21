package model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Request {
	public static void price(Coin c) {
		 try {
	            //Public API:
	            //https://www.metaweather.com/api/location/search/?query=<CITY>
	            //https://www.metaweather.com/api/location/44418/
			 	QueryBuilder queryBuilder = new QueryBuilder();
			 	queryBuilder.selectCoin(c);
			 	var a = queryBuilder.build();
			 	System.out.println(a);
	            URL url = new URL(a);

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

	                System.out.println(informationString);


	                JSONParser parse = new JSONParser();
	                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

	                //Get the first JSON object in the JSON array
	                System.out.println(dataObject.get(0));

	                JSONObject countryData = (JSONObject) dataObject.get(0);

	                System.out.println(countryData.get("woeid"));

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
