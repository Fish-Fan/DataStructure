package com.company;

import com.company.list.MyLinkedList;
import com.company.queue.MyLinkedQueue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyLinkedList list = new MyLinkedList();



        list.insertElement(10);

        list.insertElement(100);
        list.insertElement(20,1);
        list.print();
        Iterator iterator = list.iterator();
        System.out.println("head" + list.head);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


//        MyStack stack = new MyStack(10);
//        stack.pop();
//        stack.pop();
//        stack.print();

//        CircleArrayQueue queue = new CircleArrayQueue(10);
//        Integer i = (Integer) queue.remove();
//        System.out.println("first remove->" + i);
//        System.out.println("second remove->" + queue.remove());
//        queue.print();

//        MyLinkedQueue queue = new MyLinkedQueue(10);
//        queue.add(new Integer(10));
//        System.out.println("remove->" + queue.remove() + queue.remove());
//        queue.print();


    }
}
