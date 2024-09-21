package org.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Operations on LRU Cache:
 * LRUCache (Capacity c): Initialize LRU cache with positive size capacity c.
 * get (key): Returns the value of key ‘k’ if it is present in the cache otherwise it returns -1. Also updates the priority of data in the LRU cache.
 * put (key, value): Update the value of the key if that key exists, Otherwise, add a key-value pair to the cache. If the number of keys exceeds the capacity of the LRU cache then dismiss the least recently used key.
 * Working of LRU Cache:
 * Let’s suppose we have an LRU cache of capacity 3, and we would like to perform the following operations:
 * put (key=1, value=A) into the cache
 * put (key=2, value=B) into the cache
 * put (key=3, value=C) into the cache
 * get (key=2) from the cache
 * get (key=4) from the cache
 * put (key=4, value=D) into the cache
 * put (key=3, value=E) into the cache
 * get (key=4) from the cache
 * put (key=1, value=A) into the cache
 */

class LRU
{
    LinkedHashMap<Integer, Character> linkedHashMap ;
    int capacity;

    LRU(int c)
    {
        this.capacity = c;
        this.linkedHashMap = new LinkedHashMap<Integer, Character>(c, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Character> eldest) {
//                System.out.println("removing the key - " + eldest.getKey());
                return this.size() > c;
            }
        };
    }

    public char get(int key)
    {
        return linkedHashMap.getOrDefault(key, '@');
    }

    public void put(int key, char val)
    {
        linkedHashMap.put(key, val);
    }

}
public class Test2 {

    public static void main(String[] args)
    {
        LRU lruCache = new LRU(3);
        lruCache.put(1, 'A');
        lruCache.put(2, 'B');
        lruCache.put(3, 'C');
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
        lruCache.put(4, 'D');
        lruCache.put(5, 'E');
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(4));
        lruCache.put(1, 'A');
    }

}
