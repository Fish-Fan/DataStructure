package com.company.graphbyconsole;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanfeng-mac on 2017/12/7.
 * 使用邻接矩阵实现有向图
 * 优点: 结构简单，易于实现，求入度和出度都很方便
 * 缺点: 对于稀疏图容易造成空间浪费
 */
public class GraphVertex {
    private static final int INFINITY = Integer.MAX_VALUE;
    //默认构造的图大小为100个顶点
    private static final int MAX_VALUE = 100;
    private List<String> vertexCollection = new ArrayList<String>();
    private int[][] edgeArr = new int[MAX_VALUE][MAX_VALUE];

    //顶点个数
    private int verNum;
    //边数
    private int edgeNum;


    public GraphVertex() {};

    public GraphVertex(int verNum,int edgeNum) {
        this.verNum = verNum;
        this.edgeNum = edgeNum;
        if(verNum < MAX_VALUE) {
            vertexCollection = new ArrayList<String>(verNum);
            edgeArr = new int[verNum][verNum];
        }

        for(int i = 0;i < verNum;i++) {
            for(int j = 0;j < verNum;j++) {
                if(i == j)
                    edgeArr[i][j] = 0;
                else
                    edgeArr[i][j] = INFINITY;
            }
        }
    }

    public void init() {
        Scanner scanner = new Scanner(System.in);
        for(int i = 0;i < verNum;i++) {
            System.out.println("请输入第 " + i + " 个顶点的名字:");
            String vex = scanner.next();
            vertexCollection.add(vex);
        }

        System.out.println("顶点输入完毕，集合为: " + vertexCollection.toString());

        //邻接矩阵初始化
        for(int i = 0;i < verNum;i++) {
            for(int j = 0;j < verNum;j++)
                edgeArr[i][j] = INFINITY;
        }

        for(int i = 1;i <= edgeNum;i++) {
            System.out.println("请输入第 " + i + " 条边的第一个顶点:");
            String a = scanner.next();
            System.out.println("请输入第 " + i + " 条边的第二个顶点:");
            String b = scanner.next();
            if(vertexCollection.contains(a) && vertexCollection.contains(b)) {
                int aIndex = vertexCollection.indexOf(a);
                int bIndex = vertexCollection.indexOf(b);
                System.out.println("请输入该边的权值:");
                int weight = scanner.nextInt();
                edgeArr[aIndex][bIndex] = weight;
            } else {
                System.out.println("您输入的顶点有误，该边输入无效");
            }
        }

    }

    public void print() {
        System.out.println(" " + vertexCollection);
        for(int i = 0;i < verNum;i++) {
            System.out.print(vertexCollection.get(i) + " ");
            for(int j = 0;j < verNum;j++) {
                if(edgeArr[i][j] == INFINITY)
                    System.out.print("∞ ");
                else
                    System.out.print(edgeArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static GraphVertex getDemo() {
        GraphVertex graphVertex = new GraphVertex(5,6);

        for(int i = 0;i < 5;i++) {
            graphVertex.vertexCollection.add("V" + i);
        }


        graphVertex.edgeArr[0][4] = 6;
        graphVertex.edgeArr[1][0] = 9;
        graphVertex.edgeArr[1][2] = 3;
        graphVertex.edgeArr[2][0] = 2;
        graphVertex.edgeArr[2][3] = 5;
        graphVertex.edgeArr[3][4] = 1;

        return graphVertex;
    }



    public static void main(String[] args) {
        //手动生成一张邻接矩阵
//        GraphVertex graphbyconsole = new GraphVertex(5,6);
//        graphbyconsole.init();
//        graphbyconsole.print();

        //快速生成一张示例邻接矩阵
        GraphVertex graph = GraphVertex.getDemo();
        graph.print();
    }


}
