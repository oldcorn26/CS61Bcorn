package byog.Core;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/11 13:48
 */
public class BubbleSort {
    public static void sort(Room[] rooms) {
        int min, flag;
        int[] arr = handleArray(rooms);
        for (int i = 0; i < arr.length; i++) {
            min = arr[i];
            flag = i;
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    flag = j;
                }
            }
            swap(arr, i, flag);
            swap(rooms, i, flag);
        }
    }

    private static int[] handleArray(Room[] rooms) {
        int[] arr = new int[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] == null) {
                arr[i] = 999;
            } else {
                arr[i] = (rooms[i].pos.x[0] + rooms[i].pos.x[1] +
                        rooms[i].width + rooms[i].height) / 2;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(Room[] rooms, int i, int j) {
        Room temp = rooms[i];
        rooms[i] = rooms[j];
        rooms[j] = temp;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
