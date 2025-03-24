package org.apsidart.common;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListUtils {

    public static <T> T getLastElement(List<T> list){
        if(Objects.isNull(list) || list.isEmpty()){
            throw new NoSuchElementException();
        }
        return list.get(list.size() - 1);
    }

    public static <T> T getLastElement(List<T> list, T defaultValue){
        if(Objects.isNull(list) || list.isEmpty()){
            return defaultValue;
        }
        return list.get(list.size() - 1);
    }
    
}
