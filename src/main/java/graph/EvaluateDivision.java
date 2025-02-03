package graph;

import java.util.*;

public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // create a graph
        HashMap<String, HashMap<String, Double>> graph = createGraph(equations, values);

        return null;
    }


    double dfs(HashMap<String, HashMap<String, Double>> graph, String current, String target, double value, Set<String> visited){
        return 0.0;
    }

    HashMap<String, HashMap<String, Double>> createGraph(List<List<String>> equations, double[] values){
        HashMap<String, HashMap<String, Double>> adj = new HashMap<>();
        int i=0;
        for(List<String> list: equations){
            String dividend  = list.get(0);
            String divisor  = list.get(1);
            double value = values[i++];
            adj.putIfAbsent(dividend, new HashMap<>());
            adj.putIfAbsent(divisor, new HashMap<>());
            adj.get(dividend).put(divisor, value);
            adj.get(divisor).put(dividend, 1.0/value);
        }
        return  adj;
    }

    public static void main(String[] args) {
        EvaluateDivision solver = new EvaluateDivision();

        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );
        System.out.println(Arrays.toString(solver.calcEquation(equations, values, queries)));
    }
}
