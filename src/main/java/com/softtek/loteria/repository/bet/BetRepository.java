package com.softtek.loteria.repository.bet;

import java.util.List;

public interface BetRepository {
    void saveBet(String userId, List<Integer> bet);
    List<List<Integer>> findBetsByUserId(String userId);
    boolean hasBets(String userId);
}