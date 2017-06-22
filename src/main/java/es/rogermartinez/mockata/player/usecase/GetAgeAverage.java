package es.rogermartinez.mockata.player.usecase;

import es.rogermartinez.mockata.player.exception.InconsistencePlayerAgeException;
import es.rogermartinez.mockata.player.exception.NoPlayerFoundException;

public interface GetAgeAverage {
    int calculate() throws InconsistencePlayerAgeException, NoPlayerFoundException;

    int calculate(boolean rigthHanded) throws InconsistencePlayerAgeException, NoPlayerFoundException;
}
