package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.capstone.qwirkle.R;
import com.capstone.qwirkle.models.Player;
import com.capstone.qwirkle.views.ConnectActivity;
import com.capstone.qwirkle.views.MainActivity;

import java.util.ArrayList;

public class WinningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);
        ListView listView = findViewById(R.id.winningPlayers);

        ArrayList<Player> players = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                players = (ArrayList<Player>) bundle.getSerializable("players");
            }
        }
    }



    public void btnDoneClicked(View view){
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}