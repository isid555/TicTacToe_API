package com.tutorial.models;

import com.tutorial.exception.invalidmoveexception;
import com.tutorial.stratergies.WinningAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private int nextPlayerMoveIndex;
    private Player winner;
    private List<Move> moves;
    private GameState gameState;
    private WinningAlgorithms winningAlgorithms;

    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextPlayerMoveIndex = 0;
        this.winner = null;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winningAlgorithms = new WinningAlgorithms();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    public void printBoard(){
        board.printBoard();
    }
    public boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row<0 || row>=board.getSize() ||
                col<0 || col >= board.getSize()){
            return false;
        }
        return board.getBoard().get(row).get(col).getCellstate().equals(Cellstate.EMPTY);
    }
    public void makeMove() throws invalidmoveexception {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        Move move = currentPlayer.makeMove(board);
        if(!validateMove(move)){
            // throws an exception
            throw new invalidmoveexception("Invalid move by"+ currentPlayer.getName());
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell celltoChange = board.getBoard().get(row).get(col);
        celltoChange.setPlayer(currentPlayer);
        celltoChange.setCellstate(Cellstate.FILLED);

        Move finalMove = new Move(celltoChange,currentPlayer);
        moves.add(finalMove);
        nextPlayerMoveIndex = (nextPlayerMoveIndex+1)%players.size();
        //Checking if the current move is winning or not
        if(winningAlgorithms.checkWinner(board,finalMove)){
            winner = currentPlayer;
            gameState =GameState.ENDED;
        }
        else {
            gameState = GameState.IN_PROGRESS;
        }
        return;
    }
}
