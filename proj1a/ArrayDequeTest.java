public class ArrayDequeTest {
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<String> lld1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    public static void addFirstTest() {
        System.out.println("Running addFirst test");
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(1);
        passed = checkSize(1, lld1.size()) && passed;

        lld1.addFirst(2);
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addFirst(3);
        passed = checkSize(3, lld1.size()) && passed;

        lld1.addFirst(4);
        passed = checkSize(4, lld1.size()) && passed;

        lld1.addFirst(5);
        passed = checkSize(5, lld1.size()) && passed;

        lld1.addFirst(6);
        passed = checkSize(6, lld1.size()) && passed;

        lld1.addFirst(7);
        passed = checkSize(7, lld1.size()) && passed;

        lld1.addFirst(8);
        passed = checkSize(8, lld1.size()) && passed;

        lld1.addFirst(9);
        passed = checkSize(9, lld1.size()) && passed;

        int first = lld1.removeFirst();
        System.out.println("First: " + first);

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addFirstTest();
        //addRemoveTest();
    }
}
