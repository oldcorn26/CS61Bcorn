/** The interface.*/
public interface Deque<T> {
    /** Add item as the first one.*/
    void addFirst(T item);
    /** Add item as the last one.*/
    void addLast(T item);
    /** Return true if the list is empty.*/
    boolean isEmpty();
    /** Return the size.*/
    int size();
    /** Print the list.*/
    void printDeque();
    /** Return and remove the first one.*/
    T removeFirst();
    /** Return and remove the last one.*/
    T removeLast();
    /** Return the index one.*/
    T get(int index);
}
