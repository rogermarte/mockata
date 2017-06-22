package es.rogermartinez.mockata.player.usecase.impl;

import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;
import es.rogermartinez.mockata.player.repository.PlayerRepository;
import es.rogermartinez.mockata.player.repository.impl.PlayerInMemory;
import es.rogermartinez.mockata.player.usecase.GetAgeAverage;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetAgeAverageImplTest {

    private PlayerRepository playerRepository = new PlayerInMemory();

    @Test(expected = NoPlayerFoundException.class)
    public void should_throws_no_player_exception_when_repository_is_null() throws Exception {
        // Given
        GetAgeAverage getAgeAverage = new GetAgeAverageImpl(null);
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }
}