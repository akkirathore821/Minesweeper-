package com.example.game;

public class Constants {

    public static final int BOMB_NUMBERS = 15;
    public static final int SIDE = 15;
    public static boolean isPlaying = true;

    public static boolean getIsPlaying() {
        return isPlaying;
    }

    public static void setIsPlaying(boolean isPlaying) {
        Constants.isPlaying = isPlaying;
    }
}
