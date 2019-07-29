package com.tww.test.datastructure.skiplist;

import lombok.Data;

import java.util.List;
import java.util.Random;

public class SkipList<T extends Comparable> {
    private int size;
    private Node<T> first;
    private int initIndexLevel;


    public SkipList() {
        this(4);
    }

    public SkipList(int initIndexLevel) {
        this.initIndexLevel = initIndexLevel;
    }

    public void insert(T data) {
        int level = random();

    }

    private int random() {
        int level = 0;
        Random random = new Random();
        if (random.nextInt(1) > 0) {
            level++;
        }
        return level;
    }

    @Data
    public class Node<T> {
        private Node<T> next;
        private List<Integer> indexes;
    }
}
