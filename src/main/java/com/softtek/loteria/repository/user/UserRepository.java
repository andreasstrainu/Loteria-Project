package com.softtek.loteria.repository.user;

import com.softtek.loteria.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(String id);
    List<User> findAll();
    boolean existsById(String id);
    void update(User user);
}
