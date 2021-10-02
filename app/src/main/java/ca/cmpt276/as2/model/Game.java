//used this youtube video for date time: https://www.youtube.com/watch?v=hKyDooHQ_GE

package ca.cmpt276.as2.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one game played by 1 to 4 players. Stores date/time when game was created
 * @author Rio Samson
 */
public class Game {
    private final LocalDateTime LOCAL_DATE;
    private final DateTimeFormatter DATE_FORMATTER;
    private final int NUM_PLAYERS;
    private final List<Integer> winners = new ArrayList<>();
    private final List<Integer> scores = new ArrayList<>();

    public Game(int numPlayers) {
        this.NUM_PLAYERS = numPlayers;
        this.LOCAL_DATE = LocalDateTime.now();
        this.DATE_FORMATTER = DateTimeFormatter.ofPattern("(@yyyy-MM-dd HH:mm)");
    }

    public void addScores(int score) {
        scores.add(score);
    }

    //update Game before showing the menu again - find winners, scores
    public void saveGame() {
        int winningScore = scores.get(0);
        for(int i = 1; i< NUM_PLAYERS; i++) {
            if(scores.get(i) > winningScore) {
                winningScore = scores.get(i);
            }
        }

        for(int i = 0; i< NUM_PLAYERS; i++) {
            if(scores.get(i) == winningScore) {
                winners.add(i+1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("  ");
        int i;
        for(i=0; i<scores.size()-1; i++) {
            str.append(scores.get(i)).append(" vs ");
        }
        str.append(scores.get(i));
        str.append(", winner player(s): ");

        int j;
        for(j=0; j<winners.size()-1; j++) {
            str.append(winners.get(j)).append(", ");
        }
        str.append(winners.get(j));
        str.append(" ").append(DATE_FORMATTER.format(LOCAL_DATE));

        return str.toString();
    }
}
