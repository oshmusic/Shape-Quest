package com.example.team_1e.persistence;

import com.example.team_1e.model.Enemy;
import com.example.team_1e.model.GameState;
import com.example.team_1e.model.Player;

public interface IPersistenceFacade {

    void savePlayer(Player p);

    void saveEnemy(Enemy e);

    void saveGameState(GameState gameState);

    Player retrievePlayer();

    Enemy retrieveEnemy();

    GameState retrieveGameState();
}
