package lab14;


import lab14lib.Generator;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/9/8 14:45
 */
public class SawToothGenerator implements Generator {
    private final int period;
    private int state;

    public SawToothGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        return normalize(state % period);
    }

    /**
     * Turn the number to -1.0~1.0.
     * @param flag is the input.
     * @return the number.
     */
    private double normalize(int flag) {
        return flag / (period - 1.0) * 2.0 - 1.0;
    }
}
