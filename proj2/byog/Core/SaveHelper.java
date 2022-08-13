package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/13 15:45
 */
public class SaveHelper implements Serializable {
    private static final long serialVersionUID = 123123123123123L;
    public int[][] saveMap;
    public long saveSeed;
    public SaveHelper(int width, int height, TETile[][] map, long seed) {
        saveMap = encodeMap(width, height, map);
        saveSeed = seed;
    }

    private int[][] encodeMap(int width, int height, TETile[][] map) {
        int[][] temp = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (Tileset.FLOOR.equals(map[i][j])) {
                    temp[i][j] = 0;
                } else if (Tileset.NOTHING.equals(map[i][j])) {
                    temp[i][j] = 1;
                } else if (Tileset.PLAYER.equals(map[i][j])) {
                    temp[i][j] = 2;
                } else if (Tileset.WALL.equals(map[i][j])) {
                    temp[i][j] = 3;
                } else if (Tileset.LOCKED_DOOR.equals(map[i][j])) {
                    temp[i][j] = 4;
                } else {temp[i][j] = 5;}
            }
        }
        return temp;
    }
}
