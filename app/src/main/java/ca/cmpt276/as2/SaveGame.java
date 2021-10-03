package ca.cmpt276.as2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as2.databinding.ActivitySaveGameBinding;

public class SaveGame extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "Extra - message";

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

        //taking in the information from user input
//        EditText userInP1Cards  = (EditText) findViewById(R.id.p1Cards);
//        EditText userInP1Points  = (EditText) findViewById(R.id.p1Points);
//        EditText userInP1Wagers  = (EditText) findViewById(R.id.p1Wagers);
//        String one = userInP1Cards.getText().toString();
//        int p1Cards = Integer.parseInt(one);

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
}