package synthesizer;

//import edu.princeton.cs.introcs.StdAudio;

//Make sure this class is public
public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int cap = (int) (Math.round(SR / frequency));
        buffer = new ArrayRingBuffer<Double>(cap);
        while (cap > 0) {
            buffer.enqueue(0.0);
            cap--;
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int cap = buffer.capacity();
        double [] music = new double[cap];
        double r;
        this.clearBuffer();
        while (cap > 0) {
            r = Math.random() - 0.5;
            while(this.checkSame(music, r)) {
                r = Math.random() - 0.5;
            }
            buffer.enqueue(r);
            cap--;
        }
    }

    /** Clear all the numbers.*/
    private void clearBuffer() {
        int num = buffer.fillCount();
        while (num > 0) {
            buffer.dequeue();
            num--;
        }
    }

    /** Check if it is same.
     * @param music is a array
     * @param checkNum is the number we want to check
     * @return true if it is true
     */
    private boolean checkSame(double[] music, double checkNum) {
        int num = buffer.fillCount();
        for (int i = 0; i < num; i++) {
            if (music[i] == checkNum) {
                return true;
            }
        }
        return false;
    }


    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        double fir = buffer.dequeue();
        double nxt = buffer.peek();
        buffer.enqueue(DECAY * (fir + nxt) / 2);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
