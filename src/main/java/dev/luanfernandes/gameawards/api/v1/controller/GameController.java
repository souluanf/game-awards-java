package dev.luanfernandes.gameawards.api.v1.controller;

import dev.luanfernandes.gameawards.api.v1.controller.request.GameRequest;
import dev.luanfernandes.gameawards.api.v1.controller.response.GameResponse;
import dev.luanfernandes.gameawards.api.v1.openapi.GameOpenApi;
import dev.luanfernandes.gameawards.domain.model.Game;
import dev.luanfernandes.gameawards.domain.service.GameService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/games")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameController implements GameOpenApi {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Override
    @GetMapping
    public List<GameResponse> findAll() {
        return gameService.findAll().stream().map(game -> modelMapper.map(game,GameResponse.class)).toList();
    }

    @Override
    @GetMapping("/{id}")
    public GameResponse findById(@PathVariable Long id) {
        return modelMapper.map(gameService.findById(id),GameResponse.class);
    }

    @Override
    @PostMapping
    public void insert(@RequestBody GameRequest gameRequest) {
        var obj = modelMapper.map(gameRequest, Game.class);
        gameService.insert(obj);
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody GameRequest gameRequest) {
        var game = modelMapper.map(gameRequest,Game.class);
        gameService.update(id,game);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameService.delete(id);
    }
}
