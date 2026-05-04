package com.softtek.loteria.validator;

import com.softtek.loteria.model.User;

public class UserValidator {
    private static final String USER_DNI_PATTERN = "^\\d{8}[A-Z]$";

    public static void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        validateUserDni(user.getDni());
        validateUserName(user.getName());
    }

    public static void validateUserDni(String dni) {
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("El DNI no puede ser nulo o vacío");
        }
        if (!dni.matches(USER_DNI_PATTERN)) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos seguidos de una letra mayúscula");
        }
    }

    public static void validateUserName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario no puede ser nulo o vacío");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("El nombre no puede exceder 100 caracteres");
        }
    }
}
