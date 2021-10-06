package ca.cmpt276.as2.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import ca.cmpt276.as2.MainActivity;
import ca.cmpt276.as2.SaveGame;

/**
 * Represents the manager for games. Organizes games and its overall information
 * @author Rio Samson
 */
public class GameManager implements Iterable<Game>{
    private List<Game> games = new ArrayList<>();

    /*
        Singleton Support
     */
    private static GameManager instance;
    private GameManager() {
    }
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /*
        Normal Object Code
     */
    public void add(Game game) {
        games.add(game);
    }

    public void delete(int index) {
        games.remove(index);
    }

    public Game getGame(int index) {
        return games.get(index);
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
