package byog.Core;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/10 14:39
 */
public class Room {
    public position pos;
    public int width;
    public int height;

    public class position {
        int[] x;

        public position(int pox, int poy) {
            x = new int[2];
            x[0] = pox;
            x[1] = poy;
        }
    }

    public Room(int x, int y, int wid, int hei) {
        pos = new position(x, y);
        width = wid;
        height = hei;
    }

    public int[] getDiagonal() {
        int[] dia = new int[2];
        dia[0] = pos.x[0] + width - 1;
        dia[1] = pos.x[1] + height - 1;
        return dia;
    }

    public boolean checkIfOverlap(Room[] rooms) {
        for (Room r : rooms) {
            if(checkIfContain(r)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfContain(Room room) {
        if (room == null) return false;
        int x1 = pos.x[0];
        int y1 = pos.x[1];
        int x2 = getDiagonal()[0];
        int y2 = getDiagonal()[1];

        int X1 = room.pos.x[0] - 2;
        int Y1 = room.pos.x[1] - 2;
        int X2 = room.getDiagonal()[0] + 2;
        int Y2 = room.getDiagonal()[1] + 2;

        if (X1 >= x2 || X2 <= x1 || Y1 >= y2 || Y2 <= y1) {
            return false;
        } else return true;
    }

    public boolean checkIfOut(int WIDTH, int HEIGHT) {
        int x2 = getDiagonal()[0];
        int y2 = getDiagonal()[1];

        if (x2 >= WIDTH || y2 >= HEIGHT || checkIfSmall(width, height)) {
            return true;
        } else return false;
    }

    private boolean checkIfSmall(int rw, int rh) {
        return rw <= 4 || rh <= 4;
    }


}
