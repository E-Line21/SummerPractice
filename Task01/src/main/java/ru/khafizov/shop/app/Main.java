package ru.khafizov.shop.app;

import ru.khafizov.shop.users.controllers.UsersUIConsole;
import ru.khafizov.shop.users.models.User;
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

        testUserRepository(usersRepository);
    }

    private static void testUserRepository(UsersRepository usersRepository) {
        usersRepository.save(new User("1", "Bulat", "bulat@mail.ru", "12345678"));
        usersRepository.save(new User("2", "Petya", "petya@gmail.ru", "qwerty"));
        System.out.println(usersRepository.findAll());

        usersRepository.update(new User("2", "Petya", "petya_new@gmail.ru", "qwerty"));
        System.out.println(usersRepository.findById("2"));

        usersRepository.delete("1");
        System.out.println(usersRepository.findAll());
    }
}
