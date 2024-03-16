package guru.springframework;

import java.util.HashMap;

public class Bank {

    private HashMap<Pair, Integer> rateMap = new HashMap<Pair, Integer>();

    Money reduce(Expression source, String toCurrency){
        return source.reduce(this, toCurrency);
    }

    // Get the rate between two currencies (tasa o tarifa)
    public int rate(String from, String to){
        if(from.equals(to)) {
            return 1;
        }
        return rateMap.get(new Pair(from, to));
    }

    /**
     * Add a new exchange rate.
     * rate ( tarifa o costo de cambio) example: How much is 1 dollar to 1 franc
     * @param from
     * @param to
     * @param rate
     */
    public void addRate(String from, String to, int rate) {
        rateMap.put(new Pair(from, to), rate );
    }
}
