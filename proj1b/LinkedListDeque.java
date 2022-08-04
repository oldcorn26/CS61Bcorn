/**A list program.*/
public class LinkedListDeque<T> implements Deque<T> {
    /**A constant.*/
    private Node sentinel;
    /**A constant.*/
    private Node temp_global;
    /**A constant.*/
    private int size = 0;

    /**A node class.*/
    private class Node {
        /**A constant.*/
        public T attr;
        /**A constant.*/
        public Node pre;
        /**A constant.*/
        public Node next;

        /**A node class.
         * @param item is the number
         */
        public Node(T item) {
            this.attr = item;
            this.pre = null;
            this.next = null;
        }
    }

    /** Create a new linked list.*/
    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    /** Add a new node at first one.
     * @param item is attribute of the node we add
     */
    @Override
    public void addFirst(T item) {
        Node temp;
        Node n = new Node(item);
        temp = sentinel.next;
        sentinel.next = n;
        n.pre = sentinel;
        n.next = temp;
        temp.pre = n;
        size++;
    }

    /** Add a new node at last one.
     * @param item is attribute of the node we add
     */
    @Override
    public void addLast(T item) {
        Node temp;
        Node n = new Node(item);
        temp = sentinel.pre;
        sentinel.pre = n;
        n.next = sentinel;
        n.pre = temp;
        temp.next = n;
        size++;
    }

    /** Check if it is empty.
     * Return ture if it is empty.*/
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**Return the size of list.*/
    @Override
    public int size() {
        return size;
    }

    /** Print the items in the list.*/
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.attr);
            if (i + 1 < size) {
                System.out.print(' ');
            }
            p = p.next;
        }
    }

    /** Remove the first one in the list
     * Return the item of the removed one.*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node temp;
        temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        temp.next = null;
        temp.pre = null;
        size--;
        return temp.attr;
    }

    /** Remove the last one in the list.
     * Return the item of the removed one.*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node temp;
        temp = sentinel.pre;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        temp.next = null;
        temp.pre = null;
        size--;
        return temp.attr;
    }

    /** Return the index one's item.
     * @param index is where the item we want belongs to
     * Use iteration*/
    @Override
    public T get(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        }
        Node n = sentinel.next;
        while (index > 0) {
            n = n.next;
            index--;
        }
        return n.attr;
    }

    /** Return the index one's item.
     * @param index is where the item we want belongs to
     * Use recursion*/
    public T getRecursive(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        }
        temp_global = sentinel.next;
        return getRecursiveHelper(temp_global, index);
    }

    /** The helper program of getRecursion.
     * @param head is the first one of a list
     * @param index is where the item we want belongs to
     * @return the item we want
     */
    private T getRecursiveHelper(Node head, int index) {
        if (index == 0) {
            return head.attr;
        } else {
            return getRecursiveHelper(head.next, index - 1);
        }
    }
}
