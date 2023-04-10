package com.example.comp3350.softwaresavants.persistence.hsqldb;

public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause) {
        super(cause);
    }
}
