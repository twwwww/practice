package com.tww.test.code;

import java.util.ArrayList;
import java.util.List;

public class LocalDemo {

    private List<String> data = new ArrayList<>();

    public void someMethod(String param) {
        List<String> data = this.data;
        if (data != null && data.size() > 0 && data.contains(param)) {
            System.out.println(data.indexOf(param));
        }

    }

}