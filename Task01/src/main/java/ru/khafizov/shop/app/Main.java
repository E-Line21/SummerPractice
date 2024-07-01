package ru.khafizov.shop.app;

import ru.khafizov.shop.users.controllers.UsersUIConsole;
import ru.khafizov.shop.users.repositories.UsersRepository;
import ru.khafizov.shop.users.repositories.impl.UsersRepositoryFileImpl;
import ru.khafizov.shop.users.services.UsersService;
import ru.khafizov.shop.users.validators.EmailValidator;
import ru.khafizov.shop.users.validators.SimpleEmailValidator;

public class Main {
    public static void main(String[] args) {
        EmailValidator emailValidator = new SimpleEmailValidator();
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");

        UsersService usersService = new UsersService(usersRepository, emailValidator);
        UsersUIConsole ui = new UsersUIConsole(usersService);
        ui.printRegistrationMenu();
    }
}
