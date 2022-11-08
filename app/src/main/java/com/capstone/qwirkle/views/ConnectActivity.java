package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.capstone.qwirkle.PlayerClient;
import com.capstone.qwirkle.R;
import com.capstone.qwirkle.messages.Message;
import com.capstone.qwirkle.messages.client.Join;

public class ConnectActivity extends AppCompatActivity {

    TextView ip_address, username;
    PlayerClient client

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        ip_address = findViewById(R.id.ip_address);
        username = findViewById(R.id.playerName);

    }
    public void backArrow(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    public void connect(View view){
        String serverAddress = ip_address.getText().toString();
        String handle = username.getText().toString();

        Log.i("ChatClient", "Connecting to " + serverAddress + " as " + handle);
        client = new PlayerClient(
                message -> runOnUiThread(
                        () -> onMessageReceived(message)
                ));

        client.connect(serverAddress, handle);
    }

    private void onMessageReceived(Message message) {
        if(message instanceof Join){

        }el
    }


}
