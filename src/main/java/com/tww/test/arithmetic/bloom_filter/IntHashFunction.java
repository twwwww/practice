package com.tww.test.arithmetic.bloom_filter;

import java.util.Objects;

public interface IntHashFunction {
    int hash(int value);

    static IntHashFunction defaultHash() {
        return Objects::hashCode;
    }

}
