package ca.cmpt276.as2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as2.databinding.ActivityMainBinding;
import ca.cmpt276.as2.model.Game;
import ca.cmpt276.as2.model.GameManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //testing testing testing - done test

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        gameManager = GameManager.getInstance();
        populateListView();
        setupFloatingActionButton();
        setupListViewClicker();

    }

    private void setupListViewClicker() {
        ListView list = (ListView) findViewById(R.id.listViewGames);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "Clicked #" + position + " - " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateListView() {
        //turn list of games into a string array

        String[] myGames = {"rio", "is", "great"};
        //DONT DELETE  - NEED ONCE IMPLEMENTED USERINPUT AND CAN FILL GAME MANAGER!!!!!!
        /*
        String[] myGames = new String[gameManager.length()];
        int i = 0;
        for (Game game : gameManager) {
            myGames[i] = game.toString();
        }
    */

        //builds the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.items_for_listview,
                myGames);

        ListView list = (ListView) findViewById(R.id.listViewGames);
        list.setAdapter(adapter);
    }

    private void setupFloatingActionButton() {
        binding.fab.setOnClickListener(view -> {
            Intent i = SaveGame.makeLaunchIntent(MainActivity.this, "Enter Values");
            startActivity(i);
        });
    }
}