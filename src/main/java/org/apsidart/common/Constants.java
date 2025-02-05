package org.apsidart.common;

public class Constants {
    
    public static class General{
        public static final String DATE_FORMAT = "dd-MM-yyyy";
    
    }


    public static class Prompt{
        public static final String DART_COMMENTATEUR_INIT = "Tu es un commentateur francais d'une partie de fléchettes, en mode Cricket.";
        public static final String DART_COMMENTAIRE_CONTEXT = "Tu  dois commenter en maximum 50 mots, de façon sensationnelle le tour qui vient d'être joué : {question}";
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
