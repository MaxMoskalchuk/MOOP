package ua.univ.lab2.TaskB.Necklace;

/**
 * Created by Asus on 04.11.2018.
 *
 */
import ua.univ.lab2.TaskB.Stones.GemStones;

import java.util.Collections;
import java.util.Comparator;
import javafx.util.Pair;
import java.util.ArrayList;

public final class NecklaceUtils {

    static class SortGemByPrice implements Comparator<GemStones> {
        public int compare(GemStones s1, GemStones s2) {
            return s1.getPrice() - s2.getPrice();
        }
    }

    public static void sortStones(Necklace necklace) {
        Collections.sort(necklace.getStones(),
                new SortGemByPrice());
    }

    public static Pair<Integer,Double> getTotalPrice(Necklace necklace) {
        int price = 0;
        double weight = 0;
        for(GemStones cl : necklace.getStones()) {
            price += cl.getPrice();
            weight += cl.getWeight();
        }
        return new Pair<>(price,weight);
    }

    public static ArrayList<GemStones> getStones(Necklace necklace, int lowerBound, int upperBound) {
        ArrayList<GemStones> result = new ArrayList<>();
        for(GemStones cl : necklace.getStones()) {
            if(cl.getTransparency() >= lowerBound && cl.getTransparency() <= upperBound) {
                result.add(cl);
            }
        }
        return result;
    }
}
