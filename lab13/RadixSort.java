/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int width = 0;
        String[] copy = new String[asciis.length];
        System.arraycopy(asciis, 0, copy, 0, asciis.length);

        for (String s : copy) {
            width = Math.max(width, s.length());
        }

        for (int i = width - 1; i >= 0; i--) {
            sortHelperLSD(copy, i);
        }
        return copy;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int[] flag = new int[257];
        String[] temp = new String[asciis.length];
        System.arraycopy(asciis, 0, temp, 0, asciis.length);

        for (String s : asciis) {
            if (s.length() > index) {
                flag[(int) s.charAt(index) + 1]++;
            } else {
                flag[0]++;
            }
        }

        // Handle the counter.
        for (int i = 0; i < 257; i ++) {
            if (i == 0) {
                flag[0]--;
            } else {
                flag[i] = flag[i - 1] + flag[i];
            }
        }

        for (int i = asciis.length - 1; i >= 0; i--) {
            if (asciis[i].length() > index) {
                int pos = (int) asciis[i].charAt(index) + 1;
                temp[flag[pos]] = asciis[i];
                flag[pos]--;
            } else {
                temp[flag[0]] = asciis[i];
                flag[0]--;
            }
        }
        System.arraycopy(temp, 0, asciis, 0, temp.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

//    public static void main(String[] args) {
//        String[] origin = new String[5];
//        String[] sorted;
//        origin[0] = "b";
//        origin[1] = "c";
//        origin[2] = "a";
//        origin[3] = "cc";
//        origin[4] = "aa";
//
//        sorted = RadixSort.sort(origin);
//        for (String s : origin) {
//            System.out.print(s + " ");
//        }
//
//        System.out.print("\n");
//
//        for (String s : sorted) {
//            System.out.print(s + " ");
//        }
//    }
}
