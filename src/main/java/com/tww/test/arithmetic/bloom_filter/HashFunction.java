package com.tww.test.arithmetic.bloom_filter;

public interface HashFunction<T> {
    int hash(T t);

    static <T> HashFunction<T> defaultHash() {
        return Object::hashCode;
    }
}
