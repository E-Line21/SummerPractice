package ru.khafizov.shop.users.validators;

public interface EmailValidator {
    void validate(String email) throws IllegalArgumentException;
}
