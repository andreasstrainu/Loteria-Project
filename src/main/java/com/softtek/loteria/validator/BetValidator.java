package com.softtek.loteria.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BetValidator {
    private static final int BET_SIZE = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 49;

    public static void validateBet(List<Integer> bet) {
        if (bet == null) {
            throw new IllegalArgumentException("La apuesta no puede ser nula");
        }
        if (bet.size() != BET_SIZE) {
            throw new IllegalArgumentException(
                    String.format("La apuesta debe contener exactamente %d números, se recibieron %d", BET_SIZE, bet.size())
            );
        }
        Set<Integer> uniqueNumbers = new HashSet<>();

        for (Integer number : bet) {
            if (number == null) {
                throw new IllegalArgumentException("La apuesta no puede contener números nulos");
            }
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new IllegalArgumentException(
                        String.format("Los números de la apuesta deben estar entre %d y %d. Recibido: %d", MIN_NUMBER, MAX_NUMBER, number)
                );
            }
            if (!uniqueNumbers.add(number)) {
                throw new IllegalArgumentException(
                        String.format("El número %d está repetido", number)
                );
            }
        }
    }

    public static boolean areBetsEqual(List<Integer> bet1, List<Integer> bet2) {
        if (bet1 == null || bet2 == null) {
            return false;
        }
        if (bet1.size() != bet2.size()) {
            return false;
        }
        Set<Integer> set1 = new HashSet<>(bet1);
        Set<Integer> set2 = new HashSet<>(bet2);
        return set1.equals(set2);
    }
}
