* insert interval
* merge interval
* Non Overlapping interval

* insert interval
```text
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

Note that you don't need to modify intervals in-place. You can make a new array and return it.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

1. Initialization: We initialize an empty ArrayList to store the merged intervals and a pointer i to traverse through the intervals.

2. Add Intervals Before New Interval: We first add all intervals that end before the start of the new interval.

3. Merge Overlapping Intervals: We then merge all intervals that overlap with the new interval. This is done by updating the start and end of the new interval to the minimum start and maximum end of the overlapping intervals, respectively.

4. Add Remaining Intervals: Finally, we add all remaining intervals that start after the new interval ends.

5. Return Result: We convert the result list back to a 2D array and return it.

This approach ensures that the intervals remain sorted and non-overlapping.
```

```java
// TC O(n)
// SC O(n)
  public int[][] insert(int[][] intervals, int[] newInterval) {
       
        ArrayList<int[]> result = new ArrayList<>();
        int i=0;
        int n = intervals.length;

        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }  

        while(i<n && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);

        while(i<n){
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
        

    }
```

* merge interval
```text
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 
```

```text
1. Sort Intervals: First, we sort the intervals based on their starting times. This helps in easily finding overlapping intervals.

2. Initialize: We initialize a list to store the merged intervals. We also initialize currentInterval to the first interval in the sorted list and add it to the merged list.

3. Iterate and Merge: For each interval, we check if it overlaps with the currentInterval:

3.a If it does overlap (currentEnd >= nextStart), we merge it by updating the end of currentInterval to the maximum end time.
3.b If it doesn't overlap, we set currentInterval to the new interval and add it to the merged list.
4. Return Result: Finally, we convert the list of merged intervals back to a 2D array and return it.

TC O(n log n)
SC O(n)
```

```java
 public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by their start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) {
                // Merge intervals
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // Add the new interval
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

```


* Non Overlapping interval
```text
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 
 To solve the problem of finding the minimum number of intervals to remove in order to make the rest of the intervals non-overlapping, you can follow a greedy algorithm approach. The key idea is to always keep the interval that ends the earliest to maximize the number of non-overlapping intervals.

Here is the step-by-step solution and the implementation in Java:

Step-by-Step Solution:
* Sort the intervals by their end times: This helps in always considering the interval that ends the earliest, thus leaving more room for subsequent intervals.
* Iterate through the sorted intervals and keep track of the end of the last non-overlapping interval: If the current interval overlaps with the last non-overlapping interval, increase the count of intervals to be removed.
* Update the end of the last non-overlapping interval: If there is no overlap, update the end to the end of the current interval.

TC : n log n
```

```java
public int eraseOverlapIntervals(int[][] intervals) {
        int count=0;
        // sort array on the basis of end time
        Arrays.sort(intervals, (a,b) -> a[1]- b[1]);
        // if the start time of next is less than end time of previous then its overlapping
        int prevEnd = intervals[0][1];

        for(int i=1;i<intervals.length;i++){
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if(nextStart < prevEnd){
                count++;
            } else {
            prevEnd = nextEnd;
            }
        }

        return count;
    }
```


# 452. Minimum Number of Arrows to Burst Balloons

## Problem Statement

### Context
You are presented with a wall representing the XY-plane, where balloons are taped with their horizontal diameters. Your mission is to burst all balloons using the minimum number of vertical arrows.

### Key Constraints
- Balloons are represented as `[xstart, xend]`
- Arrows can be shot vertically from any x-coordinate
- An arrow bursts a balloon if it passes through its horizontal range
- No limit on the number of arrows

## Problem Visualization

### Balloon Representation
```
Wall (XY-Plane)

x-axis:  1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16
         |----|----|----|----|----|----|----|----|----|----|----|----|----|----|
Balloon1:    [-------------------]
Balloon2:          [-------------------]
Balloon3:                [-------------------]
```

## Intuitive Approach

### Core Strategy: Greedy Arrow Placement
1. Sort balloons by their end points
2. Place arrows at the earliest possible point
3. Maximize the coverage of each arrow
4. Minimize total number of arrows

## Algorithm Walkthrough

### Step-by-Step Process
1. **Sort Balloons**: Order by end points (ascending)
2. **Initialize First Arrow**:
    - Place at the end of the first balloon
    - This becomes our initial coverage point
3. **Iterate Through Balloons**:
    - If next balloon starts after current arrow, shoot a new arrow
    - Update arrow position to the new balloon's end point

## Mathematical Model

### Formal Representation
- Let `B = {b1, b2, ..., bn}` be the set of balloons
- Let `A = {a1, a2, ..., ak}` be the set of arrows
- Objective: Minimize `|A|`
- Constraint: ∀b ∈ B, ∃a ∈ A such that a ∈ b

## Java Implementation

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        // Handle edge cases
        if (points == null || points.length == 0) return 0;
        
        // Sort balloons by end points
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int arrows = 1;  // Minimum one arrow
        int arrowPos = points[0][1];  // First arrow at first balloon's end
        
        for (int i = 1; i < points.length; i++) {
            // If current balloon starts after previous arrow, need new arrow
            if (points[i][0] > arrowPos) {
                arrows++;
                arrowPos = points[i][1];
            }
        }
        
        return arrows;
    }
}
```

## Complexity Analysis

### Time Complexity
- Sorting: O(n log n)
- Single Pass: O(n)
- **Overall**: O(n log n)

### Space Complexity
- In-place sorting
- Constant extra space
- **Overall**: O(1)

## Example Scenarios

### Scenario 1: Overlapping Balloons
**Input**: `[[10,16],[2,8],[1,6],[7,12]]`
**Output**: 2 Arrows
**Explanation**:
- First Arrow (x=6): Bursts [1,6] and [2,8]
- Second Arrow (x=11): Bursts [7,12] and [10,16]

### Scenario 2: No Overlap
**Input**: `[[1,2],[3,4],[5,6],[7,8]]`
**Output**: 4 Arrows
**Explanation**: Each balloon requires a separate arrow

## Problem-Solving Strategies

### Mental Models
1. **Box Packing Analogy**
    - Imagine arranging boxes with minimal separators
    - Each separators covers maximum possible boxes

2. **Resource Allocation**
    - Arrows are limited resources
    - Goal: Maximize coverage with minimum allocation

## Common Pitfalls

1. **Sorting Overlooked**
    - Sorting is crucial for greedy approach
    - Determines arrow placement efficiency

2. **Boundary Condition Mistakes**
    - Careful handling of start and end points
    - Consider edge cases in implementation


