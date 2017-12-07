package com.company.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yanfeng-mac on 2017/12/7.
 * 使用邻接表来实现无向图
 */
public class GraphLinkedList {
    private static class Vertex {
        private String vertexName;
        private Vertex next;

        public Vertex() {};

        public Vertex(String name,Vertex next) {
            vertexName = name;
            this.next = next;
        }

        public String toString() {
            return this.vertexName;
        }

    }

    private int vertexNum;
    private Vertex[] vertexArr = {};

    public GraphLinkedList(int vertexNum) {
        this.vertexNum = vertexNum;
        vertexArr = new Vertex[vertexNum];
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 1;i <= vertexNum;i++) {
            Vertex vertex = new Vertex();
            System.out.println("请输入第" + i + "个顶点的名称: ");
            String vName = scanner.next();
            vertex.vertexName = vName;

            vertexArr[i-1] = vertex;
        }

        System.out.println("顶点集合输入完毕，集合为 " + Arrays.toString(vertexArr));

        for(int i = 0;i < vertexNum;i++) {
            Vertex vertex = vertexArr[i];
            Vertex cursor = vertex;
            System.out.println("请输入顶点 " + vertex.vertexName + " 的所有连接顶点的个数: ");
            int linkedVertexNum = scanner.nextInt();

            for(int j = 1;j <= linkedVertexNum;j++) {
                System.out.println("请输入顶点 " + vertex.vertexName + " 的第 " + j + " 个连接顶点");
                String linkedVertexName = scanner.next();
                if(contains(linkedVertexName)) {
                    Vertex linkedVertex = new Vertex(linkedVertexName,null);
                    cursor.next = linkedVertex;
                    cursor = linkedVertex;
                }
            }

        }


    }

    public boolean contains(String name) {
        for(int i = 0;i < vertexArr.length;i++) {
            if(vertexArr[i].vertexName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        for(int i = 0;i < vertexNum;i++) {
            Vertex currentVertex = vertexArr[i];
            Vertex cursor = currentVertex;

//            int flag = 1;
//            while (cursor.next != null) {
//                System.out.print(cursor.vertexName + "->");
//                cursor = cursor.next;
//            }

            while (true) {
                System.out.print(cursor.vertexName + "->");
                cursor = cursor.next;

                if(cursor.next == null) {
                    System.out.print(cursor.vertexName + "->");
                    cursor = cursor.next;
                    break;
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphLinkedList graph = new GraphLinkedList(4);
        graph.init();
        graph.print();
    }

}
