package com.tww.test.arithmetic.bloom_filter;

import java.util.List;

public class BloomFilter<T> {
    private static final Integer INIT_SIZE = 1024_1024;
    private Integer size;
    private List<HashFunction<T>> hashFunctions;
    private List<T> initObjects;

    public BloomFilter() {
    }

    public BloomFilter(Integer size, List<HashFunction<T>> hashFunctions, List<T> initObjects) {
        this.size = size;
        this.hashFunctions = hashFunctions;
        this.initObjects = initObjects;
    }
}
