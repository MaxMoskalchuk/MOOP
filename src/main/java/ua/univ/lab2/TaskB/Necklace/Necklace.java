package ua.univ.lab2.TaskB.Necklace;

/**
 * Created by Asus on 04.11.2018.
 */


import ua.univ.lab2.TaskB.Stones.GemStones;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
import java.lang.*;
import java.io.*;

public final class Necklace {
    String name;
    List<GemStones> stones = new ArrayList<>();

    public Necklace(String name)
    {
        this.name = name;
    }

    public void addStone(GemStones stone)
    {
        stones.add(stone);
    }

    public List<GemStones> getStones()
    {
        return stones;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Necklace: \n\t")
                .append("Name: ").append(name).append("\n\t")
                .append("Stones:");
        for(GemStones cl : stones) {
            sb.append("\n\t").append(cl.toString());
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        String nameNecklace = name;
        long hash = 111222444888L;
        long pow = 153;
        final long mod = 5000000000053L, base = 153;
        for(int i = 0 ; i < nameNecklace.length() ; ++i) {
            hash = (hash * pow + (int)nameNecklace.charAt(i)) % mod;
            if (hash < 0) hash += mod;
            pow = (pow * base) % mod;
            if (pow < 0)  pow  += mod;
        }
        return (int)(hash % Integer.MAX_VALUE);
    }
    class SortGem implements Comparator<GemStones> {
        public int compare(GemStones s1, GemStones s2) {
            return s1.hashCode() - s2.hashCode();
        }
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Necklace)) return false;
        Necklace other = (Necklace) obj;
        boolean res = name.equalsIgnoreCase(other.name)
                && stones.size() == other.stones.size();
        if(res == false) return false;
        Collections.sort(stones, new SortGem());
        Collections.sort(other.stones, new SortGem());
        for(int i = 0; i < stones.size(); ++i) {
            if(!stones.get(i).equals(other.stones.get(i))) {
                return false;
            }
        }
        return true;
    }
}

