package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    static List<Integer> bfs(List<List<Integer>> adj, int s) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];

        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()){
            Integer node = q.poll();
            result.add(node);

            List<Integer> neighbours = adj.get(node);
            for(int num: neighbours){
                if(!visited[num]) {
                    visited[num] = true;
                    q.offer(num);
                }
            }

        }


        return result;
    }

    public static void main(String[] args) {
        int V = 5;

        List<List<Integer>> adj = new ArrayList<>(V);
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }

        addEdge(adj, 0, 1);
        addEdge(adj, 0, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 1, 4);
        addEdge(adj, 2, 4);

        List<Integer> bfs = bfs(adj, 0);
        bfs.stream().forEach(System.out::println);


    }

    static void addEdge(List<List<Integer>> list, int u, int v){
        list.get(u).add(v);
        list.get(v).add(u);
    }
}
