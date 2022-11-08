package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.qwirkle.R;

import java.util.Locale;

public class ConnectPlayers extends AppCompatActivity {

    TextView numPlayers;
    TextView txtWaitPlayers;
    ListView listConnectedPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_for_players);
        numPlayers = findViewById(R.id.txtNumPlayers);
        listConnectedPlayers = findViewById(R.id.listPlayers);
    }


    public void updateNumPlayers(int curNum) {
        numPlayers.setText(String.format(Locale.getDefault(), "%d/4", curNum));
        if (curNum == 4)
            txtWaitPlayers.setText(this.getString(R.string.start_game_msg));
    }


    public void startGame(){
        Intent intent=new Intent(getApplicationContext(),playActivity.class);
        startActivity(intent);
    }
}