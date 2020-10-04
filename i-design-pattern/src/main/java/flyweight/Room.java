package flyweight;

import java.util.*;

/**
 * @Author: Jeremy
 * @Date: 2020/8/28 21:32
 */
public class Room {
    private String id;

    // save players list;
    private List<String> players;

    // save pokers for each players;
    private Map<String, List<Poker>> pokers;

    public Room(String id, List<String> players) {
        this.id = id;
        this.players = players;
        this.pokers = new HashMap<>(players.size());
    }

    public void dispatch() {
        if (this.players.size() != 3) {
            throw new IllegalStateException("The number of players has to be 3!!!");
        }

        // init
        this.pokers.clear();
        for (String user : players) {
            this.pokers.put(user, new ArrayList<>(13));
        }

        // shuffle
        List<String> keys = PokerFactory.getAllPokersString();
        Collections.shuffle(keys);

        // dispatch
        int count = 0;
        for (String key : keys) {
            this.pokers.get(players.get(count++ % 3)).add(PokerFactory.getPoker(key));
        }
    }

    public void info() {
        System.out.println(String.format("--------Room %s--------\n" +
                "player1: %s \n" +
                "player2: %s \n" +
                "player3: %s \n" +
                "", id, players.get(0), players.get(1), players.get(2)));
        for (String player : players) {
            System.out.println(String.format("Pokers for player %s", player));
            Collections.sort(pokers.get(player));
            for (Poker poker : pokers.get(player)) {
                System.out.println(poker.toString());
            }
            System.out.println();
        }
    }
}
