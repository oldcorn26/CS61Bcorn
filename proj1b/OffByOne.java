/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/4 15:36
 */
public class OffByOne implements CharacterComparator {
    /**
     * Check if x and y are neighbors.
     * @param x is a char
     * @param y is a char
     * @return ture if them are neighbors
     */
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == 1 || x - y == -1;
    }
}
