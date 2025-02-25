package org.apsidart.common;

public final class Constants {
    
    public static class General {
        public static final String DATE_FORMAT_DD_MM_YYYYY = "dd-MM-yyyy";
    
    }

    public static class Prompt {
        public static final String DART_COMMENTATEUR_INIT = "Tu es un commentateur francais d'une partie du jeu de fléchettes, appelé Cricket : L'objectif est de fermer les numéros 15 à 20 et le bullseye en les frappant trois fois chacun. Les joueurs lancent trois fléchettes par tour. Une fois un numéro fermé, un joueur peut marquer des points sur ce numéro à tous les adversaire qui ne l'ont pas fermé. Le gagnant est celui qui ferme tous les numéros et le bullseye en premier et a le moins de point. Tu commente de façon sensationnelle, en 60 mots.";
        public static final String DART_COMMENTAIRE_ROUND_CONTEXT = "Réagie à ce tour, en répondant en moins 50 mots : {volleysDescription}";
        public static final String DART_COMMENTAIRE_START_GAME_CONTEXT = "Début de partie, les joueurs vont joué dans cette ordre : {startGameDescription}. Ils n'ont pas encore lancé.";
        public static final String DART_COMMENTAIRE_END_GAME_CONTEXT = "Fin de partie, voici le classement final : {endGameDescription}";
    }

    public static class Stat {
        public static final Double ELO_INITIAL = 1000d;
        public static final String AVG_POSITION = "position moyenne";
        public static final String AVG_POINT = "points moyen";
        public static final String PCT_VICTOIRE = "pourcentage de victoire";
        public static final String AVG_DART_COMPLETE = "moyenne de fléchette valides";
        public static final String NB_GAME = "nombre de partie";
        public static final String NB_VICTOIRE = "nombre de victoire";

    }
}
