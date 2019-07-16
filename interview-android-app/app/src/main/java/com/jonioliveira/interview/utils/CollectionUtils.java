package com.jonioliveira.interview.utils;

import java.util.Collection;

public class CollectionUtils {
    public static <T> T find(final Collection<T> collection, final  Predicate<T> predicate){
        for (T item : collection){
            if (predicate.contains(item)){
                return item;
            }
        }
        return null;
    }
}
