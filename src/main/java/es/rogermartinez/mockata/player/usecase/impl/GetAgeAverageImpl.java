package es.rogermartinez.mockata.player.usecase.impl;

import es.rogermartinez.mockata.player.Player;
import es.rogermartinez.mockata.player.exception.InconsistencePlayerAgeException;
import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;
import es.rogermartinez.mockata.player.repository.PlayerRepository;
import es.rogermartinez.mockata.player.usecase.GetAgeAverage;

import java.util.List;

public class GetAgeAverageImpl implements GetAgeAverage {

    private PlayerRepository playerRepository;

    public GetAgeAverageImpl(PlayerRepository playerRepository) {
        assert playerRepository != null;
        this.playerRepository = playerRepository;
    }

    @Override
    public int calculate() throws InconsistencePlayerAgeException, NoPlayerFoundException {
        List<Player> players = playerRepository.obtainAllPlayers();
        validatePlayers(players);
        return average(players);
    }

    private int average(List<Player> players) throws InconsistencePlayerAgeException {
        return getTotalAge(players) / players.size();
    }

    private int getTotalAge(List<Player> players) throws InconsistencePlayerAgeException {
        int age = 0;
        for (Player player : players) {
            validateAge(player);
            age += player.getAge();
        }
        return age;
    }

    private void validateAge(Player player) throws InconsistencePlayerAgeException {
        if (player.getAge() < 0 || player.getAge() > 99) {
            throw new InconsistencePlayerAgeException();
        }
    }

    private void validatePlayers(List<Player> players) throws NoPlayerFoundException {
        if (players == null
                || players.isEmpty()) {
            throw new NoPlayerFoundException();
        }
    }
}
