package com.tww.test.code;

import java.util.ArrayList;
import java.util.List;

public class ThisDemo {

    private List<String> data = new ArrayList<>();

    public void someMethod(String param) {

        if (this.data != null && data.size() > 0 && data.contains(param)) {
            System.out.println(data.indexOf(param));
        }

    }
}