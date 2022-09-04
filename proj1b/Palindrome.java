public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        while(d.size() > 1) {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if(first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        while(d.size() > 1) {
            Character first = d.removeFirst();
            Character last = d.removeLast();
            if(!cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

}
