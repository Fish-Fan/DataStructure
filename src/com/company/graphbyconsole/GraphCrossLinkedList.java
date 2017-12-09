package com.company.graphbyconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanfeng-mac on 2017/12/8.
 * 邻接表可轻松求出某个顶点的出度，但是求该顶点的入度就需要遍历整个表
 * 逆邻接表可轻松求出某个顶点的入度，但是求该顶点的出度就需要遍历整个表
 * 那么，十字链表就为解决该问题而来，十字链表可轻松求出某个顶点的入度和出度
 */
public class GraphCrossLinkedList {
    private static class Vertex {
        //顶点名称
        private String vName;
        //顶点的入度链表
        private Edge firstin;
        //顶点的出度链表
        private Edge firstout;

        public String toString() {
            return this.vName;
        }
    }

    private static class Edge {
        //有向图中边的尾部顶点的下标
        private int tailVex;
        //有向图中边的头部顶点的下标
        private int headVex;
        //用来存储某个顶点的所有入度边
        private Edge headLink;
        //用来存储某个顶点的所有出度边
        private Edge tailLink;

        public Edge(int tailIndex,int headIndex) {
            this.tailVex = tailIndex;
            this.headVex = headIndex;
        }
    }

    //默认顶点个数
    private final int INITIAL_SIZE = 10;
    List<Vertex> vertexArr = null;
    private int vertexNum;

    public GraphCrossLinkedList() {
        vertexArr = new ArrayList<Vertex>(INITIAL_SIZE);
    }

    public GraphCrossLinkedList(int vertexNum) {
        this.vertexNum = vertexNum;
        vertexArr = new ArrayList<Vertex>(vertexNum);
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 1;i <= vertexNum;i++) {
            Vertex vertex = new Vertex();
            System.out.println("请输入第 " + i + " 个顶点的名称");
            String vName = scanner.next();
            vertex.vName = vName;
            vertexArr.add(vertex);
        }

        createOutEdgeWithVex();
        createInEdgeWithVex();




    }

    private void createOutEdgeWithVex() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 1;i <= vertexNum;i++) {
            //当前顶点索引
            int currentVexIndex = i-1;
            Vertex currentVertex = vertexArr.get(currentVexIndex);
            System.out.println("请输入顶点 " + currentVertex.vName + " 的出度");
            int outEdgeNum = scanner.nextInt();

            Edge cursor = null;
            for(int j = 0;j < outEdgeNum;j++) {
                System.out.println("请输入该出度边的指向顶点");
                String headEdgePointVexName = scanner.next();
                if(contains(headEdgePointVexName)) {
                    int headEdgePointVexIndex = getVexIndex(headEdgePointVexName);
                    Edge edge = new Edge(currentVexIndex,headEdgePointVexIndex);


                    if(cursor != null) {
                        //连接该顶点的所有出度边
                        cursor.tailLink = edge;
                        cursor = edge;
                    } else {
                        cursor = edge;
                        currentVertex.firstout = cursor;
                    }
                } else {
                    System.out.println("输入的顶点不存在");
                }
            }

        }
    }

    private void createInEdgeWithVex() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 1;i <= vertexNum;i++) {
            //当前顶点索引
            int currentVexIndex = i-1;
            Vertex currentVertex = vertexArr.get(currentVexIndex);
            System.out.println("请输入顶点 " + currentVertex.vName + " 的入度");
            int outEdgeNum = scanner.nextInt();

            Edge cursor = null;
            for(int j = 0;j < outEdgeNum;j++) {
                System.out.println("请输入该入度边的尾部顶点");
                String tailEdgePointVexName = scanner.next();
                if(contains(tailEdgePointVexName)) {
                    int tailEdgePointVexIndex = getVexIndex(tailEdgePointVexName);
                    Vertex tailVertex = vertexArr.get(tailEdgePointVexIndex);

                    Edge edge = tailVertex.firstout;
                    Edge findCursor = edge;
                    //查找该入度边
                    while (findCursor != null) {
                        if(findCursor.headVex == currentVexIndex) {
                            if(cursor == null) {
                                currentVertex.firstin = findCursor;
                                cursor = findCursor;
                                break;
                            } else {
                                //连接该顶点所有入度边
                                cursor.headLink = findCursor;
                                cursor = findCursor;
                                break;
                            }

                        }
                        findCursor = findCursor.tailLink;
                    }
                } else {
                    System.out.println("输入的顶点不存在");
                }
            }

        }
    }

    public void getVexInEdge() {
        for(Vertex vertex : vertexArr) {
            Edge inEdge = vertex.firstin;
            Edge cursor = inEdge;

            System.out.println("顶点 " + vertex.vName + " 的所有入度顶点为: ");

            while (cursor != null) {
                System.out.print(cursor.tailVex + "->");
                cursor = cursor.headLink;
            }
            System.out.println();
        }
    }

    public void print() {
        for(int i = 0;i < vertexArr.size();i++) {
            Vertex vertex = vertexArr.get(i);
            System.out.print(vertex.vName + "->");

            Edge edge = vertex.firstout;
            Edge cursor = edge;
            while (cursor != null) {
                System.out.print(cursor.tailVex + "|" + cursor.headVex + "->");
                cursor = cursor.tailLink;
            }
            System.out.println();
        }
    }

    private boolean contains(String name) {
        for(Vertex vertex : vertexArr) {
            if(vertex.vName.equals(name))
                return true;
        }
        return false;
    }

    private int getVexIndex(String name) {
        for(int i = 0;i < vertexArr.size();i++) {
            if(vertexArr.get(i).vName.equals(name))
                return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        GraphCrossLinkedList grap = new GraphCrossLinkedList(4);
        grap.init();
        System.out.println(grap.vertexArr);
        grap.print();
        grap.getVexInEdge();
    }
}
