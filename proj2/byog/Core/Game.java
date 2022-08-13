package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
//import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private static final int ROOM_NUMBER = 15;
    private static Random RANDOM;
    private static TETile[][] map = new TETile[WIDTH][HEIGHT];
    private static boolean playerTurn;
    private static boolean readSave;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        /* Initial sth.*/
        int[] player;
        playerTurn = true;
        getRANDOM(getSeed(input));

        /* Randomly create some rooms, hallways.*/
        Room[] rooms = fillRoomsRandomly();
        fillHallwaysRandomly(rooms);

        /* Supplement the rest part and move the coordinate of player.*/
        player = fillOtherPlace(rooms);
        player = getPlayerCoordinate(input, player);

        /* Draw the map and interact with people.*/
//        drawMap(player);

        return map;
    }

    /**
     * Draw a map and wait for 25ms.
     * @param player is the coordinate of player.
     */
    private void drawMap(int[] player) {
        ter.initialize(WIDTH, HEIGHT + 1);
        while (playerTurn) {
            ter.renderFrame(map);
            showMapInfo();
            player = getKeyTyped(player);
//            StdDraw.pause(25);
        }
    }

    /**
     * Initial the coordinate of player.
     * @param input is the String.
     * @param player is the coordinate of player.
     * @return the new coordinate of player.
     */
    private int[] getPlayerCoordinate(String input, int[] player) {
        int i = 1;
        if (input.charAt(0) == 'N' || input.charAt(0) == 'n') {
            while (input.charAt(i) != 'S' && input.charAt(i) != 's') {
                i++;
            }
            i++;
        }
        input = input.substring(i);
        for (i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'w':
                case 'W':
                    if (movePlayer('U', player[0], player[1])) {
                        player[1]++;
                    }
                    break;
                case 's':
                case 'S':
                    if (movePlayer('D', player[0], player[1])) {
                        player[1]--;
                    }
                    break;
                case 'a':
                case 'A':
                    if (movePlayer('L', player[0], player[1])) {
                        player[0]--;
                    }
                    break;
                case 'd':
                case 'D':
                    if (movePlayer('R', player[0], player[1])) {
                        player[0]++;
                    }
                    break;
            }
        }
        return player;
    }

    /**
     * Get the key input and move the player.
     * @param player is the coordinate of the player.
     * @return the new coordinate of the player.
     */
    private int[] getKeyTyped(int[] player) {
//        if (StdDraw.hasNextKeyTyped()) {
//            char key = StdDraw.nextKeyTyped();
//            if (key == 'w' || key == 'W') {
//                if (movePlayer('U', player[0], player[1])) {player[1]++;}
//            } else if (key == 's' || key == 'S') {
//                if (movePlayer('D', player[0], player[1])) {player[1]--;}
//            } else if (key == 'a' || key == 'A') {
//                if (movePlayer('L', player[0], player[1])) {player[0]--;}
//            } else if (key == 'd' || key == 'D') {
//                if (movePlayer('R', player[0], player[1])) {player[0]++;}
//            }
//        }
        return player;
    }

    /**
     * Change the coordinate of the player.
     * @param MODE is the way player moves to.
     * @param x,y is the coordinate of the previous player.
     * @return true if move the player successfully.
     */
    private boolean movePlayer(char MODE, int x, int y) {
        switch (MODE) {
            case 'U':
                if (checkIfFloor(x, y + 1)) {
                    map[x][y] = Tileset.FLOOR;
                    map[x][y + 1] = Tileset.PLAYER;
                    return true;
                }
                break;
            case 'D':
                if (checkIfFloor(x, y - 1)) {
                    map[x][y] = Tileset.FLOOR;
                    map[x][y - 1] = Tileset.PLAYER;
                    return true;
                }
                break;
            case 'L':
                if (checkIfFloor(x - 1, y)) {
                    map[x][y] = Tileset.FLOOR;
                    map[x - 1][y] = Tileset.PLAYER;
                    return true;
                }
                break;
            case 'R':
                if (checkIfFloor(x + 1, y)) {
                    map[x][y] = Tileset.FLOOR;
                    map[x + 1][y] = Tileset.PLAYER;
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Check if the coordinate is FLOOR.
     * @param x,y is the coordinate.
     * @return true if it is floor.
     */
    private boolean checkIfFloor(int x, int y) {
        if (x >= WIDTH || x < 0 || y >= HEIGHT || y < 0) return false;
        return map[x][y] == Tileset.FLOOR;
    }

    /**
     * Draw the information of the mouse place.
     */
    private void showMapInfo() {
//        int x = (int)StdDraw.mouseX();
//        int y = (int)StdDraw.mouseY();
//        String s;
//        if (x >= WIDTH || x < 0 || y >= HEIGHT || y < 0) return;
//        if (map[x][y] == Tileset.WALL) s = "WALL";
//        else if (map[x][y] == Tileset.FLOOR) s = "FLOOR";
//        else if (map[x][y] == Tileset.LOCKED_DOOR) s = "LOCKED DOOR";
//        else if (map[x][y] == Tileset.PLAYER) s = "PLAYER";
//        else s = "";
//        StdDraw.setPenColor(Color.white);
//        StdDraw.textLeft(3, HEIGHT, s);
//        StdDraw.show();
    }

    /**
     * Fill some rooms randomly.
     * @return the rooms array.
     */
    private Room[] fillRoomsRandomly() {
        int flag = 0;
        Room[] rooms = new Room[Game.ROOM_NUMBER];

        // Randomly create some rooms.
        while (checkIfAdd(rooms) && flag < 15) {
            int rx = RANDOM.nextInt(WIDTH);
            int ry = RANDOM.nextInt(HEIGHT);
            int rw = RANDOM.nextInt(WIDTH / 5);
            int rh = RANDOM.nextInt(HEIGHT / 3);
            Room temp = new Room(rx, ry, rw, rh);

            if (temp.checkIfOut(WIDTH, HEIGHT) || temp.checkIfOverlap(rooms)) {
            } else {
                rooms[flag] = temp;
                fillFloor(temp);
                flag++;
            }
        }
        BubbleSort.sort(rooms);
        return rooms;
    }

    /**
     * Fill some hallways randomly.
     * @param rooms is the rooms array.
     */
    private void fillHallwaysRandomly(Room[] rooms) {
        for (int i = 0; i < ROOM_NUMBER - 1; i++) {
            if (rooms[i] == null) continue;
            createHallway(rooms[i], rooms[i + 1],
                    RANDOM.nextInt(10), RANDOM.nextInt(10));
        }
    }

    /**
     * Fill rest of the map, walls, door, continuity.
     * @param rooms is the rooms array.
     * @return the initial coordinate of the player.
     */
    private int[] fillOtherPlace(Room[] rooms) {
        int flag = 0;
        int[] player;
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (map[i][j] == null) {
                    map[i][j] = Tileset.NOTHING;
                } else if (checkIfWall(i, j)) {
                    map[i][j] = Tileset.WALL;
                } else {}
            }
        }
        checkContinuity();
        while ((player = getDoor(rooms[rooms.length / 2 + flag])) == null) {
            flag++;
        }
        return player;
    }

    /**
     * Randomly create a door.
     * @param room is the room we pick.
     * @return the coordinate of the player.
     */
    private int[] getDoor(Room room) {
        int[] result = new int[2];
        for (int j = room.pos.x[1]; j < room.getDiagonal()[1]; j++) {
            for(int i = room.pos.x[0]; i <= room.getDiagonal()[0]; i++) {
                if (map[i][j] == Tileset.WALL && map[i][j + 1] == Tileset.FLOOR) {
                    map[i][j] = Tileset.LOCKED_DOOR;
                    fillPlayer(i, j + 1);
                    result[0] = i;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Fill a player.
     * @param x,y is the coordinate of the player.
     */
    private void fillPlayer(int x, int y) {
        map[x][y] = Tileset.PLAYER;
    }

    /**
     * Get a random maker.
     * @param seed is the random seed.
     */
    private void getRANDOM(long seed) {
        RANDOM = new Random(seed);
    }

    /**
     * Get the random seed in the input.
     * @param input is the input
     * @return the number seed.
     */
    private long getSeed(String input) {
        String s = "";
        if (input.charAt(0) == 'N' || input.charAt(0) == 'n') {
            int i = 1;
            while (input.charAt(i) != 'S' && input.charAt(i) != 's') {
                s += input.charAt(i);
                i++;
            }
            return Long.parseLong(s);
        } else if (input.charAt(0) == 'L' || input.charAt(0) == 'l') {
            return 0;
        }
        else return 0;
    }

    /**
     * Check if the total area is beyond 2/3.
     * @param rooms is the array of rooms
     * @return true if beyond.
     */
    private boolean checkIfAdd(Room[] rooms) {
        int areaAll = WIDTH * HEIGHT;
        int area = 0;
        for (Room r : rooms) {
            if (r == null) continue;
            area += r.width * r.height;
        }
        if (area > areaAll * 2 / 3) {
            return false;
        } else return true;
    }

    /**
     * Fill the rooms' floor.
     * @param room is the room we want to fill in.
     */
    private void fillFloor(Room room) {
        for (int i = room.pos.x[0]; i <= room.getDiagonal()[0]; i++) {
            for(int j = room.pos.x[1]; j <= room.getDiagonal()[1]; j++) {
                map[i][j] = Tileset.FLOOR;
            }
        }
    }

    /**
     * Check if it is wall.
     * @param i is the x.
     * @param j is the y.
     * @return true if it is wall.
     */
    private boolean checkIfWall(int i, int j) {
        if (i == 0 || j == 0 || i == WIDTH - 1 || j == HEIGHT - 1) {
            return true;
        } else if (map[i - 1][j] == null || map[i - 1][j] == Tileset.NOTHING ||
                map[i + 1][j] == null || map[i + 1][j] == Tileset.NOTHING ||
                map[i][j - 1] == null || map[i][j - 1] == Tileset.NOTHING ||
                map[i][j + 1] == null || map[i][j + 1] == Tileset.NOTHING) {
            return true;
        } else return false;
    }

    /** Create some hallways.
     * @param r1,r2 is the room we want to connect together.
     * @param n1 is this room we start, 1~10.
     * @param n2 is the other room we end, 1~10.
     */
    private void createHallway(Room r1, Room r2, int n1, int n2) {
        int[] start = getTilePosition(r1, n1);
        int[] end = getTilePosition(r2, n2);
        int[] temp = new int[2];
        if (n1 > n2) {
            temp[0] = start[0]; temp[1] = end[1];
        } else {
            temp[0] = end[0]; temp[1] = start[1];
        }
        fillHallway(start, temp);
        fillHallwayExtend(start, temp);
        fillHallway(temp, end);
    }

    /**
     * To extend the hallway we create in case of a mistaking conor.
     * @param start is the coordinate of the start point.
     * @param end is the coordinate of the end point.
     */
    private void fillHallwayExtend(int[] start, int[] end) {
        if (start[0] < end[0] && end[0] < WIDTH - 1 &&
                end[1] > 0 && end[1] < HEIGHT - 1) {
            map[end[0] + 1][end[1]] = Tileset.FLOOR;
            map[end[0] + 1][end[1] - 1] = Tileset.FLOOR;
            map[end[0] + 1][end[1] + 1] = Tileset.FLOOR;
        }
        if (start[0] > end[0] && end[0] > 0 &&
                end[1] > 0 && end[1] < HEIGHT - 1) {
            map[end[0] - 1][end[1]] = Tileset.FLOOR;
            map[end[0] - 1][end[1] - 1] = Tileset.FLOOR;
            map[end[0] - 1][end[1] + 1] = Tileset.FLOOR;
        }
        if (start[1] > end[1] && end[1] > 0 &&
                end[0] > 0 && end[0] < WIDTH - 1) {
            map[end[0]][end[1] - 1] = Tileset.FLOOR;
            map[end[0] + 1][end[1] - 1] = Tileset.FLOOR;
            map[end[0] - 1][end[1] - 1] = Tileset.FLOOR;
        }
        if (start[1] < end[1] && end[1] < HEIGHT - 1 &&
                end[0] > 0 && end[0] < WIDTH - 1) {
            map[end[0]][end[1] + 1] = Tileset.FLOOR;
            map[end[0] + 1][end[1] + 1] = Tileset.FLOOR;
            map[end[0] - 1][end[1] + 1] = Tileset.FLOOR;
        }
    }

    /**
     * Get the coordinate we want to start in a room randomly.
     * @param r is the room we want to start.
     * @param num is a random number, 0~10;
     * @return the coordinate we start.
     */
    public int[] getTilePosition(Room r, int num) {
        if (num <= 1 || num >= 9) num = 5;
        int area = r.height * r.width * num / 10;
        int[] result = new int[2];
        result[0] = r.pos.x[0] + area % r.width;
        result[1] = r.pos.x[1] + area / r.width;
        return result;
    }

    /**
     * Fill the hallway we create.
     * @param start is the coordinate we start.
     * @param end is the coordinate we end.
     */
    private void fillHallway(int[] start, int[] end) {
        int x, y, z, min, max;
        if (start[0] == end[0]) {
            min = Math.min(start[1], end[1]);
            max = Math.max(start[1], end[1]);
            if (start[0] == 0) {
                x = 0; y = 1; z = 2;
            } else if (start[0] == WIDTH - 1) {
                x = WIDTH - 1; y = WIDTH - 2; z = WIDTH - 3;
            } else {
                x = start[0] - 1; y = start[0]; z = start[0] + 1;
            }
            for (; min <= max; min++) {
                map[x][min] = Tileset.FLOOR;
                map[y][min] = Tileset.FLOOR;
                map[z][min] = Tileset.FLOOR;
            }
        } else if (start[1] == end[1]) {
            min = Math.min(start[0], end[0]);
            max = Math.max(start[0], end[0]);
            if (start[1] == 0) {
                x = 0; y = 1; z = 2;
            } else if (start[1] == HEIGHT - 1) {
                x = HEIGHT - 1; y = HEIGHT - 2; z = HEIGHT - 3;
            } else {
                x = start[1] - 1; y = start[1]; z = start[1] + 1;
            }
            for (; min <= max; min++) {
                map[min][x] = Tileset.FLOOR;
                map[min][y] = Tileset.FLOOR;
                map[min][z] = Tileset.FLOOR;
            }
        }
    }

    /**
     * Let the edge become continuous.
     */
    private void checkContinuity() {
        for (int i = 0; i < WIDTH - 1; i++) {
            for (int j = 0; j < HEIGHT - 1; j++) {
                if (map[i][j] != Tileset.FLOOR) continue;
                if (map[i - 1][j] == Tileset.WALL && map[i][j - 1] == Tileset.WALL &&
                        map[i + 1][j] != Tileset.WALL && map[i][j + 1] != Tileset.WALL &&
                        map[i + 1][j + 1] == Tileset.NOTHING || map[i - 1][j + 1] == Tileset.NOTHING ||
                        map[i + 1][j - 1] == Tileset.NOTHING || map[i - 1][j - 1] == Tileset.NOTHING)
                    map[i][j] = Tileset.FLOWER;
                if (map[i - 1][j] != Tileset.WALL && map[i][j - 1] == Tileset.WALL &&
                        map[i + 1][j] == Tileset.WALL && map[i][j + 1] != Tileset.WALL &&
                        map[i + 1][j + 1] == Tileset.NOTHING || map[i - 1][j + 1] == Tileset.NOTHING ||
                        map[i + 1][j - 1] == Tileset.NOTHING || map[i - 1][j - 1] == Tileset.NOTHING)
                    map[i][j] = Tileset.FLOWER;
                if (map[i - 1][j] != Tileset.WALL && map[i][j - 1] != Tileset.WALL &&
                        map[i + 1][j] == Tileset.WALL && map[i][j + 1] == Tileset.WALL &&
                        map[i + 1][j + 1] == Tileset.NOTHING || map[i - 1][j + 1] == Tileset.NOTHING ||
                        map[i + 1][j - 1] == Tileset.NOTHING || map[i - 1][j - 1] == Tileset.NOTHING)
                    map[i][j] = Tileset.FLOWER;
                if (map[i - 1][j] == Tileset.WALL && map[i][j - 1] != Tileset.WALL &&
                        map[i + 1][j] != Tileset.WALL && map[i][j + 1] == Tileset.WALL &&
                        map[i + 1][j + 1] == Tileset.NOTHING || map[i - 1][j + 1] == Tileset.NOTHING ||
                        map[i + 1][j - 1] == Tileset.NOTHING || map[i - 1][j - 1] == Tileset.NOTHING)
                    map[i][j] = Tileset.FLOWER;
            }
        }
        for (int i = 0; i < WIDTH - 1; i++) {
            for (int j = 0; j < HEIGHT - 1; j++) {
                if (map[i][j] == Tileset.FLOWER) {map[i][j] = Tileset.WALL;}
            }
        }
    }
}
