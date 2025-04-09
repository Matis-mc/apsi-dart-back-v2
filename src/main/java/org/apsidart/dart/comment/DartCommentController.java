package org.apsidart.dart.comment;

import java.util.List;

import org.apsidart.dart.game.dto.CommentDto;
import org.apsidart.dart.game.dto.PlayerPerformanceDto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/dart/comment")
@Valid
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DartCommentController {

    @Inject
    private CommentService commentService;

    @POST
    @Path("/game/{game-id}")
    public CommentDto commentOnStartGame(@Valid @PathParam("game-id") String gameId, @Valid List<PlayerPerformanceDto> playerDtos) {
        return commentService.getDartStartGameCommentaire(playerDtos);
    }

    @POST
    @Path("/game/{game-id}/round")
    public CommentDto commentOnRound(@Valid @PathParam("game-id") String gameId, @Valid List<PlayerPerformanceDto> performances){
        return commentService.getDartRoundCommentaire(performances);
    }
}
