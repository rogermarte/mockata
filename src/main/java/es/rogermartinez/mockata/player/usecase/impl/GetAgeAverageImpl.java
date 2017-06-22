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
        for (Player player : players) {
            if (player.getAge() < 0 || player.getAge() > 99) {
                throw new InconsistencePlayerAgeException();
            }
        }
        return 0;
    }

    private void validatePlayers(List<Player> players) throws NoPlayerFoundException {
        if (players == null
                || players.isEmpty()) {
            throw new NoPlayerFoundException();
        }
    }
}
