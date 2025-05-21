package com.battlesnake;

import static spark.Spark.*;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        port(8080);

        // Respond to root path `/` with SnakeConfig JSON
        get("/", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new SnakeConfig());
        });

        post("/start", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new SnakeConfig());
        });

        post("/move", (req, res) -> {
            res.type("application/json");

            GameState gameState = new Gson().fromJson(req.body(), GameState.class);
            System.out.println("Received game state on turn " + gameState.turn);
            System.out.println(req.body());  // Log the full input JSON

            String move;
            try {
                move = MoveDecider.getNextMove(gameState);
            } catch (Exception e) {
                e.printStackTrace();
                move = "up"; // fallback move
            }

            return "{\"move\":\"" + move + "\"}";
        });


        post("/end", (req, res) -> {
            System.out.println("Game Over!");
            return "{}";
        });
    }
}
