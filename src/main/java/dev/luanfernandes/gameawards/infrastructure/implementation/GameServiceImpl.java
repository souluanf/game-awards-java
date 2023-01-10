package dev.luanfernandes.gameawards.infrastructure.implementation;

import dev.luanfernandes.gameawards.domain.exceptions.EntityNotFoundException;
import dev.luanfernandes.gameawards.domain.model.Game;
import dev.luanfernandes.gameawards.domain.service.GameService;
import dev.luanfernandes.gameawards.infrastructure.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void insert(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        var obj = findById(id);
        obj.setName(game.getName());
        obj.setDescription(game.getDescription());
        obj.setCover(game.getCover());
        obj.setVotes(game.getVotes());
        gameRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        var obj = findById(id);
        gameRepository.deleteById(obj.getId());
    }
}
