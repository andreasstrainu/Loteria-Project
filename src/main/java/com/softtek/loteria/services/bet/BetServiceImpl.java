package com.softtek.loteria.services.bet;
import com.softtek.loteria.model.User;
import com.softtek.loteria.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BetServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(BetServiceImpl.class);

    private final UserRepository userRepository;

    public BetServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void addBet(String userDni, List<Integer> numbers) {
        User user = userRepository.findByDni(userDni)
                .orElseThrow(() -> {
                    logger.error("Usuario no encontrado");
                    return new RuntimeException("User not found");
                });

        validateBet(numbers);

        if (user.getBets().contains(numbers)) {
            logger.warn("Usuario repitió apuesta");
        }

        user.getBets().add(numbers);

        logger.info("Apuesta añadida");
        logger.debug("Apuesta: {} para usuario {}", numbers, userDni);
    }

    private void validateBet(List<Integer> numbers)
    {
        if (numbers.size() != 6) {
            logger.error("Apuesta inválida: tamaño incorrecto");
            throw new RuntimeException("Bet must have 6 numbers");
        }

        Set<Integer> set = new HashSet<>(numbers);

        if (set.size() != 6) {
            logger.error("Apuesta inválida: números repetidos");
            throw new RuntimeException("Numbers cannot repeat");
        }

        for (int n : numbers) {
            if (n < 1 || n > 49) {
                logger.error("Número fuera de rango");
                throw new RuntimeException("Numbers must be between 1 and 49");
            }
        }
    }
}