package com.shun.framework;

import java.io.Serializable;

/**
 * User: mew <p />
 * Time: 17/11/6 11:06  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Pair<T, S> implements Serializable {

    private static final long serialVersionUID = -7854458964101354741L;

    private T left;

    private S right;

    public Pair(T t, S s) {
        this.left = t;
        this.right = s;
    }

    private Pair() {
    }

    public static <T, S> Pair<T, S> of(T t, S s) {
        return new Pair(t, s);
    }

    public T getLeft() {
        return this.left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public S getRight() {
        return this.right;
    }

    public void setRight(S right) {
        this.right = right;
    }

}
