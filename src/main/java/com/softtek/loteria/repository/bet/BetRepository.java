package com.softtek.loteria.repository.bet;

import java.util.List;

public interface BetRepository {
    void saveBet(String userDni, List<Integer> bet);
    List<List<Integer>> findBetsByUserDni(String userDni);
    boolean hasBets(String userDni);
}