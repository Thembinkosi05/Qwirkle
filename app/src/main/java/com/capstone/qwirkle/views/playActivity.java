package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.qwirkle.R;
import com.capstone.qwirkle.controller.GameController;
import com.capstone.qwirkle.models.Tile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class playActivity extends AppCompatActivity {

    LinearLayout tileContainer;
    viewTile tile1,tile2,tile3,tile4,tile5,tile6;
    TableLayout playBoard;
    List<viewTile> viewTileList;
    GameController gameController;
    TextView curPlayer;
    Spinner numOfPlayers;

    private int screenWidth;
    private int screenHeight;

    //handlers and timers
    private Handler handler;
    private Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getReferences();

        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        initTileView();

        //get current player
        int num =2;
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                num = bundle.getInt("numOfPlayers");
            }
        }
        gameController= new GameController(num,this);
    }


    private void getReferences(){
        tileContainer = findViewById(R.id.tileContainer);
        playBoard = findViewById(R.id.playBoard);
        curPlayer = findViewById(R.id.curPlayer);
        numOfPlayers = findViewById(R.id.numOfPlayers);
    }

    private void initTileView(){

        viewTileList = new ArrayList<>(6);

        tile1 = findViewById(R.id.tile1);
        tile1.setParentHeight(screenHeight);
        tile1.setParentWidth(screenWidth);

        tile2 = findViewById(R.id.tile2);
        tile2.setParentHeight(screenHeight);
        tile2.setParentWidth(screenWidth);

        tile3 = findViewById(R.id.tile3);
        tile3.setParentHeight(screenHeight);
        tile3.setParentWidth(screenWidth);

        tile4 = findViewById(R.id.tile4);
        tile4.setParentHeight(screenHeight);
        tile4.setParentWidth(screenWidth);

        tile5 = findViewById(R.id.tile5);
        tile5.setParentHeight(screenHeight);
        tile5.setParentWidth(screenWidth);

        tile6 = findViewById(R.id.tile6);
        tile6.setParentHeight(screenHeight);
        tile6.setParentWidth(screenWidth);

        viewTileList.addAll(Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6));

        initDragDropDView();
    }


    private void initDragDropDView() {
        viewTileList.forEach(VwDice -> VwDice.setOnTouchListener((v, event) -> {
            float xDown = 0;
            float yDown = 0;

            switch (event.getActionMasked()) {

                case MotionEvent.ACTION_DOWN:
                    xDown = event.getX();
                    yDown = event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float xMoved, yMoved;
                    xMoved = event.getX();
                    yMoved = event.getY();

                    float distanceX = xMoved - xDown;
                    float distanceY = yMoved - yDown;

                    v.setX(distanceX);
                    v.setY(distanceY);

            }

            /**
             if(event.getAction() == MotionEvent.ACTION_DOWN) {
             //  BoardFragment curFrag = (BoardFragment) fragmentStateAdapter.getItem(boardContainer.getCurrentItem());

             //            if (appHostPlayer == curFrag.getPlayer()) { //for multiplayer server

             ClipData data = ClipData.newPlainText("", "");
             View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
             v.startDragAndDrop(data, shadowBuilder, v, 0);
             //            v.setVisibility(View.INVISIBLE);

             //   if (curFrag.getPlayer() != game.getCurPlayer()) //for turn-based
             //     Toast.makeText(this, "It's not your turn! :)", Toast.LENGTH_LONG).show();

             // return true;
             } else v.performClick();
             //            }
             //            Toast.makeText(this, "You cannot make changes to this players's board!", Toast.LENGTH_LONG).show();
             //            return false;
             // return false;*/
            return false;
        }));
    }
    /**
    private void initDragListener(ImageView view) {
        view.setOnDragListener((v, event) -> {
            // Defines a variable to store the action type for the incoming event
          //  if(player.isTurn()) {
                final int action = event.getAction();

                View draggedView = (View) event.getLocalState();
                if (draggedView instanceof viewTile) {
                    viewTile tileView = (viewTile) draggedView;
                    tileView.setTag("TILE");
                    Tile dice = tileView.getTile();
                    int[] coord = getCellCoordinates(view);

                //    Window playerWindow = player.getWindow();

                    if (coord[0] != -1) //Check that the current view is valid to accept the dice
                        switch (action) {

                            case DragEvent.ACTION_DRAG_STARTED:

                                // Determines if this View can accept the dragged data
                                Log.d(tileView.getTag().toString(), "DRAG_STARTED AND LISTENING");
                              //  v.setBackgroundColor(getResources().getColor(R.color.colorDiceGreen, view.getContext().getTheme()));

                                return true;


                            case DragEvent.ACTION_DRAG_ENTERED:

                                // Applies a green tint to the View. Return true; the return value is ignored.
                              //     v.setBackgroundColor(getResources().getColor(R.color.colorDiceGreen, view.getContext().getTheme()));

                                // Invalidate the view to force a redraw in the new tint
                                tileView.invalidate();

                                return true;

                            case DragEvent.ACTION_DRAG_LOCATION:

                                // Ignore the event
                                return true;

                            case DragEvent.ACTION_DRAG_EXITED:

                                // Re-sets the color tint to blue. Returns true; the return value is ignored.
//                                view.setBackgroundColor(getResources().getColor(R.color.colorGray, view.getContext().getTheme()));

                                // Invalidate the view to force a redraw in the new tint
                                view.invalidate();

                                return true;

                            case DragEvent.ACTION_DROP:

                                if(player.placeDice(((VwDice) draggedView).getDice(), coord[0], coord[1])){
                                    view.setImageDrawable(((VwDice) draggedView).getDrawable());
                                    view.setEnabled(false);
                                    draggedView.setVisibility(View.GONE);
          //                          hasPlayed = true;
                                }

                                // Turns off any color tints
                  //              view.setBackgroundColor(getResources().getColor(R.color.colorWhite, view.getContext().getTheme()));

                                // Invalidates the view to force a redraw
                                view.invalidate();

                                // Returns true. DragEvent.getResult() will return true.
                        //        FloatingActionButton fab = activity.findViewById(R.id.fabNextTurn);

//                                fab.show();
                                return true;

                            case DragEvent.ACTION_DRAG_ENDED:

                                // Turns off any color tinting
                                view.setBackgroundColor(getResources().getColor(R.color.colorWhite, view.getContext().getTheme()));

                                // Invalidates the view to force a redraw
                                view.invalidate();

                                // Does a getResult(), and displays what happened.
                                if (event.getResult()) {
                                    Toast.makeText(draggedView.getContext(), "Dice Placed, end turn by pressing floating button.", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(draggedView.getContext(), "Please drop on a valid cell on the board.", Toast.LENGTH_LONG).show();
                                }

                                // returns true; the value is ignored.
                                return true;

                            // An unknown action type was received.
                            default:
                                Log.e("DragDrop", "Unknown action type received by OnDragListener.");
                                break;
                        }
                    else if(hasPlayed){
                        Toast.makeText(draggedView.getContext(), "Dice Placed, end turn by pressing floating button.", Toast.LENGTH_LONG).show();
                    }
                }
            } else hasPlayed = false;
            view.setBackgroundColor(getResources().getColor(R.color.colorWhite, view.getContext().getTheme()));
            return false;
        });
    }*/

    private int[] getCellCoordinates(ImageView view) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (getTblCell(row, col) == view) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private ImageView getTblCell(int x, int y) {
        TableRow row = (TableRow) playBoard.getChildAt(x);
        return (ImageView) row.getChildAt(y);
    }
}