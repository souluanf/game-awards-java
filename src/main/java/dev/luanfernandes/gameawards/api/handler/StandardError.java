package dev.luanfernandes.gameawards.api.handler;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@AllArgsConstructor
@ToString
@Schema(name = "Problem")
public class StandardError{
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}