public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int first;
    private int last;
    private int max;

    /** Creates an empty list.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        max = 8;
        size = 0;
        first = 0;
        last = 7;
    }

    /** Resize the list.
     * @param num is the size of the new list.
     */
    private void resize(int num) {
        if (num <= 8) {
            return;
        }
        T[] newArray = (T[]) new Object[num];
        if (first < last) {
            System.arraycopy(items, 0, newArray, 0, first);
            System.arraycopy(items, last, newArray, num - max + last, max - last - 1);
            last = num - max + last;
            max = num;
        } else {
            for (int i = 0; i < size; i++) {
                newArray[i] = items[first - i - 1];
            }
            first = size - 1;
            max = num;
            last = num - 1;
        }
    }

    /** Add an item to the first of the list.
     * @param item is what we want to add.
     */
    public void addFirst(T item) {
        if (size + 2 >= max) {
            this.resize(max * 2);
        }
        if (first < last) {
            items[first] = item;
            first++;
            size++;
        } else if (first == max - 1) {
            first = 0;
            items[max - 1] = item;
            size++;
        } else {
            items[first] = item;
            first++;
            size++;
        }
    }

    /** Add a item to the last of the list.
     * @param item is what we want to add.
     */
    public void addLast(T item) {
        if (size + 2 >= max) {
            this.resize(max * 2);
        }
        items[last] = item;
        last --;
        size ++;
        if (first < last) {
            items[last] = item;
            last--;
            size++;
        } else if (last == 0) {
            last = max - 1;
            items[0] = item;
            size++;
        } else {
            items[last] = item;
            last++;
            size++;
        }
    }

    /** Check if the list is empty
     * Return ture if it is empty.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the size of list.*/
    public int size() {
        return size;
    }

    /** Print the items in the list.*/
    public void printDeque() {
        if (first < last) {
            int x = first;
            int y = max - 1;
            while (x > 0) {
                System.out.println(items[x]);
                x--;
            }
            while (y > last) {
                System.out.println(items[y]);
                y--;
            }
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println(items[first - i - 1]);
            }
        }
    }

    /** Remove the first one in the list
     * Return the removed one.*/
    public T removeFirst() {
        if (3 * size < max) {
            this.resize(max / 2);
        }
        if (size == 0) {
            return null;
        } else if (first == 0) {
            first = max - 1;
            size--;
            return items[max - 1];
        } else {
            first--;
            size--;
            return items[first];
        }
    }

    /** Remove the last one in the list
     * Return the removed one.*/
    public T removeLast() {
        if (3 * size < max) {
            this.resize(max / 2);
        }
        if (size == 0) {
            return null;
        } else if (last == max - 1) {
            last = 0;
            size--;
            return items[0];
        } else {
            last++;
            size--;
            return items[last];
        }
    }

    /** Return the index one's item.
     * @param index is where the item we want belongs to
     * Use iteration*/
    public T get(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        } else if (first - index < 1) {
            return items[max - 1 - index + first];
        } else {
            return items[first - index - 1];
        }
    }

    /** Return the index one's item.
     * @param index is where the item we want belongs to
     * Use recursion*/
    public T getRecursive(int index) {
        if (index + 1 > size || index < 0) {
            return null;
        }
        int temp_global = first;
        return getRecursiveHelper(temp_global, index);
    }

    /** The helper program of getRecursion
     * @param head is the first sentinel of a list
     * @param index is where the item we want belongs to
     * @return the item we want
     */
    private T getRecursiveHelper(int head, int index) {
        if (index == 0) {
            if (head == 0) return items[max - 1];
            else return items[head - 1];
        } else if (head == 0) {
            return getRecursiveHelper(max - 1, index - 1);
        } else {
            return getRecursiveHelper(head - 1, index - 1);
        }
    }
}
