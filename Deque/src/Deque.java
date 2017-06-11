import java.util.Iterator;

public class Deque<Item> implements DequeInterface<Item>, Iterable<Item> {
    private Node head;
    private Node tail;
    private int N = 0;

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public Deque() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean isEmpty() {
        return this.N == 0;
    }

    @Override
    public int size() {
        return this.N;
    }

    @Override
    public void addFirst(Item o) {
        Node newNode = new Node();
        newNode.current = o;
        newNode.next = this.head;
        newNode.previous = null;
        this.head = newNode;
        if (this.tail == null) {
            this.tail = this.head;
        }
        this.N++;
    }

    @Override
    public void addLast(Item o) {
        Node newNode = new Node();
        newNode.current = o;
        newNode.next = null;
        newNode.previous = this.tail;
        this.tail.next = newNode;
        this.tail = newNode;
        this.N++;
    }

    @Override
    public Item removeFirst() {
        Node temp = this.head;
        this.head = this.head.next;
        temp.next = null;
        N--;
        return temp.current;
    }

    @Override
    public Item removeLast() {
        Node temp = this.tail;
        this.tail = this.tail.previous;
        temp.next = null;
        N--;
        return temp.current;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return head.next != null;
        }

        @Override
        public Item next() {
            Item item = current.current;
            current = current.next;
            return item;
        }
    }

    private class Node {
        Item current;
        Node next;
        Node previous;
    }
}
