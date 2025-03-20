package recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        System.out.println("Starting backtracking with n = " + n);
        gp(result, "", 0, 0, n);
        return result;
    }

    private static void backtrack(List<String> result, String current, int open, int close, int max, int depth) {
        String indent = "  ".repeat(depth); // Indentation for readability
        System.out.println(indent + "CALL: current=\"" + current + "\", open=" + open + ", close=" + close);

        // Base case: If the current string length equals 2*n, we've used all parentheses
        if (current.length() == max * 2) {
            System.out.println(indent + "→ FOUND VALID COMBINATION: \"" + current + "\"");
            result.add(current);
            return;
        }

        // We can add an opening parenthesis if we haven't used all n
        if (open < max) {
            System.out.println(indent + "→ Adding '(': open < max");
            backtrack(result, current + "(", open + 1, close, max, depth + 1);
        } else {
            System.out.println(indent + "→ Cannot add '(': open = max = " + max);
        }

        // We can add a closing parenthesis if there are opening parentheses to match
        if (close < open) {
            System.out.println(indent + "→ Adding ')': close < open");
            backtrack(result, current + ")", open, close + 1, max, depth + 1);
        } else {
            System.out.println(indent + "→ Cannot add ')': close = open = " + open);
        }

        System.out.println(indent + "RETURN from current=\"" + current + "\"");
    }

    public static void main(String[] args) {
        System.out.println("===== GENERATING PARENTHESES FOR n = 1 =====");
//        List<String> result1 = generateParenthesis(1);
//        System.out.println("Result for n=1: " + result1);
//
//        System.out.println("\n===== GENERATING PARENTHESES FOR n = 2 =====");
//        List<String> result2 = generateParenthesis(2);
//        System.out.println("Result for n=2: " + result2);

        System.out.println("\n===== GENERATING PARENTHESES FOR n = 3 =====");
        List<String> result3 = generateParenthesis(3);
        System.out.println("Result for n=3: " + result3);
    }

    static void gp(List<String> current, String pattern, int open, int close, int max){
        if(open == max && close==max){
            current.add(pattern);
            return;
        }

        if(open < max){
            gp(current, pattern + "(", open+1, close, max);
        }
        if(close<open){
            gp(current, pattern + ")", open, close+1, max);
        }
    }
}