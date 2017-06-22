package es.rogermartinez.mockata.player.repository.impl;

import es.rogermartinez.mockata.player.Player;
import es.rogermartinez.mockata.player.repository.PlayerRepository;

import java.util.List;

public class PlayerInMemory implements PlayerRepository {
    @Override
    public List<Player> obtainAllPlayers() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Player> obtainLeftHandedPlayers() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Player> obtainRightHandedPlayers() {
        throw new RuntimeException("Not implemented");
    }
}
