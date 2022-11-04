package com.capstone.qwirkle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.capstone.qwirkle.views.playActivity;

public class NumberOfPlayers extends AppCompatActivity {

    Spinner numOfPlayers;
    Button ConfirmButton;
    ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_players);
        numOfPlayers = findViewById(R.id.numOfPlayers);
        ConfirmButton = findViewById(R.id.button);

        myAdapter = new ArrayAdapter<String>(NumberOfPlayers.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.players));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOfPlayers.setAdapter(myAdapter);

        ConfirmButton.setOnClickListener(view -> {
            String current = numOfPlayers.getSelectedItem().toString();
            int number = Integer.parseInt(current);

            Intent intent = new Intent(NumberOfPlayers.this, playActivity.class);
            intent.putExtra("numOfPlayers", number);
            startActivity(intent);
        });

    }
}