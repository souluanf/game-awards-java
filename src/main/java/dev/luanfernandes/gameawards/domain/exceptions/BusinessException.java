package dev.luanfernandes.gameawards.domain.exceptions;

import java.io.Serial;

public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1469551431955562533L;

    public BusinessException(String message) {
        super(message);
    }
}
