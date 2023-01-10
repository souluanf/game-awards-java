package dev.luanfernandes.gameawards.domain.exceptions;

import java.io.Serial;

public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4858613019815658897L;

    public EntityNotFoundException(Integer id) {
        super("Entity with id [" + id + "] not found.");
    }
    public EntityNotFoundException(Long id) {
        super("Entity with id [" + id + "] not found.");
    }
}
