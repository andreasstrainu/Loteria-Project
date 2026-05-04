package com.softtek.loteria.repository.user;

import com.softtek.loteria.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByDni(String dni);
    List<User> findAll();
    boolean existsByDni(String dni);
    void update(User user);
}
