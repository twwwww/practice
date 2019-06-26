package com.tww.test.functionl.curring;

@FunctionalInterface
public interface AddFunction<T> {

    AddFunction apply(T t);

}