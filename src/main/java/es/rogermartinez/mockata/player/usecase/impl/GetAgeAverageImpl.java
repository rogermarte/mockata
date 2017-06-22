package es.rogermartinez.mockata.player.usecase.impl;

import es.rogermartinez.mockata.player.exception.InconsistencePlayerAgeException;
import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;
import es.rogermartinez.mockata.player.repository.PlayerRepository;
import es.rogermartinez.mockata.player.usecase.GetAgeAverage;

public class GetAgeAverageImpl implements GetAgeAverage {

    private PlayerRepository playerRepository;

    public GetAgeAverageImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public int calculate() throws InconsistencePlayerAgeException, NoPlayerFoundException {
        return 0;
    }
}
