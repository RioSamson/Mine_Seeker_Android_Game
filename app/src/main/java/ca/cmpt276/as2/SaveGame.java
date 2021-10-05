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
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaveGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        //everything till here was automatically made
        gameManager = GameManager.getInstance();

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

    protected void onResume() {
        super.onResume();
        updateUI();
    }

    public static Intent makeLaunchIntent(Context c, int gameIndex) {
        Intent intent = new Intent(c, SaveGame.class);
        intent.putExtra("gameIndex", gameIndex);
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
                Intent intent = getIntent();
                int gameIndex = intent.getIntExtra("gameIndex", 0);
                if (gameIndex >= 0) {
                    editGame(gameIndex);
                } else {
                    createNewGame();
                }
                finish(); // this makes it go to the home screen
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editGame(int gameIndex) {
        Game gameBeingEdited = gameManager.getGame(gameIndex);
        // Clearing previous scores and players stored in game
        gameBeingEdited.clearPlayers();
        gameBeingEdited.clearScores();

        // Creating new player1 with new values
        // and re-adding player info and scores
        PlayerScore player1 = createPlayer(R.id.p1Cards, R.id.p1Points, R.id.p1Wagers);
        int player1Score = player1.getScore();
        gameBeingEdited.addPlayer(player1);
        gameBeingEdited.addScores(player1Score);

        // Creating new player2 with new values
        // and re-adding player info and scores
        PlayerScore player2 = createPlayer(R.id.p2Cards, R.id.p2Points, R.id.p2Wagers);
        int player2Score = player2.getScore();
        gameBeingEdited.addPlayer(player2);
        gameBeingEdited.addScores(player2Score);
        Toast.makeText(this, "Saved: " + player1Score + " vs " + player2Score, Toast.LENGTH_SHORT).show();
    }

    private void createNewGame() {
        Game game = new Game(NUM_PLAYERS);
        PlayerScore player1 = createPlayer(R.id.p1Cards, R.id.p1Points, R.id.p1Wagers);
        PlayerScore player2 = createPlayer(R.id.p2Cards, R.id.p2Points, R.id.p2Wagers);
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();
        Toast.makeText(this, "Saved: " + player1Score + " vs " + player2Score, Toast.LENGTH_SHORT).show();
        //add the scores in order to game and then add game to gameManager
        game.addScores(player1Score);
        game.addScores(player2Score);
        game.addPlayer(player1);
        game.addPlayer(player2);
        game.saveGame();
        gameManager.add(game);
    }

    private PlayerScore createPlayer(int cardsID, int pointsID, int wagersID) {
        EditText editCards  = (EditText) findViewById(cardsID);
        EditText editPoints  = (EditText) findViewById(pointsID);
        EditText editWagers  = (EditText) findViewById(wagersID);

        String stringCards = editCards.getText().toString();
        int cards = Integer.parseInt(stringCards);
        String stringPoints = editPoints.getText().toString();
        int points = Integer.parseInt(stringPoints);
        String stringWagers = editWagers.getText().toString();
        int wagers = Integer.parseInt(stringWagers);
        return new PlayerScore(cards, points, wagers);
    }

    private void updateUI() {
        Intent intent = getIntent();
        int gameIndex = intent.getIntExtra("gameIndex", 0);
        if (gameIndex >= 0) {
            // Loading Player1 Information
            int player1Index = 0;
            loadPlayerInformation(player1Index, gameIndex, R.id.p1Cards, R.id.p1Points, R.id.p1Wagers);
            // Loading Player2 Information
            int player2Index = 1;
            loadPlayerInformation(player2Index, gameIndex, R.id.p2Cards, R.id.p2Points, R.id.p2Wagers);
        }
    }

    private void loadPlayerInformation(int playerIndex, int gameIndex, int cardsID, int pointsID, int wagersID ) {
        int currentNumOfCards = gameManager.getGame(gameIndex).getPlayer(playerIndex).getNumCards();
        int currentSumOfCards = gameManager.getGame(gameIndex).getPlayer(playerIndex).getSumOfCards();
        int currentNumOfWagers = gameManager.getGame(gameIndex).getPlayer(playerIndex).getNumWager();
        EditText CardsText = findViewById(cardsID);
        EditText PointsText = findViewById(pointsID);
        EditText WagersText = findViewById(wagersID);
        String currentCards = String.valueOf(currentNumOfCards);
        String currentPoints = String.valueOf(currentSumOfCards);
        String currentWagers = String.valueOf(currentNumOfWagers);
        CardsText.setText(currentCards);
        PointsText.setText(currentPoints);
        WagersText.setText(currentWagers);
    }
}