package com.capstone.qwirkle.controller;

import com.capstone.qwirkle.models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameController {

    class TurnManager {
        List<Player> playerTurns = new ArrayList<>();
        int curIndex = 0;


        public TurnManager(List<Player> players) {

            playerTurns.addAll(players); //a deep copy of the players list is made so as not to modify the original players list
            setUpRevList();
        }

        private void setUpRevList() {
            List<Player> revList = new ArrayList<>();

            for (Player p :
                    playerTurns) {
                revList.add(p);
            }

            Collections.reverse(revList);
            playerTurns.addAll(revList);
        }

        public Player nextPlayer() {
            if (curIndex == playerTurns.size()) {
                curIndex = 0;
               // roundNumber++;
            }

            Player p = playerTurns.get(curIndex++);
          //p.setTurn(true);
            return p;
        }

        public void updatePlayerList(List<Player> list) {
            if (list == null || list.isEmpty())
                playerTurns.clear();
            else {
                playerTurns = list;
                setUpRevList();
            }
        }
    }
}
