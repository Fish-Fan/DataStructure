package com.company.queue;

import java.util.Iterator;

/**
 * Created by yanfeng-mac on 2017/11/24.
 */
public class MyLinkedQueue<E> {
    private static class Node<E> {
        private Node pre;
        private E element;
        private Node next;


        public Node(Node<E> pre,E element,Node<E> next) {
            this.pre = pre;
            this.element = element;
            this.next = next;
        }
    }



    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedQueue(int size) {
        this();
        this.size = size;
        Node first = new Node(null,new Integer(0),null);
        tail = head = first;
        for(int i = 1;i < size;i++) {
            Node n = new Node(tail,new Integer(i),null);
            tail.next = n;
            tail = n;
        }
    }

    public boolean add(E e) {
        Node<E> last = tail;
        Node<E> n = new Node<E>(tail,e,null);
        if(last == null) {
            head = n;
        } else {
            tail.next = n;
            tail = n;
            size++;
        }
        return true;
    }

    public Object remove() {
        if(tail == head) {
            return null;
        } else {
            Node<E> h = head;
            head = h.next;
            size--;
            return h.element;
        }

    }

    public void print() {
        Node<E> cursor = head;
        while (cursor != null) {
            if(cursor.element != null)
                System.out.print(cursor.element + "->");
            else
                System.out.print("null->");
            cursor = cursor.next;
        }
    }


}
