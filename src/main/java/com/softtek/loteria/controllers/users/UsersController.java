package com.softtek.loteria.controllers.users;

import java.util.List;

public interface UsersController {
    ResponseEntity<Void> registerUser(String dni, User user);

    User getUserByDni(String dni);

    List<User> getUsers();

    ResponseEntity<Void> registerBet(String dni, Bet bet);
}
