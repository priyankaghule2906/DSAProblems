package graph;

import java.util.*;

public class EventualSafeNodes {

    public static int[] eventualSafeNodes(int V, int[][] adj) {
        ArrayList<Integer>[] reverse = new ArrayList[V];
        for (int i = 0; i < V; i++)
            reverse[i] = new ArrayList<>();

        for(int i=0;i<V;i++){
            for(int node : adj[i]){
                reverse[node].add(i);
            }
        }

        int[] result = topoSort(V, reverse);
        Arrays.sort(result);
        return result;

    }

    static int[] topoSort(int V, List<Integer>[] adj){
        // count indegree
        int[] indegree = new int[V];
        for(int i=0;i<V;i++){
            for(int node: adj[i]){
                indegree[node]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<V;i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int[] result = new int[V];
        int index =0;
        while(!q.isEmpty()){
            int node = q.poll();
            result[index++] = node;

            for(int neighbor : adj[node]){
                indegree[neighbor]--;
                if(indegree[neighbor]==0){
                    q.offer(neighbor);
                }
            }

        }

        return Arrays.copyOfRange(result, 0, index);

    }

    public static void main(String[] args) {
        int V = 7;
        int[][] adj = {
                {0, 1},
                {0, 2},
                {1,2},
                {1,3},
                {2,5},
                {3,0},
                {4,5 }
        };

        int[] ans = eventualSafeNodes(V, adj);

        // Output
        System.out.println("The eventually safe nodes in the graph are:");
        for (int i : ans) {
            System.out.print(i + " ");
        }


    }
}
