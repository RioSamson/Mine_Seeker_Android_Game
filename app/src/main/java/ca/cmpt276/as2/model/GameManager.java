package ca.cmpt276.as2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the manager for games. Organizes games and its overall information
 * @author Rio Samson
 */
public class GameManager implements Iterable<Game>{
    private final List<Game> games = new ArrayList<>();

    public void add(Game game) {
        games.add(game);
    }

    public void delete(int index) {
        games.remove(index);
    }

    public boolean isEmpty() {
        return games.isEmpty();
    }

    public int length() {
        return games.size();
    }

    @Override
    public Iterator<Game> iterator() {
        return games.iterator();
    }
}
