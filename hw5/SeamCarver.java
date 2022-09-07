/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/9/6 15:09
 */
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture p;

    public SeamCarver(Picture picture) {
        p = picture;
    }

    /**
     * Current picture.
     * @return the picture.
     */
    public Picture picture() {
        return p;
    }

    /**
     * The width of current picture
     * @return the width of the picture.
     */
    public int width() {
        return p.width();
    }

    /**
     * The height of current picture
     * @return the height of the picture.
     */
    public int height() {
        return p.height();
    }

    /**
     * Computing the Energy of a Pixel.
     * @param x is the column
     * @param y is the row.
     * @return the energy.
     */
    public double energy(int x, int y) {
        int wMax = width() - 1;
        int hMax = height() - 1;
        if (x < 0 || x > wMax || y < 0 || y > hMax) {
            throw new IndexOutOfBoundsException("The input is not allowed");
        }

        double deltaXSq = getGradientSq(getRGB(x - 1, y), getRGB(x + 1, y));
        double deltaYSq = getGradientSq(getRGB(x, y - 1), getRGB(x, y + 1));

        return deltaXSq + deltaYSq;
    }

    private int getRGB(int x, int y) {
        if (x < 0) {
            return p.getRGB(width() - 1, y);
        } else if (y < 0) {
            return p.getRGB(x, height() - 1);
        } else if (x >= width()) {
            return p.getRGB(0, y);
        } else if (y >= height()) {
            return p.getRGB(x, 0);
        } else {
            return p.getRGB(x, y);
        }
    }

    private double getGradientSq(int pre, int next) {
        int r0 = (pre >> 16) & 0xFF;
        int g0 = (pre >> 8) & 0xFF;
        int b0 = pre & 0xFF;
        int r1 = (next >> 16) & 0xFF;
        int g1 = (next >> 8) & 0xFF;
        int b1 = next & 0xFF;

        return (r0 - r1) * (r0 - r1) + (g0 - g1) * (g0 - g1) + (b0 - b1) * (b0 - b1);
    }

    public int[] findHorizontalSeam() {
        int flag;
        double sum;
        double[][] energyMap = new double[width()][height()];
        int[] lastResult = new int[width()];
        int[] result = new int[width()];
        double min = Double.MAX_VALUE;
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                energyMap[i][j] = energy(i, j);
            }
        }

        for (int j = 0; j < height(); j++) {
            flag = j;
            result[0] = flag;
            sum = energyMap[0][j];
            for (int i = 0; i < width() - 1; i++) {
                //Pick the minimum one.
                if (flag == 0) {
                    if (Math.min(energyMap[i + 1][flag], energyMap[i + 1][flag + 1]) ==
                            energyMap[i + 1][flag + 1]) {
                        sum += energyMap[i + 1][flag + 1];
                        flag++;
                    } else {
                        sum += energyMap[i + 1][flag];
                    }
                } else if (flag == height() - 1) {
                    if (Math.min(energyMap[i + 1][flag], energyMap[i + 1][flag - 1]) ==
                            energyMap[i + 1][flag - 1]) {
                        sum += energyMap[i + 1][flag - 1];
                        flag--;
                    } else {
                        sum += energyMap[i + 1][flag];
                    }
                } else {
                    double temp = Math.min(Math.min(energyMap[i + 1][flag], energyMap[i + 1][flag - 1]),
                            energyMap[i + 1][flag + 1]);
                    if (temp == energyMap[i + 1][flag - 1]) {
                        sum += energyMap[i + 1][flag - 1];
                        flag--;
                    } else if (temp == energyMap[i + 1][flag + 1]) {
                        sum += energyMap[i + 1][flag + 1];
                        flag++;
                    } else {
                        sum += energyMap[i + 1][flag];
                    }
                }
                result[i + 1] = flag;
            }

            if (sum < min) {
                lastResult = result;
                result = new int[width()];
                min = sum;
            }
        }
        return lastResult;
    }

    public int[] findVerticalSeam() {
        int flag;
        double sum;
        double[][] energyMap = new double[width()][height()];
        int[] lastResult = new int[height()];
        int[] result = new int[height()];
        double min = Double.MAX_VALUE;
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                energyMap[i][j] = energy(i, j);
            }
        }

        for (int i = 0; i < width(); i++) {
            flag = i;
            result[0] = flag;
            sum = energyMap[i][0];
            for (int j = 0; j < height() - 1; j++) {
                //Pick the minimum one.
                if (flag == 0) {
                    if (Math.min(energyMap[flag][j + 1], energyMap[flag + 1][j + 1]) ==
                    energyMap[flag + 1][j + 1]) {
                        sum += energyMap[flag + 1][j + 1];
                        flag++;
                    } else {
                        sum += energyMap[flag][j + 1];
                    }
                } else if (flag == width() - 1) {
                    if (Math.min(energyMap[flag][j + 1], energyMap[flag - 1][j + 1]) ==
                            energyMap[flag - 1][j + 1]) {
                        sum += energyMap[flag - 1][j + 1];
                        flag--;
                    } else {
                        sum += energyMap[flag][j + 1];
                    }
                } else {
                    double temp = Math.min(Math.min(energyMap[flag][j + 1], energyMap[flag - 1][j + 1]),
                            energyMap[flag + 1][j + 1]);
                    if (temp == energyMap[flag - 1][j + 1]) {
                        sum += energyMap[flag - 1][j + 1];
                        flag--;
                    } else if (temp == energyMap[flag + 1][j + 1]) {
                        sum += energyMap[flag + 1][j + 1];
                        flag++;
                    } else {
                        sum += energyMap[flag][j + 1];
                    }
                }
                result[j + 1] = flag;
            }

            if (sum < min) {
                lastResult = result;
                result = new int[height()];
                min = sum;
            }
        }
        return lastResult;
    }

    private void checkLegality(int[] seam, int length) {
        if (seam.length != length) {
            throw new IllegalArgumentException("The length of seam is illegal.");
        }
        for (int i = 0; i < length - 1; i++) {
            int temp = seam[i] - seam[i + 1];
            if (temp > 1 || temp < -1) {
                throw new IllegalArgumentException("The input seam is not continuous.");
            }
        }
    }

    /**
     * Remove horizontal seam from picture.
     * @param seam is the seam
     */
    public void removeHorizontalSeam(int[] seam) {
        checkLegality(seam, width());

        int flag;
        Picture temp = new Picture(width(), height() - 1);
        for (int i = 0; i < width(); i++) {
            flag = 0;
            for (int j = 0; j < height(); j++) {
                if (seam[i] != j) {
                    temp.setRGB(i, flag, p.getRGB(i, j));
                    flag++;
                }
            }
        }
        p = temp;
    }

    /**
     * Remove vertical seam from picture.
     * @param seam is the seam
     */
    public void removeVerticalSeam(int[] seam) {
        checkLegality(seam, height());

        int flag;
        Picture temp = new Picture(width() - 1, height());
        for (int j = 0; j < height(); j++) {
            flag = 0;
            for (int i = 0; i < width(); i++) {
                if (seam[j] != i) {
                    temp.setRGB(flag, j, p.getRGB(i, j));
                    flag++;
                }
            }
        }
        p = temp;
    }
}
