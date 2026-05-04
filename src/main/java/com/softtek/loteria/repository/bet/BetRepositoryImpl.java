package com.softtek.loteria.repository.bet;

import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BetRepositoryImpl implements BetRepository {

    private final Map<String, List<List<Integer>>> betsStorage = new ConcurrentHashMap<>();

    @Override
    public void saveBet(String userDni, List<Integer> bet) {
        if (userDni == null || userDni.isBlank() || bet == null || bet.isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos");
        }

        List<Integer> betCopy = new ArrayList<>(bet);
        betsStorage.computeIfAbsent(userDni, k -> Collections.synchronizedList(new ArrayList<>()))
                .add(betCopy);
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
                        .toList()
        );
    }

    @Override
    public boolean hasBets(String userDni) {
        if (userDni == null || userDni.isBlank()) {
            return false;
        }
        List<List<Integer>> bets = betsStorage.get(userDni);
        return bets != null && !bets.isEmpty();
    }
}
