package es.rogermartinez.mockata.player.usecase.impl;

import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;
import es.rogermartinez.mockata.player.repository.PlayerRepository;
import es.rogermartinez.mockata.player.repository.impl.PlayerInMemory;
import es.rogermartinez.mockata.player.usecase.GetAgeAverage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

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

    @Test(expected = NoPlayerFoundException.class)
    public void should_throws_no_player_exception_when_repository_return_null() throws Exception {
        // Given
        PlayerRepository playerRepositoryMock = mock(PlayerRepository.class);
        given(playerRepositoryMock.obtainAllPlayers()).willReturn(null);
        GetAgeAverage getAgeAverage = new GetAgeAverageImpl(playerRepositoryMock);
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }
}
