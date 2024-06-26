package sec07;

public class Queue {
    private Node head;

    private Node tail;

    public synchronized void add(Object newValue) {
        var n = new Node();
        if (head == null) head = n;
        else tail.next = n;
        tail = n;
        tail.value = newValue;
        // 重新激活等待集合中的所有线程
        notifyAll();
    }

    public synchronized Object remove() {
        if (head == null) return null;
        Node n = head;
        head = n.next;
        return n.value;
    }

    public synchronized Object take() throws InterruptedException {
        // 进入等待集合
        while (head == null) wait();
        Node n = head;
        head = n.next;
        return n.value;
    }

    class Node {
        Object value;
        Node next;
    }
}