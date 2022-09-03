public class ArrayDeque<T> {

    private static final int N = 8;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[N];
        size = 0;
        nextFirst = N / 2;
        nextLast = nextFirst + 1;
    }

    /**
     * resize the array, ture -> *2 , false -> /2
     */
    private void resize(boolean flag) {
        int newSize;
        if (flag) {
            newSize = items.length * 2;
        } else {
            newSize = items.length / 2;
        }
        T[] array = (T[]) new Object[newSize];
        int pos = nextFirst + 1;
        for (int count = 0; count < size; count++) {
            array[count] = items[(pos + count) % items.length];
        }
//        int beginFirst = items.length / 2, arrayFirst = array.length / 2;
//        while (beginFirst != nextFirst) {
//            array[arrayFirst] = items[beginFirst];
//            beginFirst = (beginFirst - 1 + items.length) % items.length;
//            arrayFirst = (arrayFirst - 1 + array.length) % array.length;
//        }
//
//        int beginLast = items.length / 2 + 1, arrayLast = array.length / 2 + 1;
//        while (beginLast != nextLast) {
//            array[arrayLast] = items[beginLast];
//            beginLast = (beginLast + 1) % items.length;
//            arrayLast = (arrayLast + 1) % array.length;
//        }

        items = array;
        nextFirst = newSize - 1;
        nextLast = size;
    }

    private void usageCheck() {
        double usageRadio = 1.0 * size / items.length;
        if (items.length == N || usageRadio >= 0.25) {
            return;
        }
        resize(false);
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(true);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(true);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /**
     * remove should +1, not -1
     * should check isEmpty before execute
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextFirst = (nextFirst + 1) % items.length;
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        usageCheck();
        return tmp;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextLast = (nextLast - 1 + items.length) % items.length;
        T tmp = items[nextLast];
        items[nextLast] = null;
        usageCheck();
        return tmp;
    }

    public T get(int index) {
        int n = items.length;
        return items[(nextFirst + 1 + index + n) % n];
    }
}
