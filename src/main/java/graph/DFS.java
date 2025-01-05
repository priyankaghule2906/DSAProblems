package graph;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        int[][] edges = {
                {1, 2}, {1, 0}, {2, 0}, {2, 3}, {2, 4}
        };

        // Populate the adjacency list with edges
        for (int[] e : edges) {
            addEdge(adj, e[0], e[1]);
        }

        int source = 1;
        System.out.println("DFS from source: " + source);
        List<Integer> dfs = DFS(adj, source);
        dfs.stream().forEach(System.out::println);
    }

    static void DFSRec(List<List<Integer>> adj, boolean[] visited, int s, List<Integer> result) {
        visited[s] = true;
        result.add(s);
        for(int n : adj.get(s)){
            if(!visited[n]){
                visited[n] = true;
                DFSRec(adj, visited, n, result);
            }
        }
    }

    static List<Integer> DFS(List<List<Integer> > adj, int s) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        // Call the recursive DFS function
        DFSRec(adj, visited, s, result);
        return result;
    }


    static void addEdge(List<List<Integer>> adj,
                        int s, int t) {
        // Add edge from vertex s to t
        adj.get(s).add(t);
        // Due to undirected Graph
        adj.get(t).add(s);
    }
}
