package com.softtek.loteria.services.user;

import com.softtek.loteria.model.User;

import java.util.List;

public interface UserService
{
    User createUser(String id, String name);
    List<User> getAllUsers();
    User getUserById(String id);
}
