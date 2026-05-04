package com.softtek.loteria.services.user;

public interface UserService
{
    void createUser(String id, String name);
    List<User> getAllUsers();
    User getUserById(String id);
}
