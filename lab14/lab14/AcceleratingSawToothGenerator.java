package lab14;

import lab14lib.Generator;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/9/8 15:44
 */
public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private final double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.factor = factor;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        if (state % period == 0) {
            period = (int) (period * factor);
            state = 0;
        }
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
