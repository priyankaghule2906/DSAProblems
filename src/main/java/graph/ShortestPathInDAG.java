package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathInDAG {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] shortestPath(int N, int M, int[][] edges){
        // step 1: create an adjacency list
        List<List<Edge>> adj = new ArrayList<>();
        for(int i=0;i<N;i++){
            adj.add(new ArrayList<>());
        }

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int wt = e[2];
            adj.get(u).add(new Edge(v,wt));
        }

        // perform a topological sort
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N];
        for(int i=0;i<N;i++){
            if(!visited[i]){
                topoSort(i, visited,adj,stack);
            }
        }

        // Initialize distances
        int[] distance = new int[N];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        while (!stack.isEmpty()){
            int node = stack.pop();
            if (distance[node] != Integer.MAX_VALUE) {
                for (Edge edge : adj.get(node)) {
                    int v = edge.to;
                    int wt = edge.weight;

                    if (distance[node] + wt < distance[v]) {
                        distance[v] = distance[node] + wt;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        return distance;
    }

    private static void topoSort(int node, boolean[] visited, List<List<Edge>> adj, Stack<Integer> stack){
        visited[node] = true;

        for(Edge edge : adj.get(node)){
            int v = edge.to;
            if(!visited[v]){
                topoSort(v, visited, adj, stack);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {

        int n = 26;
        int[][] edge = {{21,2,79},{2,3,11},{4,6,68},{10,16,88},{16,24,41},{7,24,91},{18,23,30},{16,14,87}};
        System.out.println(Arrays.toString(shortestPath(n, 8, edge)));

        int N2 = 6, M2 = 7;
        int[][] edges2 = {{0, 1, 2}, {0, 4, 1}, {4, 5, 4}, {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}};
        System.out.println(Arrays.toString(shortestPath(N2, M2, edges2))); // Output: [0, 2, 3, 6, 1, 5]
    }
}
