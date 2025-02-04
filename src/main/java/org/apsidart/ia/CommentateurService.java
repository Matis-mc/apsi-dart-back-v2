package org.apsidart.ia;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

import static org.apsidart.common.Constants.Prompt.DART_COMMENTATEUR_INIT;
import static org.apsidart.common.Constants.Prompt.DART_COMMENTAIRE_CONTEXT;


@RegisterAiService
public interface CommentateurService {

    // Scope / context passed to the LLM
  @SystemMessage(DART_COMMENTATEUR_INIT)
  // Prompt (with detailed instructions and variable section) passed to the LLM
  @UserMessage(DART_COMMENTAIRE_CONTEXT)
  String commentVolee(String question);

}