package com.company.stack;

/**
 * Created by yanfeng-mac on 2017/11/23.
 */
public class MyStack<E> {
    private static class Node<E> {
        Node<E> next;
        E element;

        public Node(E ele,Node<E> next) {
            element = ele;
            this.next = next;
        }
    }

    private Node<E> top;

    public MyStack() {
        top = null;
    }

    public MyStack(int count) {
        this();
        for(int i = 0;i < count;i++) {
            Node node = new Node(i,top);
            top = node;
        }
    }

    public boolean push(E ele) {
        Node<E> n = new Node<E>(ele,top);
        top = n;
        return true;
    }

    public E pop() {
        Node<E> node = top;
        top = top.next;
        return node.element;
    }


    public void print() {
        Node<E> cursor = top;
        while (cursor != null) {
            System.out.print(cursor.element + "->");
            cursor = cursor.next;
        }
    }
}
