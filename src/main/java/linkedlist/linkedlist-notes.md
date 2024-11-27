# Linked List Problems

1. Linked List Cycle (leetcode 141)
2. Reverse Linked List (leetcode 206)
3. Middle of the Linked List (leetocde 876)


1. Linked List Cycle (leetcode 141)
```text
Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.
```

```text
Brute Force approach is to use use an extra ds like list or set to check if the visited node is already present in the list, if yes its a cycle
TC O(N)
SC O(N)  
```
![brute-detect-cycle.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Fbrute-detect-cycle.png)

```java
public boolean hasCycle1(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        if(head==null)
            return false;

        ListNode temp = head;
        while(temp!=null){
            if(list.contains(temp)){
                return true;
            }
            list.add(temp);
            temp = temp.next;
        }
        return false;
    }
```

```text
Optimal approach is to use Floyd Warshals Algo of fast and slow pointer
if fast and slow pointer meets at some point then there is a cycle
TC: O(n), where n is the number of nodes in the Linked List.
SC: O(1). 
```

![tortoise-hare.png](..%2F..%2F..%2F..%2F.gitbook%2Fassets%2Ftortoise-hare.png)


```java
 public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        ListNode tortoise = head;
        ListNode hare = head;
        while(tortoise!=null && hare!=null && hare.next!=null ){
            tortoise = tortoise.next;
            hare = hare.next.next;
            if(tortoise == hare ) return true;
        }
        return false;
    }
```

2. Reverse Linked List (leetcode 206)

```text
Given the head of a singly linked list, reverse the list, and return the reversed list.
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Input: head = [1,2]
Output: [2,1]

Input: head = []
Output: []

Approach 

Brute force approach is to use a stack while iterating list, store each element of list into stack
then iterate second time while iterating replace each element of list with top element of stack

Traverse and Reverse: Move through the linked list with the temp pointer. For each node:

Save the next node in a variable called front. This ensures you don't lose track of the remaining list.
Change the next pointer of the current node (temp) to point to the previous node (prev). This action reverses the link.
Move the prev pointer to the current node (temp). This prepares prev for the next iteration.
Move the temp pointer to the next node (front). This continues the traversal.
Complete the Reversal: Continue the process until the temp pointer reaches the end of the list (NULL). At this point, the prev pointer will be at the new head of the reversed list.

Time Complexity: O(N) 
Space Complexity: O(1)
```

```java
  public ListNode reverseList(ListNode head) {
        /*Initialize 'temp' at
        head of linked list*/
        ListNode temp = head;
        
        /*Initialize pointer 'prev' to NULL,
        representing the previous node*/
        ListNode prev = null;
        
        /*Traverse the list, continue till
        'temp' reaches the end (NULL)*/
        while (temp != null) {
            /* Store the next node in
            'front' to preserve the reference*/
            ListNode front = temp.next;
            
            /*Reverse the direction of the
            current node's 'next' pointer
            to point to 'prev'*/
            temp.next = prev;
            
            /*Move 'prev' to the current
            node for the next iteration*/
            prev = temp;
            
            /*Move 'temp' to the 'front' node
            advancing the traversal*/
            temp = front;
        }
        
        /*Return the new head of
        the reversed linked list*/
        return prev;
    }

```

3. Middle of the Linked List (leetocde 876)
```text
Given the head of a singly linked list, return the middle node of the linked list.

If there are two middle nodes, return the second middle node.

Example 1:


Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.
```

```text
Brute force approach with TC O(n) and SC (1)

static Node findMiddle(Node head) {
        // If the list is empty or has
        // only one element, return the head as
        // it's the middle.
        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head;
        int count = 0;

        // Count the number of nodes
        // in the linked list.
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Calculate the position of the middle node.
        int mid = count / 2 + 1;
        temp = head;

        while (temp != null) {
            mid = mid - 1;

            // Check if the middle
            // position is reached.
            if (mid == 0){
                // break out of the loop
                // to return temp
                break;
            }
            // Move temp ahead
            temp = temp.next;
        }

        // Return the middle node.
        return temp;
    }


```

```text
The Tortoise and Hare algorithm works because the fast-moving hare reaches the end of the list in exactly the same time it takes for the slow-moving tortoise to reach the middle. When the hare reaches the end, the tortoise is guaranteed to be at the middle of the list.

Algorithm

Step 1: Initialise two pointers, `slow` and `fast`, to the head of the linked list. `slow` will advance one step at a time, while `fast` will advance two steps at a time. These pointers will move simultaneously.

Step 2: Traverse the linked list with the `slow` and `fast` pointers. While traversing, repeatedly move `slow` one step and `fast` two steps at a time.

Step 3: Continue this traversal until fast reaches the end of the list (i.e., fast or fast.next is null), the slow pointer will be at the middle of the list.
```

```java
public ListNode middleNode(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        while(hare!=null && hare.next!=null){
            tortoise = tortoise.next;
            hare = hare.next.next;
        }

        return tortoise;
    }
```