package com.TCS;

import java.sql.Array;
import java.util.*;
import java.util.function.BiConsumer;

public class TCS {
    public static void main(String[] args) {
        List<String> al = new ArrayList<>();
        al.add("vasanth");
        al.add("vasanth");
        al.add("vikas");
        al.add("vikas");

        List<String> dub = new ArrayList<>();
        Set<String> st = new LinkedHashSet<>();
        for(String s: al){
            st.add(s);
        }

       BiConsumer<Integer,Integer> bi = (a,b)-> System.out.println(a+b);
        bi.accept(1,2);


        System.out.println(st);
    }
}
