package com.battlesnake;

import java.util.List;

public class GameState {
    public Game game;
    public int turn;
    public Board board;
    public Snake you;

    public static class Game {
        public String id;
        public Ruleset ruleset;
        public int timeout;
        public String source;

        public static class Ruleset {
            public String name;
            public String version;
            public Settings settings;

            public static class Settings {
                public int foodSpawnChance;
                public int minimumFood;
                public int hazardDamagePerTurn;
                public String hazardMap;
                public String hazardMapAuthor;
                public Royale royale;
                public Squad squad;

                public static class Royale {
                    public int shrinkEveryNTurns;
                }

                public static class Squad {
                    public boolean allowBodyCollisions;
                    public boolean sharedElimination;
                    public boolean sharedHealth;
                    public boolean sharedLength;
                }
            }
        }
    }

    public static class Board {
        public int height;
        public int width;
        public List<Snake> snakes;
        public List<Coord> food;
        public List<Coord> hazards;
    }

    public static class Snake {
        public String id;
        public String name;
        public String latency;
        public int health;
        public List<Coord> body;
        public Coord head;
        public int length;
        public String shout;
        public String squad;
        public Customizations customizations;

        public static class Customizations {
            public String color;
            public String head;
            public String tail;
        }
    }

    public static class Coord {
        public int x;
        public int y;
    }
}
