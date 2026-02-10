package com.example;

// Imports 
import java.util.Currency;
import java.util.Map;
import java.util.List;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CurrencyConverter {
    
    // Define a method to convert currency from one type to another using the current ratio.
    public double convert(double input, String inputType, String outputType){
        // First get the conversion rate between the input and output currencies.
        double conversionRate = getConversionRate(inputType, outputType);

        // Then calculate the conversion and return the result.
        return input * conversionRate;
    }

    // Get the name of the currency based on its code.
    public String getCurrencyName(String currencyCode){
        Map<String, String> cryptos = Map.of("BTC", "Bitcoin", "ETH", "Ethereum", "BNB", "Binance Coin", "ADA", "Cardano", "XRP", "Ripple", "SOL", "Solana", "DOT", "Polkadot", "AVAX", "Avalanche"); 
        
        // Add a separate check for cryptocurrencies since they're not in the Currency library.
        if(cryptos.containsKey(currencyCode.toUpperCase())){
            return cryptos.get(currencyCode.toUpperCase());
        }else{
            try{
                // Change the input to uppercase to avoid case sensitivity issues.
                Currency currency = Currency.getInstance(currencyCode.toUpperCase());
                return currency.getDisplayName();
            }catch(IllegalArgumentException e){
                return "Unsupported Currency";
            }
        }
    }

    // Define a method to get the conversion rate between two currencies, includes digital currency.
    private double getConversionRate(String inputType, String outputType) {
        List<String> especialCurrencies = List.of("XAG", "XAU", "XBA", "XBB", "XBC", "XBD", "XPT", "XPD", 
                                                  "XSU", "BTC", "ETH", "BNB", "ADA", "XRP", "SOL", "DOT", "AVAX");
        // If the input or output is a especial currency, the conversion rate is obtained from a different source.
        if(especialCurrencies.contains(inputType.toUpperCase()) || especialCurrencies.contains(outputType.toUpperCase())){
            try{
                // To be implemented when a free and reliable api for it is found or made.
                System.out.println("Conversion for special currency. This feature is not yet implemented.");
                return 1.0;
            }catch(Exception e){
                System.out.println("Error fetching conversion rate for special currency: " + e.getMessage());
                return 0.0;
            }
        }
        // If not especial, the conversion is obtained using ExchangeRate-Api from: https://www.exchangerate-api.com/
        else{
            try{
                // Get the API key from environment variables for secure usage.
                String apiKey = System.getenv("EXCHANGERATE-API-KEY");
                
                // Define the API-KEY  using enviromment variables for secure usage.
                String apiUrl = ("https://v6.exchangerate-api.com/v6/"+ apiKey +"/pair/" + inputType.toUpperCase() + "/" + outputType.toUpperCase());
                
                // Make a request to the API
                URL url = new URI(apiUrl).toURL(); 
                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                request.connect();

                // Convert the response to a JSON object and get the conversion rate.
                JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
                JsonObject jsonObject = root.getAsJsonObject();
                double conversionRate = jsonObject.get("conversion_rate").getAsDouble();

                return conversionRate;
            } catch(Exception e){
                System.out.println("Error fetching conversion rate: " + e.getMessage());
                return 0.0;
            }
        }
    }
}
