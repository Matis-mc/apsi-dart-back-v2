package org.apsidart.dart.comment;

import java.util.List;

import org.apsidart.dart.game.dto.CommentDto;
import org.apsidart.dart.game.dto.PlayerPerformanceDto;
import org.apsidart.dart.performance.DartPerformanceService;
import org.apsidart.ia.AIDartService;
import org.apsidart.player.dto.PlayerDto;

@ApplicationScoped
public class CommentService {

    @Inject                                            
    private AIDartService commentateurService;

    private static final Logger LOG = Logger.getLogger(DartPerformanceService.class);
    private static final String CORRECT_CHAR_REGEX = "^[a-zA-ZÀ-Ÿ-.!?]$";

    public CommentDto getDartStartGameCommentaire(List<PlayerDto> playerDtos){
        try {
            LOG.info("[DO] Generation de commentaire avec les endpoint OVH : ");
            String commentaire = commentateurService.commentStartGame(constructStartGamePrompt(playerDtos));
            return new CommentDto(checkIaReturn(commentaire));
        } catch (RuntimeException e) {
            LOG.warn("Impossible d'appeler les endpoint OVH : " + e);
            return new CommentDto("ça me coupe la chique !");
        }
    }
    
    public CommentDto getDartRoundCommentaire(List<PlayerPerformanceDto> playerPeformanceDtos){
        try {
            LOG.info("[DO] Generation de commentaire avec les endpoint OVH : ");
            String commentaire = commentateurService.commentVolee(constructRoundPrompt(playerPeformanceDtos));
            return new CommentDto(checkIaReturn(commentaire));
        } catch (RuntimeException e) {
            LOG.warn("Impossible d'appeler les endpoint OVH : " + e);
            return new CommentDto("ça me coupe la chique !");
        }
    }

    private String constructStartGamePrompt(List<PlayerPerformanceDto> playerOrdered){
        String prompt = "Premier tour. ";
        for (PlayerPerformanceDto p : playerOrdered){
            prompt += p.getPseudo() + " commence en position " + p.getPosition() + ", ";  
        }
        return prompt;
    }

    private String constructRoundPrompt(List<PlayerPerformanceDto> playerPeformanceDtos){
        String prompt = "Tour " + playerPeformanceDtos.get(0).getNumberRound() + ". ";
        for (PlayerPerformanceDto p : playerPeformanceDtos){
            prompt += p.getPseudo() + " a lancé " + describeVolee(p.getVolley()) + "et a " + p.getScore() + "points, ";  
        }
        return prompt;
    }

    private String describeVolee(String volee){
        String voleeDescription = "";
        String[] fleches = volee.split("-");
        for(String f : fleches){
            if(f.contains("T")){
                voleeDescription += "triple " + f.substring(1, 3);
            } else if(f.contains("D")){
                voleeDescription += "double " + f.substring(1, 3);
            } else {
                voleeDescription += f;
            }
            voleeDescription += ", ";
        }
        return voleeDescription;
    }

    /*
     * On vérifie que le retour pourra bien être interprété comme un String
     * Le llm peut parfois rajouter des caractères incompatibles au début et/ou a la fin des commentaires.
     */
    private String checkIaReturn(String iaReturn){
        String firstChar = iaReturn.substring(0, 1);
        while(!firstChar.matches(CORRECT_CHAR_REGEX)){
            iaReturn = iaReturn.substring(1);
            firstChar = iaReturn.substring(0, 1);
        }
        String lastChar = iaReturn.substring(iaReturn.length() - 1);
        while(!lastChar.matches(CORRECT_CHAR_REGEX)){
            iaReturn = iaReturn.substring(0, iaReturn.length() - 1);
            lastChar = iaReturn.substring(iaReturn.length() - 1);
        }
        return iaReturn;
    }
}
