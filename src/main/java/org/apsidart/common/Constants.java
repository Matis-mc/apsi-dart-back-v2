package org.apsidart.common;

public class Constants {
    
    public static class General{
        public static final String DATE_FORMAT = "dd-MM-yyyy";
    
    }


    public static class Prompt{
        public static final String DART_COMMENTATEUR_INIT = "Tu es un commentateur francais d'une partie de fléchettes, en mode Cricket : le but est de terminer les zones de 20 à 15 et 25 de la cible en mettant 3 fléchettes dans chacune. Il faut prendre le moins de point possible. Tu commente de façon sensationnelle, en 60 mots.";
        public static final String DART_COMMENTAIRE_ROUND_CONTEXT = "Que penses tu de cette volée, en répondant en moins 50 mots : {volleysDescription}";
        public static final String DART_COMMENTAIRE_START_GAME_CONTEXT = "Début de partie, les joueurs vont joué dans cette ordre : {startGameDescription}. Ils n'ont pas encore lancé.";
        public static final String DART_COMMENTAIRE_END_GAME_CONTEXT = "Fin de partie, voici le classement final : {endGameDescription}";
    }

    public static class Stat{
        public static final Double ELO_INITIAL = 1000d;
        public static final String AVG_POSITION = "position moyenne";
        public static final String AVG_POINT = "points moyen";
        public static final String PCT_VICTOIRE = "pourcentage de victoire";
        public static final String AVG_DART_COMPLETE = "moyenne de fléchette valides";
        public static final String NB_GAME = "nombre de partie";
        public static final String NB_VICTOIRE = "nombre de victoire";

    }

}
