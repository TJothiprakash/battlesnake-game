package com.battlesnake;



import java.util.List;
import java.util.Map;

public class GameState {
    public Game game;
    public int turn;
    public Board board;
    public Snake you;

    public static class Game {
        public String id;
    }

    public static class Board {
        public int height;
        public int width;
        public List<Snake> snakes;
        public List<Coord> food;
    }

    public static class Snake {
        public String id;
        public String name;
        public List<Coord> body;
    }

    public static class Coord {
        public int x;
        public int y;
    }
}
