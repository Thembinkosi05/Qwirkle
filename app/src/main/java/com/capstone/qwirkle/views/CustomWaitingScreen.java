package com.capstone.qwirkle.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.capstone.qwirkle.R;
import com.capstone.qwirkle.messages.Message;

import java.util.ArrayList;
import java.util.Locale;

public class CustomWaitingScreen {

    private final Activity activity;
    private final ArrayAdapter<Message> adapter;
    private TextView numPlayers;
    private TextView txtWaitPlayers;
    private ListView listPlayers;
    private AlertDialog dialog;

    public CustomWaitingScreen(Activity activity) {
        this.activity = activity;
        adapter = new ArrayAdapter<Message>(activity.getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<Message>());
    }


    public void startWaitingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_waiting_for_players, null);
        builder.setView(view);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
        numPlayers = view.findViewById(R.id.txtNumPlayers);
        txtWaitPlayers = view.findViewById(R.id.txtWait);
        listPlayers = view.findViewById(R.id.listPlayers);
        listPlayers.setAdapter(adapter);
    }

    public void stopMatchMaking(){
        dialog.dismiss();
    }

    public void addMessage(Message message)
    {
        adapter.add(message);
        numPlayers.setText(String.format(Locale.getDefault(), "%d/4", adapter.getCount()));
    }
}
