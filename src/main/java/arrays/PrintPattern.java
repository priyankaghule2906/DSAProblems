package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrintPattern {

    @Test
    public void test(){
        pattern(16);
    }

    public List<Integer> pattern(int N){
        List<Integer> result = new ArrayList<>();
        int n = N;

        // Generating the sequence while N is greater than 0
        while (N > 0) {
            result.add(N);
            N -= 5;
        }

        // Finding the upper bound of the sequence
        int upperBound = result.get(result.size() - 1) + 5;

        // Generating the sequence in increasing order until N reaches its initial value
        while (N != n) {
            result.add(N);
            N += 5;
        }

        result.add(n);

        return result;

    }
}
