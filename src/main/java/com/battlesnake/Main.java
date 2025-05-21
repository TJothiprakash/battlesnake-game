package com.battlesnake;

import static spark.Spark.*;

import com.google.gson.*;

public class Main {
    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> "Battlesnake AI is running!");

        post("/start", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new SnakeConfig());
        });

        post("/move", (req, res) -> {
            res.type("application/json");
            GameState gameState = new Gson().fromJson(req.body(), GameState.class);
            String move = MoveDecider.getNextMove(gameState);
            return "{\"move\":\"" + move + "\"}";
        });

        post("/end", (req, res) -> {
            System.out.println("Game Over!");
            return "{}";
        });
    }
}
