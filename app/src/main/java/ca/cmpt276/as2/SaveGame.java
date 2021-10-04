package ca.cmpt276.as2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as2.databinding.ActivitySaveGameBinding;
import ca.cmpt276.as2.model.Game;
import ca.cmpt276.as2.model.GameManager;
import ca.cmpt276.as2.model.PlayerScore;

public class SaveGame extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "Extra - message";
    private static final int NUM_PLAYERS = 2;

    private AppBarConfiguration appBarConfiguration;
    private ActivitySaveGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaveGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        //everything till here was automatically made

        //this makes the back button on the top left
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Handle the extra message when launched to add a game - makes toast saying enter values
        Intent i = getIntent();
        String message = i.getStringExtra(EXTRA_MESSAGE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        TextView printScore = (TextView) findViewById(R.id.outScore1);
        printScore.setText("" + 30);

    }

    //giving intent to anyone who asks that include a string info - lets activities come to this one
    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, SaveGame.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    //makes the save button on the top right
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate menu
        getMenuInflater().inflate(R.menu.menu_save_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Game game = new Game(NUM_PLAYERS);
                GameManager gameManager = GameManager.getInstance();

                //read the user input and calculate the score
                int player1Score = calculateScore(R.id.p1Cards, R.id.p1Points, R.id.p1Wagers);
                int player2Score = calculateScore(R.id.p2Cards, R.id.p2Points, R.id.p2Wagers);
                Toast.makeText(this, "Saved: " + player1Score + " vs " + player2Score, Toast.LENGTH_SHORT).show();

                //add the scores in order to game and then add game to gameManager
                game.addScores(player1Score);
                game.addScores(player2Score);
                game.saveGame();
                gameManager.add(game);

                finish(); // this makes it go to the home screen
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int calculateScore(int cardsID, int pointsID, int wagersID) {
        EditText editCards  = (EditText) findViewById(cardsID);
        EditText editPoints  = (EditText) findViewById(pointsID);
        EditText editWagers  = (EditText) findViewById(wagersID);

        String stringCards = editCards.getText().toString();
        int cards = Integer.parseInt(stringCards);
        String stringPoints = editPoints.getText().toString();
        int points = Integer.parseInt(stringPoints);
        String stringWagers = editWagers.getText().toString();
        int wagers = Integer.parseInt(stringWagers);

        PlayerScore playerScore = new PlayerScore(cards, points, wagers);
        return playerScore.getScore();
    }
}