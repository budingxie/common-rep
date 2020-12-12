package com.py.algorithm.struct;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/10
 */
public class Linked {

    public static void main(String[] args) {
        LinkedNode linkedNode = new LinkedNode();
        linkedNode.add(1);
        linkedNode.add(2);
        System.out.println(linkedNode);
    }


    static class LinkedNode {
        Node head;

        public void add(Integer value) {
            Node node = new Node();
            node.next = null;
            node.value = value;
            Node h;
            if ((h = this.head) == null) {
                head = node;
                return;
            }
            while (h.next != null) {
                h = h.next;
            }
            h.next = node;
        }

        public void addFirst(Integer value) {

        }
    }

    static class Node {

        Node next;

        Integer value;


    }


}