//package org.coding.practice.uber;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Problem Statement
//Uber HCVs (High Capacity Vehicles) runs across the city of UberLand. UberLand has N stops where the HCVs pickup or drop the passengers. There Is a network of M bidirectional roads tnat connects various stops. Each road has a particular length attached to it.
//
//The Uber developers have Introduced a weird feature of credits where each stop has some assigned credits c[i] (0<=i<N) which the HCV can collect when it reaches the stop. These credits are used to travel on a road and number of credits used for travelling on a road is equal to the length of the road. So a HCV can travel on a road If it has credits equal or more than the length of the road.
//
//You want to travel from a given source stop S (0<=S<N) to a destination stop D (0<=0<N) with minimum distance travelled.
//
//Calculate the minimum distance that you need to travel or return -1 if there is no way possible.
//Assume the HCV starts at the source stop S and has credits equal to the credits of S at the start.
//
//        Input:
//
//Each test case has several lines.
//
//The first line contains an integer N which represent the number of stops in UberLand.
//The second line contains an array C of N Integers which represents credits for each stop.
//The third line contains an integer M which represent the number of roads in UberLand.
//Then the following M lines each contain three Integers X (0<=X<N) ,Y (0<=-Y<N) ,Z (1<=Z<=100) which means there is road connecting stops X and Y with length Z where X != Y
//Then the last 2 lines contains one Integer respectively, S and D which represent the source and the destination stop.
//        Constraints:
//        1<=N<=50
//        0<=M<=100
//        0<=C[i]<=100
//        1<=Length of each road<=100
//
// Sample Input:
//
// 5
// 2 10 5 100 3
// 8
// 0 1 2
// 0 3 5
// 0 2 1
// 1 3 10
// 1 4 15
// 2 3 7
// 2 4 90
// 3 4 80
// 0
// 4
//
// Sample Output:
// 19
// **/
//class Stop{
//    int dest;
//    int dist;
//    int credit;
//
//    Stop(int d, int dist, int c)
//    {
//        this.dest =d;
//        this.dist = dist;
//        this.credit = c;
//    }
//}
//
//public class HCV {
//
//    public static void main(String[] args)
//    {
//        int[] C = new int[] {2, 10, 5, 100, 3};
//        int[][] roads = new int[][]{
//                {0 ,1 ,2},
//                {0, 3, 5},
//                {0 ,2, 1},
//                {1 ,3, 10},
//                {1 ,4, 15},
//                {2 ,3, 7},
//                {2 ,4, 90},
//                {3 ,4, 80}
//        };
//        int ans = minDistance(C, roads);
//    }
//
//    public int minDistance(int[] C, int[][] roads)
//    {
//        return -1;
//    }
//
//    public List<List<int[]>> createGraph(int[][] roads, int n)
//    {
//        List<List<int[]>> graph = new ArrayList<>(n);
//        for(int[] road : roads)
//        {
//            graph.get(road[0]).add(new int[]{road[1], road[2]});
//            graph.get(road[1]).add(new int[]{road[0], road[2]});
//        }
//    }
//}
//
