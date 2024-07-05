package ru.khafizov.shop.users.repositories.impl;

import ru.khafizov.shop.users.models.User;
import ru.khafizov.shop.users.repositories.UsersRepository;
import ru.khafizov.shop.users.repositories.exceptions.UserNotFoundException;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class UsersRepositoryFileImpl implements UsersRepository {
    private final String fileName;
    private static final String SEPARATOR = "\t";

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writeUser(writer, user);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return usersStream().toList();
    }

    @Override
    public void update(User user) throws UserNotFoundException{
        delete(user.getId());
        save(user);
    }

    @Override
    public void delete(String id) throws UserNotFoundException {
        List<User> users = findAll();
        boolean userFound = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (User user : users) {
                if (user.getId().equals(id)) {
                    userFound = true;
                }
                writeUser(writer, user);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        if (!userFound) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return usersStream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    private static User createUserFrom(String string) {
        String[] tokens = string.split(SEPARATOR);
        return new User(tokens[0], tokens[1], tokens[2], tokens[3]);
    }

    private void writeUser(Writer writer, User user) throws IOException {
        writer.write(user.getId() + SEPARATOR + user.getName() + SEPARATOR +
                user.getEmail() + SEPARATOR + user.getPassword());
        writer.write(System.lineSeparator());
    }

    private Stream<User> usersStream() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            return reader.lines()
                    .map(UsersRepositoryFileImpl::createUserFrom);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
