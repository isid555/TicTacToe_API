package com.tutorial.models;

import java.awt.*;
import java.util.List;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, Symbol symbol, PlayerType playerType,BotDifficultyLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        //find the first empty cell and make the move
        System.out.println("Move by bot");
        for (List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellstate().equals(Cellstate.EMPTY)){
                    return new Move(cell,this);
                }
            }
        }
//        return super.makeMove(board);
        return null;
    }
}
