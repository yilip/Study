package com.lip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lip on 2016-12-13 17:29
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.subList(2,2));
    }
}
