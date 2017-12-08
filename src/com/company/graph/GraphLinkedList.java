package com.company.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by yanfeng-mac on 2017/12/7.
 * 使用邻接表来实现无向图
 * 优点： 对于稀疏图不会造成空间浪费问题
 * 缺点： 求某个顶点的入度和出度不太方便，需要建立邻接表和逆邻接表才可以轻松求出
 * 只需找出某个顶点，然后遍历该顶点的链表即可
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

    public static GraphLinkedList getDemo() {
        GraphLinkedList graph = new GraphLinkedList(4);

        Vertex v3 = new Vertex("V3",null);
        Vertex v2 = new Vertex("V2",null);
        Vertex v1 = new Vertex("V1",null);
        Vertex v0 = new Vertex("V0",null);

        //next指向的顶点必须是创建出来的对象，不能直接指向任何已存在的顶点，否则next指针会发生缠绕，造成循环链表
        v0.next = new Vertex("V1",null);
        v0.next.next = new Vertex("V2",null);
        v0.next.next.next = new Vertex("V3",null);
        v1.next = new Vertex("V0",null);
        v1.next.next = new Vertex("V2",null);
        v2.next = new Vertex("V0",null);
        v2.next.next = new Vertex("V1",null);
        v2.next.next.next = new Vertex("V3",null);
        v3.next = new Vertex("V0",null);
        v3.next.next = new Vertex("V2",null);

        graph.vertexArr[0] = v0;
        graph.vertexArr[1] = v1;
        graph.vertexArr[2] = v2;
        graph.vertexArr[3] = v3;
        return graph;
    }

    private boolean contains(String name) {
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

            while (cursor != null) {
                System.out.print(cursor.vertexName + "->");
                cursor = cursor.next;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
//        GraphLinkedList graph = new GraphLinkedList(4);
//        graph.init();
//        graph.print();

        GraphLinkedList graph = GraphLinkedList.getDemo();
        graph.print();
    }

}
