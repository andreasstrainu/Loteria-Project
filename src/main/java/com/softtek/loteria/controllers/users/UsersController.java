package com.softtek.loteria.controllers.users;

import com.softtek.loteria.model.Bet;
import com.softtek.loteria.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersController {
    ResponseEntity<Void> registerUser(String dni, String name);

    User getUserByDni(String dni);

    List<User> getUsers();

    ResponseEntity<Void> registerBet(String dni, Bet bet);
}
