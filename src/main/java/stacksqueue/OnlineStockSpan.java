package stacksqueue;

import java.util.Stack;

public class OnlineStockSpan {

    public static void main(String[] args) {
        OnlineStockSpan OnlineStockSpan = new OnlineStockSpan();
        System.out.println(OnlineStockSpan.next(100)); // return 1
        System.out.println(OnlineStockSpan.next(80));  // return 1
        System.out.println(OnlineStockSpan.next(60));  // return 1
        System.out.println(OnlineStockSpan.next(70));  // return 2
        System.out.println(OnlineStockSpan.next(60));  // return 1
        System.out.println(OnlineStockSpan.next(75));  // return 4
        System.out.println(OnlineStockSpan.next(85));  // return 6
    }

    static class Pair {
        int price;
        int span;

        public Pair(int price, int span){
            this.price = price;
            this.span = span;
        }
    }

    private Stack<Pair> stack = null;

    public OnlineStockSpan() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span =1;
        // check last added value in the stack is its less than or equal to this values

        while(!stack.isEmpty() && stack.peek().price <= price) {
            Pair pair = stack.pop();
            span+=pair.span;
        }

        stack.push(new Pair(price, span));
        return span;
    }
}
