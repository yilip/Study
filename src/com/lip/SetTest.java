package com.lip;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Lip
 * @Date 2016-09-29 16:58
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set=new HashSet<>();
        set.add("1");
        set.add("2");
        System.out.println(set.contains(1));
    }
}
