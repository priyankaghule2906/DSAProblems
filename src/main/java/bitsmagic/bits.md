
# Minimum Flips to Make a OR b Equal to c

### Problem Explanation
The task is to determine the minimum number of bit flips required in some bits of `a` and `b` to make the bitwise OR of `a` and `b` equal to `c`. The flip operation consists of changing any single bit from `1` to `0` or from `0` to `1` in the binary representation of `a` or `b`.

### Approach to Solve the Problem

1. **Bitwise OR Operation:**
   The bitwise OR operation produces `1` if at least one of the bits in `a` or `b` is `1`. Our goal is to make sure that the result of `a | b` matches `c`.

2. **Bit Analysis:**
    - If `c` has a `1` at a bit position:
        - If both `a` and `b` have `0`, we need to flip at least one of them to `1`.
        - If either `a` or `b` has `1`, no flip is needed at that position.
    - If `c` has a `0` at a bit position:
        - If both `a` and `b` have `1`, both bits need to be flipped to `0`.
        - If either `a` or `b` has `1`, flip that `1` to `0`.

3. **Time Complexity:**
   The time complexity of this approach is **O(1)** because we are only iterating through a fixed number of bit positions (up to 30 bits for numbers within the range `1 <= a, b, c <= 10^9`).

---

### Code Implementation

```java
public class Solution {
    public int minFlips(int a, int b, int c) {
        int flips = 0;
        
        // Iterate through all bit positions (0 to 30, since 10^9 has 30 bits)
        for (int i = 0; i < 31; i++) {
            // Extract the i-th bit of a, b, and c using bitwise AND and right shift
            int aBit = (a >> i) & 1;
            int bBit = (b >> i) & 1;
            int cBit = (c >> i) & 1;
            
            if (cBit == 0) {
                // If c's bit is 0, both a's and b's bits must be 0.
                if (aBit == 1 && bBit == 1) {
                    flips += 2; // Flip both bits to 0
                } else if (aBit == 1 || bBit == 1) {
                    flips += 1; // Flip the bit to 0
                }
            } else {
                // If c's bit is 1, we need at least one of a's or b's bits to be 1
                if (aBit == 0 && bBit == 0) {
                    flips += 1; // Flip one bit to 1
                }
            }
        }
        
        return flips;
    }
}
```

---

### Detailed Explanation of Key Lines

The line `int aBit = (a >> i) & 1;` extracts the `i`-th bit from the integer `a`. Here's how it works:

1. **Right Shift (`>>`):**
    - `(a >> i)` shifts the bits of `a` to the right by `i` positions. This places the bit at position `i` in the least significant bit (rightmost) position, and all other bits are shifted out.

2. **Bitwise AND (`&`):**
    - The expression `& 1` masks all bits except for the least significant bit. This means that only the bit at position `i` is preserved, and the rest are discarded.

### Why Iterate from 0 to 30?

The reason for iterating from 0 to 30 is based on the number range constraints: `1 <= a, b, c <= 10^9`. Numbers up to `10^9` fit within 30 bits.

- **Why 30 bits?**
    - The binary representation of `10^9` is `111011100110101100101000000000`, which is 30 bits long.
    - To cover all possible bit positions in `a`, `b`, and `c` up to `10^9`, we iterate through the 30 bit positions.

By iterating from bit position 0 to 30, we ensure that we analyze all the relevant bits for the numbers in the given range.

---

### Example Walkthrough

#### Example 1:
- `a = 2`, `b = 6`, `c = 5`
    - Binary: `a = 010`, `b = 110`, `c = 101`
    - Flip bits:
        - Bit position 0: `a` has 0, `b` has 0, `c` has 1 → flip one bit (either `a` or `b`)
        - Bit position 1: `a` has 1, `b` has 1, `c` has 0 → flip both `a` and `b`
        - Bit position 2: `a` has 0, `b` has 1, `c` has 1 → no flip needed
    - Total flips: 3

#### Example 2:
- `a = 4`, `b = 2`, `c = 7`
    - Binary: `a = 100`, `b = 010`, `c = 111`
    - Flip bits:
        - Bit position 0: `a` has 0, `b` has 0, `c` has 1 → flip one bit (either `a` or `b`)
        - Bit position 1: `a` has 0, `b` has 1, `c` has 1 → no flip needed
        - Bit position 2: `a` has 1, `b` has 0, `c` has 1 → no flip needed
    - Total flips: 1

#### Example 3:
- `a = 1`, `b = 2`, `c = 3`
    - Binary: `a = 001`, `b = 010`, `c = 011`
    - Flip bits:
        - Bit position 0: `a` has 1, `b` has 0, `c` has 1 → no flip needed
        - Bit position 1: `a` has 0, `b` has 1, `c` has 1 → no flip needed
        - Bit position 2: `a` has 0, `b` has 0, `c` has 0 → no flip needed
    - Total flips: 0
