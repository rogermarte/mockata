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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

    @Test
    public void should_return_36_when_two_players_exists_with_34_and_38_age() throws Exception {
        // Given
        given(playerRepository.obtainAllPlayers()).willReturn(playersWith34And38());
        // When
        int age = getAgeAverage.calculate();
        // Then
        assertThat(age).isEqualTo(36);
    }

    @Test
    public void should_return_average_when_players_are_left_handed() throws Exception {
        // Given
        given(playerRepository.obtainLeftHandedPlayers()).willReturn(playersWith34And38());
        given(playerRepository.obtainRightHandedPlayers()).willReturn(inconsistenceOldPlayer());
        // When
        int average = getAgeAverage.calculate(false);
        // Then
        assertThat(average).isEqualTo(36);
        verify(playerRepository, times(1)).obtainLeftHandedPlayers();
        verify(playerRepository, never()).obtainRightHandedPlayers();
    }

    @Test
    public void should_return_average_when_players_are_rigth_handed() throws Exception {
        // Given
        given(playerRepository.obtainRightHandedPlayers()).willReturn(playersWith34And38());
        given(playerRepository.obtainLeftHandedPlayers()).willReturn(inconsistenceOldPlayer());
        // When
        int average = getAgeAverage.calculate(true);
        // Then
        assertThat(average).isEqualTo(36);
        verify(playerRepository, times(1)).obtainRightHandedPlayers();
        verify(playerRepository, never()).obtainLeftHandedPlayers();
    }

    private List<Player> playersWith34And38() {
        return Arrays.asList(new Player(34),
                new Player(38));
    }

    private List<Player> inconsistenceOldPlayer() {
        return Collections.singletonList(new Player(100));
    }

    private List<Player> inconsistencePlayer() {
        return Collections.singletonList(new Player(-1));
    }
}
