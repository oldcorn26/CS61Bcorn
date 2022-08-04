/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/3 23:41
 */
public class Palindrome {
    /** Turn the string into a deque.
     * @param word is the string we want to handle
     * @return a deque
     */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> L = new LinkedListDeque();
        Deque d = (Deque) L;
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    /**
     * Check if the string is a palindrome.
     * @param word is the string we want to check
     * @return true if it is palindrome
     */
    public boolean isPalindrome(String word) {
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Check if the string is a palindrome the '1' distance is allowed.
     * @param word is the string we want to check
     * @param cc is the CharacterComparator parameter
     * @return true if it is palindrome
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        boolean flag = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(word.length() - 1 - i))) {
                flag = false;
            }
        }
        return flag;
    }
}
