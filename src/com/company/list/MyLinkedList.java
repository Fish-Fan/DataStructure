package com.company.list;

import com.company.queue.MyLinkedQueue;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by yanfeng-mac on 2017/11/23.
 */
public class MyLinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> pre;
        private Node<E> next;

        public Node(Node<E> pre,E element,Node<E> next) {
            this.element = element;
            this.pre = pre;
            this.next = next;
        }
    }

    private class Itr implements Iterator<E> {
        private MyLinkedList.Node<E> cursor = head;
        private int index;

        @Override
        public boolean hasNext() {
            if(cursor == null) {
                return false;
            } else {
                if(index <= size) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        public E next() {
            if(hasNext()) {
                E result =  (E)cursor.element;
                cursor = cursor.next;
                index++;
                return result;
            } else {
                return null;
            }
        }

        @Override
        public void remove() {
            if(cursor != null) {
                cursor.pre.next = cursor.next;
                cursor = cursor.next;
            }
        }
    }

    public Node<E> head;
    public Node<E> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
    }

    //生成一个包含10个0的链表
    public MyLinkedList(int count) {
        this();
        for(int i = 0;i < count;i++) {
            Node node = new Node(null,0,head);
            head = node;
        }
        size = count;
    }


    public E getElement(int i) {
        return getNode(i).element;
    }

    public Node<E> getNode(int i) {
        int j = 1;
        Node<E> cursor = head;
        while (cursor != null && j < i) {
            cursor = cursor.next;
            j++;
        }

        if(cursor == null || j > i)
            return null;
        return cursor;
    }

    public boolean insertElement(E ele) {
        Node<E> l = tail;
        Node<E> node = new Node(l,ele,null);
        tail = node;
        if(head == null) {
            head = node;
        } else {
            l.next = node;
        }
        size++;
        return true;
    }

    public boolean insertElement(E ele,int index) {

        Node<E> n = getNode(index);
        Node<E> newNode = new Node<E>(n,ele,n.next);
        newNode.next = n.next;
        n.next = newNode;

        if(n == null)
            return false;
        size++;
        return true;
    }

    public boolean addFirst(E ele) {
        Node<E> h = head;
        Node n = new Node(null,ele,head);
        head = n;
        if(tail == null) {
            tail = n;
        } else {
            h.pre = n;
        }
        size++;
        return true;
    }

    public boolean addLast(E ele) {
        if(insertElement(ele))
            return true;
        else
            return false;
    }

    public boolean contains(E ele) {
        Node<E> cursor = head;
        while (cursor != null) {
            if(cursor.element == ele)
                return true;
            else
                cursor = cursor.next;
        }
        return false;
    }

    public boolean deleteElement(int index) {
        Node<E> node = getNode(index);
        Node<E> pre = node.pre;
        Node<E> next = node.next;

        pre.next = node.next;
        return true;
    }

    public int getSize() {
        return size;
    }



    public void print() {
        Node<E> cursor = head;
        while (cursor != null) {
            System.out.print(cursor.element + "->");
            cursor = cursor.next;
        }
    }

    public Iterator iterator() {
        return new Itr();
    }
}
