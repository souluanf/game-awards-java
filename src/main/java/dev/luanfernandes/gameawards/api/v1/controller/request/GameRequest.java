package dev.luanfernandes.gameawards.api.v1.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GameRequest {
    String name;
    String description;
    String cover;
    Long votes;
}