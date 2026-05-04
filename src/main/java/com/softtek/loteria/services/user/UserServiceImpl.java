package com.softtek.loteria.services.user;

import com.softtek.loteria.models.User;
import com.softtek.loteria.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl
{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User createUser(String id, String name)
    {
        if (userRepository.existsById(id))
        {
            logger.error("Intento de crear usuario duplicado");
            throw new RuntimeException("User already exists");
        }

        User user = new User(id, name);
        userRepository.save(user);

        logger.info("Usuario creado");
        logger.debug("Usuario creado: {}", user);

        return user;
    }

    public List<User> getAllUsers()
    {
        logger.info("Obteniendo lista de usuarios");
        return userRepository.findAll();
    }

    public User getUserById(String id)
    {
        return userRepository.findById(id).orElseThrow(() ->
                {
                    logger.error("Usuario no encontrado");
                    return new RuntimeException("User not found");
                });
    }
}