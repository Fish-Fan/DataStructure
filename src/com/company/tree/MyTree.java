package com.company.tree;

/**
 * Created by yanfeng-mac on 2017/11/27.
 */
public class MyTree<E> {
    private static class Node<E> {
        private E data;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(Node<E> leftChild,E data,Node<E> rightNode) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightNode;
        }
    }

    private Node<E> root;


    public MyTree() {

        Node j = new Node(null,"J",null);
        Node k = new Node(null,"K",null);
        Node i = new Node(null,"I",null);
        Node h = new Node(null,"H",k);
        Node g = new Node(null,"G",j);
        Node f = new Node(i,"F",null);
        Node e = new Node(null,"E",null);
        Node d = new Node(h,"D",null);
        Node c = new Node(f,"C",g);
        Node b = new Node(d,"B",e);
        root = new Node(b,"A",c);

    }

    public void preOrderTraverse(Node<E> node) {
        if(node == null) {
            return;
        }
        else {
            System.out.print(node.data);
            preOrderTraverse(node.leftChild);
            preOrderTraverse(node.rightChild);
        }
    }

    public void inOrderTraverse(Node<E> node) {
        if(node == null)
            return;
        else {
            inOrderTraverse(node.leftChild);
            System.out.print(node.data);
            inOrderTraverse(node.rightChild);
        }
    }

    public void postOrderTraverse(Node<E> node) {
        if(node == null) {
            return;
        } else {
            postOrderTraverse(node.leftChild);
            postOrderTraverse(node.rightChild);
            System.out.print(node.data);
        }
    }

    public static void main(String[] args) {
        MyTree tree = new MyTree();
        System.out.print("前:");

        tree.preOrderTraverse(tree.root);


        System.out.println();

        System.out.print("中:");
        tree.inOrderTraverse(tree.root);

        System.out.println();

        System.out.print("后:");
        tree.postOrderTraverse(tree.root);
    }
}
