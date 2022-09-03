public class LinkedListDeque<T> {

    private ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        if(other.sentinel == null) {
            return;
        }
        ListNode n = other.sentinel.next;
        while(n != other.sentinel) {
            addFirst(n.item);
            n = n.next;
        }
    }

    public void addFirst(T item) {
        /** new node alloc */
        ListNode n = new ListNode(item, sentinel, sentinel.next);
        /* No.2 node, next is unchanged*/
        sentinel.next.prev = n;

        sentinel.next = n;
        size += 1;
    }

    public void addLast(T item) {
        ListNode n = new ListNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = n;

        sentinel.prev = n;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode n = sentinel.next;

        while (n != sentinel) {
            System.out.print(n.item + " ");
            n = n.next;
        }

        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T tmp = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;

        size -= 1;
        return tmp;
    }

    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        T tmp = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;

        return tmp;
    }

    public T get(int index) {
        if(index >= size || index < 0) {
            return null;
        }
        int count = 0;
        ListNode n = sentinel.next;
        while(count != index) {
            count += 1;
            n = n.next;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if(index >= size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(ListNode n, int index) {
        if(index == 0) {
            return n.item;
        }
        return getRecursive(n.next, index - 1);
    }

    public class ListNode {
        ListNode prev;
        T item;
        ListNode next;

        public ListNode(T t, ListNode prev, ListNode next) {
            this.item = t;
            this.prev = prev;
            this.next = next;
        }
    }
}
