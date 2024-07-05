package ru.khafizov.shop.users.repositories.exceptions;

import ru.khafizov.shop.users.models.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(User user) {
        this(user.getId());
    }

    public UserNotFoundException(String id) {
        super("User with id " + id + " not found");
    }
}
