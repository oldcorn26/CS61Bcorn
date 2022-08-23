package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] temp = new int[M];
        for (Oomage o : oomages) {
            temp[(o.hashCode() & 0x7FFFFFFF) % M]++;
        }
        for (int num : temp) {
            if (num < oomages.size() / 50 || num > oomages.size() / 2.5) return false;
        }
        return true;
    }
}
