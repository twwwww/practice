package com.tww.test.code;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

public class Test {

    static int i = 11;
    int j = 12;

    public void run() {
        j = ++j;
    }


    public void test1() {
        int i = 1;
        Integer n = i;

        Integer j = Integer.valueOf(20);
        int m = j;
    }

    public void test2() {

    }

    public void test3() {
        List<String> strList = ImmutableList.of("13","24","35","46","57");
        strList.forEach( s -> { System.out.println(s); } );

        List<String> result = strList.stream().filter(string -> string.contains("3")).collect(Collectors.toList());
        result.forEach( s -> { System.out.println(s); } );
    }
    public void test4() {
        assert i == 5;
        System.out.println("true print");
    }


    public static void print(String... strs)
    {
        for (int i = 0; i < strs.length; i++)
        {
            System.out.println(strs[i]);
        }
    }

}
