package com.capstone.qwirkle.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.qwirkle.R;
import com.capstone.qwirkle.controller.GameController;
import com.capstone.qwirkle.models.Player;
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
    TextView curPlayer,bagSize,score,swap,p2,p3,p4,s2,s3,s4;
    Button Done;
    Spinner numOfPlayers;
    LinearLayout mainPlayers,one_player,layout2,layout3,layout4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getReferences();

        initTileView();
        generateBoard();
        generateSwap();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                gameController = bundle.getParcelable("gameController");
                Toast.makeText(this, "game controller is not null.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "bundle is null.", Toast.LENGTH_SHORT).show();
            }
        }else {
        Toast.makeText(this, "intent is null", Toast.LENGTH_SHORT).show();}

        bagSize.setText(gameController.getBag().size()+"");
    }

    public void Done(View view){
        gameController.fillHand();
        gameController.placementScore(gameController.getGameBoard());
        gameController.unswipe();
        gameController.curPlayer.setPlacing(false);
        gameController.changeTurn();
        setHandTile(gameController.curPlayer);
        curPlayer.setText("player "+gameController.curPlayer.getPlayerNo());
        score.setText("score: "+gameController.curPlayer.getPoints());

        bagSize.setText(gameController.getBag().size()+"");
        gameController.setToPlaced(gameController.curPlayer.getHand());
    }

    private void setHandTile(Player player){
        ArrayList<Tile> hand =player.getHand();
        for(int i = 0;i<hand.size();i++){
            viewTileList.get(i).setTile(hand.get(i));
        }
    }

    private void getReferences(){
        tileContainer = findViewById(R.id.tileContainer);
        playBoard = findViewById(R.id.playBoard);
        curPlayer = findViewById(R.id.curPlayer);
        bagSize= findViewById(R.id.bagSize);
        swap = findViewById(R.id.swap);
        score = findViewById(R.id.score);
        numOfPlayers = findViewById(R.id.numOfPlayers);
        Done = findViewById(R.id.btnDone);
        mainPlayers =findViewById(R.id.players);
        one_player = findViewById(R.id.one_player);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
    }

    private void initTileView(){

        viewTileList = new ArrayList<>(6);

        tile1 = findViewById(R.id.tile1);

        tile2 = findViewById(R.id.tile2);

        tile3 = findViewById(R.id.tile3);

        tile4 = findViewById(R.id.tile4);

        tile5 = findViewById(R.id.tile5);

        tile6 = findViewById(R.id.tile6);

        viewTileList.addAll(Arrays.asList(tile1, tile2, tile3, tile4, tile5, tile6));

        initDragDropDView();
    }

    private void initDragDropDView() {
        viewTileList.forEach(viewTile -> viewTile.setOnTouchListener((v, event) -> {
            if(event.getAction() == MotionEvent.ACTION_MOVE) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(data, shadowBuilder, v, 0);
               // v.setVisibility(View.INVISIBLE);
                return true;
            } else
            {
            //  v.setVisibility(View.VISIBLE);
                v.performClick();}
//            }
//            Toast.makeText(this, "You cannot make changes to this player's board!", Toast.LENGTH_LONG).show();
//            return false;
            return false;
        }));
    }

    private int[] getCellCoordinates(ImageView view) {
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 6; col++) {
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

    private void generateBoard(){
        for(int row=0;row<7;row++){
            for(int col=0;col<6;col++){
                ImageView curView = getTblCell(row, col);
                curView.setScaleType(ImageView.ScaleType.FIT_XY);
                initDragListener(curView);
            }
        }
    }

    private void generateSwap(){
        /**for(Tile tile : gameController.getGameBoard()){
            if(tile.getState().equals(Tile.State.PLACING)){
                Toast.makeText(this, "You are not allowed to swap tile while placing", Toast.LENGTH_SHORT).show();
                return;
            }
        }*/
        initDragSwapListener(swap);
    }

    private void initDragListener(ImageView view){
        view.setOnDragListener((v,event)->{
            final int action = event.getAction();
            View draggedView = (View) event.getLocalState();

            if (draggedView instanceof viewTile){
                viewTile tileView = (viewTile) draggedView;
                tileView.setTag("TILE");
                Tile tile = tileView.getTile();

                int[] coord = getCellCoordinates(view);
                if(gameController.isValidMove(coord[0],coord[1],((viewTile) draggedView).getTile(),gameController.getGameBoard())&&!gameController.curPlayer.isSwapping()){

                    switch (action) {
                        case MotionEvent.ACTION_BUTTON_PRESS:
                        case DragEvent.ACTION_DRAG_STARTED:
                        case DragEvent.ACTION_DRAG_ENTERED:

                            // Applies a green tint to the View. Return true; the return value is ignored.

                            // Determines if this View can accept the dragged data
                            //   Log.d(diceView.getTag().toString(), "DRAG_STARTED AND LISTENING");
                            v.setBackgroundColor(getResources().getColor(R.color.colorGreen, view.getContext().getTheme()));
                            view.invalidate();

                            return true;

                        // Invalidate the view to force a redraw in the new tint

                        case DragEvent.ACTION_DRAG_LOCATION:

                        // Ignore the event
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:

                        // Re-sets the color tint to blue. Returns true; the return value is ignored.
                       view.setBackgroundColor(getResources().getColor(R.color.colorGray, view.getContext().getTheme()));

                        // Invalidate the view to force a redraw in the new tint
                        view.invalidate();

                        return true;

                    case DragEvent.ACTION_DROP:

                        view.setImageDrawable(((viewTile) draggedView).getDrawable());
                        //view.setEnabled(false);
                        tile.setRow(coord[0]);
                        tile.setCol(coord[1]);
                        gameController.curPlayer.setPlacing(true);
                        tile.setState(Tile.State.PLACING);
                        gameController.addToBoard(gameController.curPlayer.getHand());
                        tile.setState(Tile.State.PLACING);
                        ((viewTile)draggedView).removeTile();
                        //draggedView.setVisibility(View.INVISIBLE);

                        // Turns off any color tints
                        view.setBackgroundColor(getResources().getColor(R.color.white, view.getContext().getTheme()));

                        // Invalidates the view to force a redraw
                        view.invalidate();

                      //  fab.show();
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:

                        // Turns off any color tinting
                        view.setBackgroundColor(getResources().getColor(R.color.white, view.getContext().getTheme()));

                        // Invalidates the view to force a redraw

                        // Does a getResult(), and displays what happened.
                        if (event.getResult()) {
                            Toast.makeText(this, "Dice Placed, end turn by pressing floating button.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Please drop on a valid cell on the board.", Toast.LENGTH_SHORT).show();
                        }
                        view.invalidate();
                        // returns true; the value is ignored.
                        return true;

                    // An unknown action type was received.
                    default:
                        Log.e("DragDrop", "Unknown action type received by OnDragListener.");
                        break;
                }
            }
            }

            return false;
        });
    }

    public void initDragSwapListener(TextView view){
        view.setOnDragListener((v,event)->{
            final int action = event.getAction();
            View draggedView = (View) event.getLocalState();
            if(draggedView instanceof viewTile){
                viewTile tileView = (viewTile) draggedView;
                tileView.setTag("TILE");
                Tile tile = tileView.getTile();

                switch (action) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                    case DragEvent.ACTION_DRAG_STARTED:
                    case DragEvent.ACTION_DRAG_ENTERED:
                        view.invalidate();

                        return true;

                    // Invalidate the view to force a redraw in the new tint

                    case DragEvent.ACTION_DRAG_LOCATION:

                        // Ignore the event
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:

                        view.invalidate();

                        return true;

                    case DragEvent.ACTION_DROP:

                        if(!gameController.curPlayer.isPlacing()&&!tile.isSwapped()){
                            tile.setState(Tile.State.SWAPPING);
                            gameController.curPlayer.setSwapping(true);
                            tile.setSwapped(true);
                            gameController.swapPieces(gameController.curPlayer.getHand());
                            ((viewTile)draggedView).removeTile();
                            setHandTile(gameController.curPlayer);
                            Toast.makeText(this, "dice has been swapped", Toast.LENGTH_SHORT).show();
                            //swap sound here
                        }else {
                            //error swap sound here
                            Toast.makeText(this, "swap is not allowed", Toast.LENGTH_SHORT).show();
                        }

                        // Invalidates the view to force a redraw
                        view.invalidate();
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:

                        if (event.getResult()) {
                           // Toast.makeText(this, "dice has been swapped", Toast.LENGTH_SHORT).show();
                        } else {
                           // Toast.makeText(this, "dice not swapped.", Toast.LENGTH_SHORT).show();
                        }
                        view.invalidate();
                        // returns true; the value is ignored.
                        return true;

                    // An unknown action type was received.
                    default:
                        Log.e("DragDrop", "Unknown action type received by OnDragListener.");
                        break;
                }
            }
            return false;
        });
    }


}