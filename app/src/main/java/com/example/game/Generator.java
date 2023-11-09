package com.example.game;

import java.util.Random;

public class Generator {

    public static int[][] generatorGrid(int bombs, int width, int height){

        int[][] grid = new int[width][height];

        Random r = new Random();
        while(bombs > 0){
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if(grid[x][y] != -1){
                grid[x][y] = -1;
                bombs--;
            }
        }

        neighbours(grid, width, height);

        return grid;
    }

    public static void neighbours(int[][] grid, int width, int height){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = getNumber(grid,i,j,width,height);
            }
        }
    }

    private static int getNumber(int[][] grid, int i, int j, int width, int height) {
        if(grid[i][j] == -1)
            return -1;

        int count = 0;

        if(isMine(grid,i-1,j-1,width,height)) count++;
        if(isMine(grid,i-1,j,width,height)) count++;
        if(isMine(grid,i,j-1,width,height)) count++;
        if(isMine(grid,i+1,j+1,width,height)) count++;
        if(isMine(grid,i,j+1,width,height)) count++;
        if(isMine(grid,i+1,j,width,height)) count++;
        if(isMine(grid,i+1,j-1,width,height)) count++;
        if(isMine(grid,i-1,j+1,width,height)) count++;

        return count;
    }

    private static boolean isMine(int[][] grid, int i, int j, int width, int height) {
        return i >= 0 && j >= 0 && i < width && j < height && grid[i][j] == -1;
    }


}
