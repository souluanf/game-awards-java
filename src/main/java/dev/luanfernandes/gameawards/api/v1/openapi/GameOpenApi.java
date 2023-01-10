package dev.luanfernandes.gameawards.api.v1.openapi;

import dev.luanfernandes.gameawards.api.v1.controller.request.GameRequest;
import dev.luanfernandes.gameawards.api.v1.controller.response.GameResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Game")
public interface GameOpenApi {

    @Operation(summary = "findAll",description = "Retornar lista com todos os jogos")
    List<GameResponse> findAll();
    @Operation(summary = "by id",description = "Consulta do jogo pelo ID ")
    GameResponse findById(@Parameter Long id);

    @Operation(summary = "save",description = "Armazenar novo jogo")
    void insert(@Parameter GameRequest game);

    @Operation(summary = "update",description = "Localizar e Atualizar jogo existente")
    void update(@Parameter Long id, @Parameter GameRequest game);
    @Operation(summary = "delete",description = "Deletar jogo do banco de dados")
    void delete(@Parameter Long id);
}
