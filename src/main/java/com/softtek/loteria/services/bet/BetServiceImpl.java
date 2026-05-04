package com.softtek.loteria.services.bet;

import com.softtek.loteria.repository.user.UserRepository;
import com.softtek.loteria.validator.BetValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    private static final Logger logger = LoggerFactory.getLogger(BetServiceImpl.class);

    private final UserRepository userRepository;

    public BetServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addBet(String userId, Integer[] numbers) {
        if (userId == null || userId.isBlank()) {
            logger.error("UserId no puede ser nulo o vacío");
            throw new IllegalArgumentException("UserId no puede ser nulo o vacío");
        }

        if (numbers == null || numbers.length == 0) {
            logger.error("Los números de la apuesta no pueden ser nulos o vacíos");
            throw new IllegalArgumentException("Los números de la apuesta no pueden ser nulos o vacíos");
        }

        List<Integer> betNumbers = new ArrayList<>(Arrays.asList(numbers));

        try {
            BetValidator.validateBet(betNumbers);
        } catch (IllegalArgumentException e) {
            logger.error("Validación de apuesta fallida: {}", e.getMessage());
            throw e;
        }

        userRepository.findByDni(userId).ifPresentOrElse(
                user -> {
                    user.getBets().add(betNumbers);
                    userRepository.update(user);
                    logger.info("Apuesta añadida para usuario: {}", userId);
                    logger.debug("Apuesta añadida: {}", betNumbers);
                },
                () -> {
                    logger.error("Usuario con DNI {} no encontrado", userId);
                    throw new RuntimeException("Usuario no encontrado");
                }
        );
    }
}