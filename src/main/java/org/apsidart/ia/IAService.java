package org.apsidart.ia;

import java.util.List;

import org.apsidart.dart.game.dto.PlayerPeformanceDto;
import org.apsidart.dart.performance.DartPerformanceService;
import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IAService {

    @Inject                                            
    CommentateurService commentateurService;

    private static final Logger LOG = Logger.getLogger(DartPerformanceService.class);

    
    public String getCommentaire(List<PlayerPeformanceDto> playerPeformanceDtos){
        try {
            LOG.info("[DO] Generation de commentaire avec les endpoint OVH : ");
            return commentateurService.commentVolee(constructPromptFromContext(playerPeformanceDtos));
        } catch (RuntimeException e) {
            LOG.warn("Impossible d'appeler les endpoint OVH : " + e);
            return "ça me coupe la chique !";
        }
    }

    private String constructPromptFromContext(List<PlayerPeformanceDto> playerPeformanceDtos){
        String prompt = "Tour " + playerPeformanceDtos.get(0).getNumberRound() + ". ";
        for (PlayerPeformanceDto p : playerPeformanceDtos){
            prompt += p.getPseudo() + " a lancé " + describeVolee(p.getVolley()) + "et a " + p.getScore() + "points";  
        }
        LOG.warn(prompt);
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
    
}
