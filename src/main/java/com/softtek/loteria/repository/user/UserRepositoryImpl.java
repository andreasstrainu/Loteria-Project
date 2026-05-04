package com.softtek.loteria.repository.user;

import com.softtek.loteria.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<String, User> usersStorage = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        if (user == null || user.getDni() == null) {
            throw new IllegalArgumentException("El usuario y el DNI no pueden ser nulos");
        }
        if (usersStorage.containsKey(user.getDni())) {
            throw new IllegalArgumentException("El usuario con el DNI " + user.getDni() + " ya existe");
        }

    }

    @Override
    public Optional<User> findByDni(String dni) {
        if (dni == null || dni.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(usersStorage.get(dni));
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(usersStorage.values()));
    }

    @Override
    public boolean existsByDni(String dni) {
        return dni != null && !dni.isBlank() && usersStorage.containsKey(dni);
    }

    @Override
    public void update(User user) {
        if (user == null || user.getDni() == null) {
            throw new IllegalArgumentException("El usuario y el DNI no pueden ser nulos");
        }
        if (!usersStorage.containsKey(user.getDni())) {
            throw new IllegalArgumentException("El usuario con el DNI " + user.getDni() + " no existe");
        }
        usersStorage.put(user.getDni(), user);

    }
}
