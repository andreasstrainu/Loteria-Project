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
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("El usuario y el id no pueden ser nulos");
        }
        if (usersStorage.containsKey(user.getId())) {
            throw new IllegalArgumentException("El usuario con el id " + user.getId() + " ya existe");
        }

    }

    @Override
    public Optional<User> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return Optional.ofNullable(usersStorage.get(id));
    }

    @Override
    public List<User> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(usersStorage.values()));
    }

    @Override
    public boolean existsById(String id) {
        return id != null && !id.isBlank() && usersStorage.containsKey(id);
    }

    @Override
    public void update(User user) {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("El usuario y el id no pueden ser nulos");
        }
        if (!usersStorage.containsKey(user.getId())) {
            throw new IllegalArgumentException("El usuario con el id " + user.getId() + " no existe");
        }
        usersStorage.put(user.getId(), user);

    }
}
