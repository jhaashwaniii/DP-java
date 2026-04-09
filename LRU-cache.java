package org.example.cache;
import java.util.*;
class Node{
    int key,value;
    Node prev,next;
    public Node(int key,int value)
    {
        this.key=key;
        this.value=value;
    }

}
class LRUCache{
   private int capacity;
   private Map<Integer,Node> map;
   private Node head,tail;

   public LRUCache(int capacity)
   {
    this.capacity=capacity;
    map=new HashMap<>();
    head=new Node(0,0);
    tail=new Node(0,0);
    head.next=tail;
    tail.prev=head;
   }

   //remove a Node

    public void remove(Node node)
    {
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    // Add a Node after Head
    public void add(Node node)
    {
        node.next=head.next;
        head.next.prev=node;

        head.next=node;
        node.prev=head;
    }
//    If exists:
//    Move node to head (most recent)
//    Else:
//            return -1
    public int get(int key)
    {
        if(!map.containsKey(key)) return -1;

        Node node=map.get(key);
        remove(node);
        add(node);
        return  node.value;
    }
//    If exists:
//    Update + move to head
//    Else:
//    Add new node
//    If capacity exceeded → remove tail
    public void put(int key,int value)
    {
        if(map.containsKey(key))
        {
            remove(map.get(key));
        }
        Node node=new Node(key,value);
        add(node);
        map.put(key,node);

        if(map.size()>capacity)
        {
            Node lru=tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

}

public class LRU {
    public static void main(String [] args)
    {
        System.out.println("Welcome to LRU Cache Simulator");
        LRUCache lruCache=new LRUCache(10);
        lruCache.put(2,9);
        lruCache.put(3,9);
        lruCache.put(2,8);
        System.out.println(lruCache.get(4));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
    }

}
