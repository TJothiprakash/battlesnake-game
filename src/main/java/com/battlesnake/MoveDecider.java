package com.battlesnake;

import java.util.*;

public class MoveDecider {
    private static final String[] DIRECTIONS = {"up", "down", "left", "right"};
    private static final int[][] DELTAS = {
            {0, 1},   // up
            {0, -1},  // down
            {-1, 0},  // left
            {1, 0}    // right
    };

    public static String getNextMove(GameState state) {
        GameState.Coord head = state.you.head;
        Set<String> safeMoves = getSafeMoves(state);

        if (safeMoves.isEmpty()) {
            return "up"; // fallback move
        }

        // Find closest food
        GameState.Coord targetFood = getClosestFood(head, state.board.food);

        String bestMove = "up";
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < DIRECTIONS.length; i++) {
            String move = DIRECTIONS[i];
            if (!safeMoves.contains(move)) continue;

            int newX = head.x + DELTAS[i][0];
            int newY = head.y + DELTAS[i][1];
            int dist = manhattanDistance(newX, newY, targetFood.x, targetFood.y);

            if (dist < minDistance) {
                minDistance = dist;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private static Set<String> getSafeMoves(GameState state) {
        Set<String> safe = new HashSet<>();
        GameState.Coord head = state.you.head;
        int boardWidth = state.board.width;
        int boardHeight = state.board.height;

        Set<String> snakeBodies = new HashSet<>();
        for (GameState.Snake snake : state.board.snakes) {
            for (GameState.Coord c : snake.body) {
                snakeBodies.add(c.x + "," + c.y);
            }
        }

        for (int i = 0; i < DIRECTIONS.length; i++) {
            int nx = head.x + DELTAS[i][0];
            int ny = head.y + DELTAS[i][1];

            if (nx < 0 || ny < 0 || nx >= boardWidth || ny >= boardHeight) continue; // Avoid wall
            if (snakeBodies.contains(nx + "," + ny)) continue; // Avoid snake body

            safe.add(DIRECTIONS[i]);
        }

        return safe;
    }

    private static GameState.Coord getClosestFood(GameState.Coord head, List<GameState.Coord> foodList) {
        GameState.Coord closest = foodList.get(0);
        int minDist = manhattanDistance(head.x, head.y, closest.x, closest.y);

        for (GameState.Coord food : foodList) {
            int dist = manhattanDistance(head.x, head.y, food.x, food.y);
            if (dist < minDist) {
                minDist = dist;
                closest = food;
            }
        }

        return closest;
    }

    private static int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
