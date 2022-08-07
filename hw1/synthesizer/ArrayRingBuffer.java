package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /** Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /** Index for the next enqueue. */
    private int last;
    /** Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        fillCount++;
        if (last < capacity - 1) {
            last++;
        } else {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T x = rb[first];
        fillCount--;
        if (first < capacity - 1) {
            first++;
        } else {
            first = 0;
        }
        return x;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            return rb[first];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<T> {
        private int flag;
        private int i;

        public KeyIterator() {
            flag = fillCount;
            i = first;
        }
        @Override
        public boolean hasNext() {
            return flag != 0;
        }

        @Override
        public T next() {
            T returnItem = rb[i];
            flag--;
            if (i < capacity - 1) {
                i++;
            } else {
                i = 0;
            }
            return returnItem;
        }
    }
}
