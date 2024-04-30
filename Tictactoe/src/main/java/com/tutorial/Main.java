package com.tutorial;

import com.tutorial.controller.GameController;
import com.tutorial.exception.invalidmoveexception;
import com.tutorial.models.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws invalidmoveexception {
//        System.out.println("Hello world!");
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);
//        System.out.println("Please enter your game dimension");
        int dimension = 3;
        List<Player>players = List.of(
                new Player("Rolex",new Symbol('X'), PlayerType.HUMAN),
                new Bot("Scaler",new Symbol('O'),PlayerType.BOT,BotDifficultyLevel.EASY)
        );


        Game game = gameController.startGame(dimension,players);
//        gameController.printBoard(game);


        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            //1.print the board
            game.printBoard();

            //2.player's turn
            gameController.makeMove(game);
        }
        if(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            System.out.println("Game is Draw");
        }
        else {
            System.out.println("Player"+" "+gameController.getWinner(game).getName()+" is the winner");
        }
    }
}