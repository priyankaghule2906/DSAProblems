package stacksqueue;

import java.util.HashMap;

class Node{
    public int key, value;
    public Node next, prev;

    Node(int key, int value){
        this.key = key;
        this.value = value;
        next = prev = null;
    }

}

public class LRUCache {

    private HashMap<Integer, Node> cache;
    private Node head;
    private Node tail;
    int capacity, size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node!=null){
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if(node == null){
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(node);

            if(cache.size() > capacity){
                Node tail = popTail();
                cache.remove(tail.key);
            }

        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }

    public void addNode(Node node){
        Node nextNode = head.next;
        head.next = node;
        nextNode.prev = node;
        node.prev = head;
        node.next = nextNode;
    }

    public void removeNode(Node node){
        Node next = node.next;
        Node prev = node.prev;

        prev.next = next;
        next.prev = prev;
    }

    public Node popTail(){
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    public static void main(String[] args) {
        // LRU Cache
        LRUCache cache = new LRUCache(2);

        // Queries
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " ");
        cache.put(3, 3);
        System.out.print(cache.get(2) + " ");
        cache.put(4, 4);
        System.out.print(cache.get(1) + " ");
        System.out.print(cache.get(3) + " ");
        System.out.print(cache.get(4) + " ");
    }
}
