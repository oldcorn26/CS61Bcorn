package synthesizer;

import java.util.Iterator;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/6 18:49
 */
public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front


    /** Is the buffer empty?
     * @return true if it is ture.
     */
    default boolean isEmpty() {
        return this.fillCount() == 0;
    }

    /** Is the buffer full?
     * @return true if it is true.
     */
    default boolean isFull() {
        return this.fillCount() == this.capacity();
    }
}
