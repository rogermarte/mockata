package es.rogermartinez.mockata.player.usecase.impl;

import es.rogermartinez.mockata.player.Player;
import es.rogermartinez.mockata.player.exception.InconsistencePlayerAgeException;
import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;
import es.rogermartinez.mockata.player.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class GetAgeAverageImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GetAgeAverageImpl getAgeAverage;

    @Test(expected = NoPlayerFoundException.class)
    public void should_throws_no_player_exception_when_repository_return_null() throws Exception {
        // Given
        given(playerRepository.obtainAllPlayers()).willReturn(null);
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }

    @Test(expected = NoPlayerFoundException.class)
    public void should_throws_no_player_exception_when_repository_return_no_players() throws Exception {
        // Given
        given(playerRepository.obtainAllPlayers()).willReturn(new ArrayList<Player>());
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }

    @Test(expected = InconsistencePlayerAgeException.class)
    public void should_throws_inconsistence_player_exception_when_age_is_lesser_than_0() throws Exception {
        // Given
        given(playerRepository.obtainAllPlayers()).willReturn(inconsistencePlayer());
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }

    @Test(expected = InconsistencePlayerAgeException.class)
    public void should_throws_inconsistence_player_exception_when_age_is_greater_than_99() throws Exception {
        // Given
        given(playerRepository.obtainAllPlayers()).willReturn(inconsistenceOldPlayer());
        // When
        getAgeAverage.calculate();
        // Then
        assertTrue(false);
    }

    private List<Player> inconsistenceOldPlayer() {
        return Collections.singletonList(new Player(100));
    }

    private List<Player> inconsistencePlayer() {
        return Collections.singletonList(new Player(-1));
    }
}
