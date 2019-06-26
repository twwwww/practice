package com.tww.test.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 图 十字邻接数组
 */
public class AdjacencyTable<T> {
    class Side {
        T from;
        T to;

        public T getFrom() {
            return from;
        }

        public void setFrom(T from) {
            this.from = from;
        }

        public T getTo() {
            return to;
        }

        public void setTo(T to) {
            this.to = to;
        }
    }

    class Entry<K> {
        K node;
        List<Side> in;
        List<Side> out;

        public K getNode() {
            return node;
        }

        public void setNode(K node) {
            this.node = node;
        }

        public List<Side> getIn() {
            return in;
        }

        public void setIn(List<Side> in) {
            this.in = in;
        }

        public List<Side> getOut() {
            return out;
        }

        public void setOut(List<Side> out) {
            this.out = out;
        }
    }

    HashMap<Object,Entry> nodes;

    /**
     * 随机生成
     * @param clazz 节点类型
     * @param nodeNum 节点数
     */
    AdjacencyTable(int nodeNum,Class clazz) throws Exception {
        if (clazz == null) {
            clazz = Integer.class;
        }
        if (nodeNum < 1) {
            nodeNum = 10;
        }
        init(nodeNum,clazz);
    }

    private void init(int nodeNum, Class clazz) throws Exception {
        Random random = new Random();
        List list = new ArrayList();
        for (int i = 0; i < nodeNum; i++) {
            Object node = clazz.newInstance();
            list.add(node);
        }
        int sideNum = random.nextInt(nodeNum * (nodeNum - 1));
        genSide(list,sideNum);
    }

    private List<Entry> genSide(List<T> nodes,Integer sideNum) {
        List<Entry<T>> list = new ArrayList<Entry<T>>();
        int readySides = 0;
        while (readySides < sideNum) {

        }
        return null;
    }

    private void setSide(Side side) {
        T from = side.getFrom();
        T to = side.getTo();
        if (nodes.containsKey(from)) {
            Entry<T> entry = new Entry<T>();
            entry.setNode(from);
            ArrayList<Side> out = new ArrayList<Side>();
            out.add(side);
            entry.setOut(out);
        }
        if (nodes.containsKey(to)) {
            Entry<T> entry = new Entry<T>();
            entry.setNode(to);
            ArrayList<Side> in = new ArrayList<Side>();
            in.add(side);
            entry.setOut(in);
        }
    }
}