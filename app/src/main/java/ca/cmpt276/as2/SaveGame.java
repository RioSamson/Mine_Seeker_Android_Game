package ca.cmpt276.as2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.cmpt276.as2.databinding.ActivitySaveGameBinding;

public class SaveGame extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "Extra - message";

    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, SaveGame.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivitySaveGameBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaveGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        //Handle the extra
        Intent i = getIntent();
        String message = i.getStringExtra(EXTRA_MESSAGE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}