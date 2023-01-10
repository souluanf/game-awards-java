package dev.luanfernandes.gameawards.api.v1.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GameResponse {
    Long id;
    String name;
    String description;
    String cover;
    long votes;
}
