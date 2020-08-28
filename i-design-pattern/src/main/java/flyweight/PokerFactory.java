package flyweight;

import java.util.*;

/**
 * @Author: Jeremy
 * @Date: 2020/8/28 21:16
 */
public final class PokerFactory {
    private static final Map<String, Poker> POKER_MAP = new HashMap<>();

    static {
        init();
    }

    public static void init() {
        for (int i = 1; i <= 13; i++) {
            POKER_MAP.put(String.format("%d-red-diamond", i), new Poker(i, Poker.Color.RED, Poker.Shape.DIAMOND));
            POKER_MAP.put(String.format("%d-red-peach", i), new Poker(i, Poker.Color.RED, Poker.Shape.PEACH));
            POKER_MAP.put(String.format("%d-black-plum", i), new Poker(i, Poker.Color.BLACK, Poker.Shape.PLUM));
            POKER_MAP.put(String.format("%d-black-peach", i), new Poker(i, Poker.Color.BLACK, Poker.Shape.PEACH));
        }
        POKER_MAP.put("0-red-joker", new Poker(0, Poker.Color.RED, Poker.Shape.JOKER));
        POKER_MAP.put("0-black-joker", new Poker(0, Poker.Color.BLACK, Poker.Shape.JOKER));
    }

    public static Poker getPoker(String key) {
        if (!POKER_MAP.containsKey(key)) {
            throw new IllegalStateException(String.format("%s does not exist", key));
        }
        return POKER_MAP.get(key);
    }

    public static List<String> getAllPokersString() {
        return new ArrayList<>(POKER_MAP.keySet());
    }
}
