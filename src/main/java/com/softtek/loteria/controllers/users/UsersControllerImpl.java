package com.softtek.loteria.controllers.users;

import java.net.URI;
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
    public ResponseEntity<Void> registerUser(@PathVariable String dni, @RequestBody User user) {
        user.setDni(dni);
        Optional<User> registerUser = userService.registerUser(user);

        if (registerUser.isEmpty()) {
            userService.registerUser(user);
            return ResponseEntity.
                    created(URI.create("/restaurante/locales/" + user.getDni())). //TODO Cambiar ruta
                            build();
        } else {
            return ResponseEntity.
                    noContent().
                    header("Content-Location", "/restaurante/locales/" + user.getDni()).   //TODO Cambiar ruta
                            build();
        }
    }

    @Override
    @GetMapping("/{dni}")
    public User getUserByDni(String dni) {
        Optional<User> user = userService.getUser(Integer.parseInt(dni));

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User no encontrado");
        } else {
            return user.get();
        }
        return null;
    }

    @Override
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @Override
    @PutMapping("{dni}/bets")
    public ResponseEntity<Void> registerBet(@PathVariable String dni, @RequestParam Bet bet) {
        User user = getUserByDni(dni);
        Optional<User> registerBet = userService.registerBet(user, bet);


    }
}
