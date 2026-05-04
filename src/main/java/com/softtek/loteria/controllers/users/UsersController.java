package com.softtek.loteria.controllers.users;

import com.softtek.loteria.model.Bet;
import com.softtek.loteria.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UsersController {
    ResponseEntity<Void> registerUser(String dni, Map<String, String> request);

    User getUserByDni(String dni);

    List<User> getUsers();
}
