package ru.khafizov.shop.users.services;

import ru.khafizov.shop.users.models.User;
import ru.khafizov.shop.users.repositories.UsersRepository;
import ru.khafizov.shop.users.validators.EmailValidator;

import java.util.UUID;

public class UsersService {
    private final EmailValidator emailValidator;

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository, EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
        this.usersRepository = usersRepository;
    }

    public void register(String name, String email, String password) {
        // TODO: сделать валидацию имени и пароля
        emailValidator.validate(email);

        User user = new User(UUID.randomUUID().toString(), name, email, password);

        usersRepository.save(user);
    }
}
