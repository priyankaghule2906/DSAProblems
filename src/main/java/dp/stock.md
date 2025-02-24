# Best Time to Buy and Sell Stock with Transaction Fee

## Problem Statement
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`-th day, and an integer `fee` representing a transaction fee.
Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

### Notes:
- You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
- The transaction fee is only charged once for each stock purchase and sale.

### Example 1:
**Input:**
```java
prices = [1,3,2,8,4,9], fee = 2
```
**Output:**
```java
8
```
**Explanation:**
- Buy at `prices[0] = 1`, sell at `prices[3] = 8` → Profit: `(8 - 1 - 2) = 5`
- Buy at `prices[4] = 4`, sell at `prices[5] = 9` → Profit: `(9 - 4 - 2) = 3`
- Total profit = `5 + 3 = 8`

### Example 2:
**Input:**
```java
prices = [1,3,7,5,10,3], fee = 3
```
**Output:**
```java
6
```

---

## Intuition
This is a variation of the "Best Time to Buy and Sell Stock" series but with a **transaction fee**.
We maintain two states:
1. **Hold:** Maximum profit if we currently hold a stock.
2. **No Hold:** Maximum profit if we do not hold any stock.

At each step, we update:
- `noHold`: The max profit when not holding stock (either do nothing or sell the stock held previously).
- `hold`: The max profit when holding stock (either do nothing or buy stock using `noHold`).

---

## Java Solution
```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int hold = -prices[0]; // Buying at day 0
        int noHold = 0; // No stock initially
        int n = prices.length;

        for (int i = 1; i < n; i++) {
            noHold = Math.max(noHold, hold + prices[i] - fee); // Max profit after selling
            hold = Math.max(hold, noHold - prices[i]); // Max profit after buying
        }

        return noHold;
    }
}
```

### **Time & Space Complexity**
- **Time Complexity:** `O(n)`, since we iterate through the `prices` array once.
- **Space Complexity:** `O(1)`, since we use only two variables (`hold` and `noHold`).

---

## Dry Run with Multiple Examples

### Example 1
**Input:** `prices = [1,3,2,8,4,9], fee = 2`

| Day | Price | noHold | hold |
|----|------|--------|------|
| 0  | 1    | 0      | -1   |
| 1  | 3    | 0      | -1   |
| 2  | 2    | 0      | -1   |
| 3  | 8    | 5      | -1   |
| 4  | 4    | 5      | 1    |
| 5  | 9    | 8      | 1    |

**Output:** `8`

### Example 2
**Input:** `prices = [1,3,7,5,10,3], fee = 3`

| Day | Price | noHold | hold |
|----|------|--------|------|
| 0  | 1    | 0      | -1   |
| 1  | 3    | 0      | -1   |
| 2  | 7    | 3      | -1   |
| 3  | 5    | 3      | -1   |
| 4  | 10   | 6      | -1   |
| 5  | 3    | 6      | 3    |

**Output:** `6`

---

## Key Takeaways
- Use **Dynamic Programming** with two states (`hold` and `noHold`).
- Iterate through the array in **O(n)** time.
- Optimize space to **O(1)** by keeping track of only two variables.
- Ensure **transactions include the fee deduction** at the right step.

This is an efficient and scalable approach for solving the problem 🚀

