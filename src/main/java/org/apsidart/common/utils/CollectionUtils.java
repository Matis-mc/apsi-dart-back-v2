package org.apsidart.common.utils;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CollectionUtils {
    
    public static <T> void replaceLastElement(@NotNull List<T> list, @NotNull T t ){
        if(list.isEmpty()){
            list.add(t);
        }else {
            list.set(list.size()-1, t);
        }
    }

    // Pas top le return null, c'est juste pour éviter un indexOutOfBoundException
    public static <T> T getLastIndex(List<T> list){
            if(list.isEmpty()){
                return null;
            }
            return list.get(list.size() - 1);
        }

}
