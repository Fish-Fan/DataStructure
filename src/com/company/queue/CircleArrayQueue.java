package com.company.queue;

/**
 * Created by yanfeng-mac on 2017/11/24.
 */
public class CircleArrayQueue<E> {
    //队列长度
    private int size;
    //队列成员数量
    private int counts;
    private int head;
    private int tail;
    Object[] elements;
    private final int INITARRAY_SIZE = 16;

    public CircleArrayQueue() {
        elements = new Object[INITARRAY_SIZE];
        size = INITARRAY_SIZE;
        head = 0;
        tail = 0;
        counts = 0;
    }

    //初始化一个长度为size的循环队列
    public CircleArrayQueue(int size) {
        this();
        this.size = size;
        elements = new Object[size];
        for(int i = 0;i < size;i++) {
            elements[i] = new Integer(i);
        }
        counts = size;
        head = 0;
        tail = size-1;
    }

    public boolean add(E e) {
        //判断队列是否满了
        if((tail+1) % size == head) {
            return false;
        } else {
            elements[tail] = e;
            tail = (++tail) % size;
            counts++;
            return true;
        }
    }

    public Object remove() {
        //判空
        if(head == tail) {
            return null;
        } else {
            Object e = elements[head];
            elements[head] = null;
            head = (++head) % size;
            counts--;
            return e;
        }
    }

    public int getSize() {
        return counts;
    }

    public void print() {
        System.out.print("|");
        for(int i = 0;i < size;i++) {
            if(elements[i] == null)
                System.out.print("null|");
            else
                System.out.print(elements[i] + "|");
        }
    }
}
