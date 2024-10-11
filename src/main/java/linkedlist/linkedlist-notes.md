# Linked List Problems

1. Linked List Cycle (leetcode 141)


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