package es.rogermartinez.mockata.player.repository;

import es.rogermartinez.mockata.player.Player;

import java.util.List;

public interface PlayerRepository {

    List<Player> obtainAllPlayers();

    List<Player> obtainLeftHandedPlayers();

    List<Player> obtainRightHandedPlayers();
}
