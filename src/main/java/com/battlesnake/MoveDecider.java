package com.battlesnake;

public class MoveDecider {
    public static String getNextMove(GameState state) {
        if (state.you.body == null || state.you.body.isEmpty()) {
            return "up"; // fallback if no body data
        }

        // TODO: Replace with actual logic later
        return "right";
    }
}
