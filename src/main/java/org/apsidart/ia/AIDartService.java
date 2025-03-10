package org.apsidart.ia;

import static org.apsidart.common.Constants.Prompt.DART_COMMENTAIRE_END_GAME_CONTEXT;
import static org.apsidart.common.Constants.Prompt.DART_COMMENTAIRE_ROUND_CONTEXT;
import static org.apsidart.common.Constants.Prompt.DART_COMMENTAIRE_START_GAME_CONTEXT;
import static org.apsidart.common.Constants.Prompt.DART_COMMENTATEUR_INIT;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;


@RegisterAiService
public interface AIDartService {

    // Scope / context passed to the LLM
  @SystemMessage(DART_COMMENTATEUR_INIT)
  // Prompt (with detailed instructions and variable section) passed to the LLM
  @UserMessage(DART_COMMENTAIRE_ROUND_CONTEXT)
  String commentVolee(String volleysDescription);

  @SystemMessage(DART_COMMENTATEUR_INIT)
  @UserMessage(DART_COMMENTAIRE_END_GAME_CONTEXT)
  String commentEndGame(String endGameDescription);

  @SystemMessage(DART_COMMENTATEUR_INIT)
  @UserMessage(DART_COMMENTAIRE_START_GAME_CONTEXT)
  String commentStartGame(String startGameDescription);

}