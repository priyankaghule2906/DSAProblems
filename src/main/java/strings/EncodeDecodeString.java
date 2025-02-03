package strings;

import org.junit.Test;

import java.util.Stack;

public class EncodeDecodeString {

    @Test
    public void test() {
        System.out.println(decodeString("3[a2[c]]"));
    }


    public String decodeString(String s) {
        // We'll use two stacks:
        // One for keeping track of numbers (counts)
        Stack<Integer> countStack = new Stack<>();
        // One for keeping track of partial results
        Stack<StringBuilder> stringStack = new Stack<>();

        // Current running string
        StringBuilder currentString = new StringBuilder();
        // Current number being processed
        int currentCount = 0;

        // Process each character in the string
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Build number from digits
                currentCount = currentCount * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push the current count and string to their respective stacks
                countStack.push(currentCount);
                stringStack.push(currentString);
                // Reset for next segment
                currentCount = 0;
                currentString = new StringBuilder();
            } else if (ch == ']') {
                // Time to process a complete bracket pair
                StringBuilder temp = stringStack.pop();
                int repeatTimes = countStack.pop();
                // Repeat the current string the required number of times
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentString);
                }
                currentString = temp;
            } else {
                // Regular character, just append to current string
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}

