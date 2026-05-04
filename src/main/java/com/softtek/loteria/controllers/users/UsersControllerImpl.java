package com.softtek.loteria.controllers.users;

import com.softtek.loteria.model.Bet;
import com.softtek.loteria.model.User;
import com.softtek.loteria.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("loteria/users")
public class UsersControllerImpl implements UsersController {

    private final UserService userService;

    public UsersControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping("/register/{dni}")
    public ResponseEntity<Void> registerUser(@PathVariable String dni, @RequestBody String name) {
        Optional<User> registerUser = Optional.ofNullable(userService.createUser(dni, name));

        if (registerUser.isEmpty()) {
            userService.createUser(dni, name);
            return ResponseEntity.
                    created(). //TODO Cambiar ruta
                            build();
        } else {
            return ResponseEntity.
                    noContent().
                    header().   //TODO Cambiar ruta
                            build();
        }
    }

    @Override
    @GetMapping("/{dni}")
    public User getUserByDni(String dni) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(dni));

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User no encontrado");
        } else {
            return user.get();
        }
    }

    @Override
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Override
    @PutMapping("{dni}/bets")
    public ResponseEntity<Void> registerBet(@PathVariable String dni, @RequestParam Bet bet) {
        User user = getUserByDni(dni);
        Optional<User> registerBet = userService.registerBet(user, bet);


    }
}
