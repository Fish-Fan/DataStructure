package com.company.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanfeng-mac on 2017/12/9.
 * 上个程序(graphbyconsole.graphCrossLinkedList)中的十字链表是基于控制台的，
 * 这个直接提供顶点数组和边集合就可以初始化出来一张图，相比控制台来说更方便了些，但是
 * 就代码量和复杂程度来说还是控制台的多些，相当于出力不讨好了...
 */
public class GraphCrossLinkedList {
    /**
     * vName-->顶点名称
     * firstin-->顶点的入度链表的头结点
     * firstout-->顶点的出度链表的头结点
     */
    private static class Vertex {
        private String vName;
        private Edge firstin;
        private Edge firstout;

        public String toString() {
            return this.vName;
        }

        public Vertex() {}
        public Vertex(String name) {
            this.vName = name;
        }
    }

    /**
     * tailVex-->有向图中边的尾部顶点的下标
     * headVex-->有向图中边的头部顶点的下标
     * headLink-->用来存储某个顶点的所有入度边
     * tailLink-->用来存储某个顶点的所有出度边
     */
    private static class Edge {
        private int tailVex;
        private int headVex;
        private Edge headLink;
        private Edge tailLink;

        public Edge(int tailIndex,int headIndex) {
            this.tailVex = tailIndex;
            this.headVex = headIndex;
        }
    }

    //方便用户初始化图
    private static class GraphEdge {

        private String tail;
        private String head;

        public GraphEdge(String tailName,String headName) {
            this.head = headName;
            this.tail = tailName;
        }
    }


    private List<Vertex> vertexArr;

    public void init(String[] vexs,List<GraphEdge> graphEdges) {
        initVertexArr(vexs);
        initGraphEdge(graphEdges);
    }

    //初始化顶点集合
    private void initVertexArr(String[] vexs) {
        vertexArr = new ArrayList<Vertex>(vexs.length);
        for(int i = 0;i < vexs.length;i++) {
            Vertex vertex = new Vertex(vexs[i]);
            vertexArr.add(vertex);
        }
    }

    //初始化边集合
    private void initGraphEdge(List<GraphEdge> graphEdges) {
        for(int i = 0;i < graphEdges.size();i++) {
            GraphEdge edge = graphEdges.get(i);
            String headName = edge.head;
            String tailName = edge.tail;
            if(contains(headName) && contains(tailName)) {
                int tailIndex = getVexIndex(tailName);
                int headIndex = getVexIndex(headName);
                Vertex headVertex = vertexArr.get(headIndex);
                Vertex tailVertex = vertexArr.get(tailIndex);
                Edge edgeInit = new Edge(tailIndex,headIndex);
                insertVexEdge(headVertex,tailVertex,edgeInit);
            }

        }
    }

    //插入顶点的边
    private void insertVexEdge(Vertex headV,Vertex tailV,Edge edge) {

        //头插法将出度边插入至顶点中
        if(tailV.firstout == null) {
            tailV.firstout = edge;
        } else {
            Edge tailVNextEdge = tailV.firstout;
            edge.tailLink = tailVNextEdge;
            tailV.firstout = edge;
        }
        //头插法将入度边插入至顶点中
        if(headV.firstin == null) {
            headV.firstin = edge;
        } else {
            Edge headVNextEdge = headV.firstin;
            edge.headLink = headVNextEdge;
            headV.firstin = edge;
        }
    }

    private boolean contains(String name) {
        for(Vertex vertex : vertexArr) {
            if(vertex.vName.equals(name))
                return true;
        }
        return false;
    }

    //获取顶点的下标
    private int getVexIndex(String name) {
        for(int i = 0;i < vertexArr.size();i++) {
            if(vertexArr.get(i).vName.equals(name))
                return i;
        }
        return -1;
    }

    //获取各个顶点的出度顶点
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

    //获取各个顶点的入度顶点
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

    public static void main(String[] args) {
        GraphCrossLinkedList graph = new GraphCrossLinkedList();
        String[] vexs = {"V0","V1","V2","V3"};
        List<GraphEdge> edgeList = new ArrayList<GraphEdge>();
        GraphEdge graphEdge = new GraphEdge("V0","V3");
        GraphEdge graphEdge1 = new GraphEdge("V1","V0");
        GraphEdge graphEdge2 = new GraphEdge("V2","V0");
        GraphEdge graphEdge3 = new GraphEdge("V1","V2");
        GraphEdge graphEdge4 = new GraphEdge("V2","V1");

        edgeList.add(graphEdge);
        edgeList.add(graphEdge1);
        edgeList.add(graphEdge2);
        edgeList.add(graphEdge3);
        edgeList.add(graphEdge4);

        graph.init(vexs,edgeList);
        graph.print();
        graph.getVexInEdge();
    }

}
