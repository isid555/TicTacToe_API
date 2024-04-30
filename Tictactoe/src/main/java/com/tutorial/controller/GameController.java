package com.tutorial.controller;

import com.tutorial.exception.invalidmoveexception;
import com.tutorial.models.Game;
import com.tutorial.models.GameState;
import com.tutorial.models.Player;

import java.util.List;

public class GameController {
    //todo
//     No 2 players  shld hv same symbol
    // if ,then throw exception
   public Game startGame(int dimension, List<Player> players){
       return new Game(dimension,players);
   }


   public GameState checkState(Game game){
       return game.getGameState();
   }
   public void makeMove(Game game) throws invalidmoveexception {
       game.makeMove();
   }
   public Player getWinner(Game game){
       return game.getWinner();
   }
   public void printBoard(Game game){
    game.printBoard();
   }
}
