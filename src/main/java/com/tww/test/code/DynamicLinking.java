package com.tww.test.code;

public class DynamicLinking {

    static abstract class Root {
        protected abstract void print();
    }

    static class NodeA extends Root {
        @Override
        protected void print() {
            System.out.println("NodeA...");
        }
    }

    static class NodeB extends Root {
        @Override
        protected void print() {
            System.out.println("NodeB...");
        }
    }

    public static void main(String[] args) {
        Root nodeA = new NodeA();
        Root nodeB = new NodeB();
        nodeA.print();
        nodeB.print();
        nodeA = new NodeB();
        nodeA.print();
    }
}