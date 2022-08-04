/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/4 16:48
 */
public class OffByN implements CharacterComparator {
    private int N;

    /**
     * Initiate the class.
     * @param i is a number
     */
    public OffByN(int i) {
        N = i;
    }

    /**
     * Check if it is palindrome by N.
     * @param x is a char
     * @param y is a char
     * @return true if it is palindrome
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == N || x - y == -N;
    }
}
