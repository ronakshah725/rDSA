package org.ronak.ds.datastructures;
import java.util.*;

public class CurrencyExchange {

    public record CurrencyPair(String from, String to, double rate) {}


    public double convertCurrency(List<CurrencyPair> currencyRates, String fromCurrency, String toCurrency) {

        Map<String, Map<String, Double>> fromCurrencyMap = new HashMap<>();

        for(CurrencyPair pair : currencyRates){
            fromCurrencyMap.putIfAbsent(pair.from, new HashMap<>());
            fromCurrencyMap.putIfAbsent(pair.to, new HashMap<>());
            fromCurrencyMap.get(pair.from).put(pair.to, pair.rate);

            CurrencyPair reversePair = new CurrencyPair(pair.to, pair.from, 1 / pair.rate);
            fromCurrencyMap.get(reversePair.from).put(reversePair.to, reversePair.rate);
        }

        if(!fromCurrencyMap.containsKey(fromCurrency) || !fromCurrencyMap.containsKey(toCurrency)){
            return -1;
        }

        Set<String> visitedCurrency = new HashSet<>();

        Queue<String> q = new LinkedList<>();

        q.addAll(fromCurrencyMap.get(fromCurrency).keySet());
        visitedCurrency.add(fromCurrency);

        double conversion  = 1.0;

        while(!q.isEmpty()){
            String adjCurrency = q.poll();
            Map<String, Double> adjCurrencies = fromCurrencyMap.get(adjCurrency);

            if(!visitedCurrency.contains(adjCurrency)){
                conversion *= adjCurrencies.get(adjCurrency);
                visitedCurrency.add(adjCurrency);
                if(adjCurrency.equals(toCurrency)){
                    return conversion;
                }

                q.addAll(fromCurrencyMap.get(adjCurrency).keySet());
            }   
        }
        
        return -1.0;
    }

    public static void main(String[] args) {
// USD -> JPY -> AUD
// ^       ^
// |    /
// YEN
        List<CurrencyPair> pairs = List.of( 
            new CurrencyPair("USD", "JPY", 110),
            new CurrencyPair("USD", "AUD", 1.45),
            new CurrencyPair("JPY", "GBP", 0.0070),
            new CurrencyPair("GBP", "AUD", 1.89)
        ); 
        
        CurrencyExchange exchange = new CurrencyExchange();
        assert exchange.convertCurrency(pairs, "JPY", "AUD") == 1.45 * 110 * 0.0070 : "incorrect connversion"; ;
    }
}


