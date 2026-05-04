package com.softtek.loteria.repository.bet;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BetRepositoryImpl implements BetRepository {

    private final Map<String, List<List<Integer>>> betsStorage = new ConcurrentHashMap<>();

    @Override
    public void saveBet(String userDni, List<Integer> bet) {
        if (userDni == null || userDni.isBlank()) {
            throw new IllegalArgumentException("El DNI del usuario no puede ser nulo o vacío");
        }
        if (bet == null || bet.isEmpty()) {
            throw new IllegalArgumentException("La apuesta no puede ser nula o vacía");

        }
    }

    @Override
    public List<List<Integer>> findBetsByUserDni(String userDni) {
        if (userDni == null || userDni.isBlank()) {
            return Collections.emptyList();
        }
        List<List<Integer>> bets = betsStorage.get(userDni);
        if (bets == null) {
            return Collections.emptyList();
        }


        return Collections.unmodifiableList(
            bets.stream()
                    .map(ArrayList::new)
                    .toList());
    }

    @Override
    public boolean hasBets(String userDni) {
        if(userDni == null || userDni.isBlank()) {
            return false;
        }
        List<List<Integer>> bets = betsStorage.get(userDni);
        return bets != null && !bets.isEmpty();
    }

}
