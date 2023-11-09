package com.example.game;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Game {

    private static Game instance;
    private Context context;
    private Cell[][] gameGrid = new Cell[Constants.SIDE][Constants.SIDE];

    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }
    private Game(){}

    public void createGrid(Context context){
        this.context = context;

        Log.e("CheckTheLog", "createGrid");

        Constants.setIsPlaying(true);
        int[][] generatedGrid = Generator.generatorGrid(Constants.BOMB_NUMBERS,Constants.SIDE,Constants.SIDE);
        printGrid(generatedGrid);
        setGrid(context,generatedGrid);
    }

    private void setGrid(Context context, int[][] grid){
        for (int x = 0; x < Constants.SIDE; x++) {
            for (int y = 0; y < Constants.SIDE; y++) {
                if(gameGrid[x][y] == null){
                    gameGrid[x][y] = new Cell(context, x, y);
                }
                gameGrid[x][y].setValue(grid[x][y]);
                gameGrid[x][y].invalidate();
            }
        }
    }

    public Cell getCellAtPos(int position){
        int x = position % Constants.SIDE;
        int y = position / Constants.SIDE;
        return gameGrid[x][y];
    }

    public Cell getCellAtPos(int x, int y){
        return gameGrid[x][y];
    }

    public void click(int xPos, int yPos) {

        if(xPos >= 0 && yPos >= 0 && xPos < Constants.SIDE && yPos < Constants.SIDE && !getCellAtPos(xPos,yPos).isClicked()){
            getCellAtPos(xPos, yPos).setClicked();

            if(getCellAtPos(xPos, yPos).getValue() == 0){
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if(i != j){
                            click(xPos+i,yPos+j);
                        }
                    }
                }
            }

            if(getCellAtPos(xPos, yPos).isBomb()){
                onGameLost();
            }
        }

        checkGameIsEnd();
    }

    private void checkGameIsEnd() {
        int nonRevealed = 0;
        for (int i = 0; i < Constants.SIDE; i++) {
            for (int j = 0; j < Constants.SIDE; j++) {
                if(!getCellAtPos(i,j).isRevealed())
                    nonRevealed++;
            }
        }
        if(nonRevealed == Constants.BOMB_NUMBERS)
            onGameWin();
    }

    private void onGameWin(){
        for (int i = 0; i < Constants.SIDE; i++) {
            for (int j = 0; j < Constants.SIDE; j++) {
                getCellAtPos(i,j).setRevealed();
            }
        }
        Constants.setIsPlaying(false);
        Toast.makeText(context,"WoHoo!! You won the Game",Toast.LENGTH_LONG).show();
    }
    private void onGameLost(){
        for (int i = 0; i < Constants.SIDE; i++) {
            for (int j = 0; j < Constants.SIDE; j++) {
                getCellAtPos(i,j).setRevealed();
            }
        }
        Constants.setIsPlaying(false);
        Toast.makeText(context,"You lost the Game",Toast.LENGTH_LONG).show();
    }

    private void printGrid(int[][] generatedGrid) {
        for (int i = 0; i < Constants.SIDE; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < Constants.SIDE; j++) {
                if(generatedGrid[i][j] == -1)
                    str.append("B ");
                else
                    str.append(generatedGrid[i][j]).append(" ");
            }
            Log.e("CheckTheLog", str.toString());
        }
        setGrid(context,generatedGrid);
    }


}
