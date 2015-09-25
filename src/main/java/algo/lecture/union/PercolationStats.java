package algo.lecture.union;

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Created on 15/9/24.
 * Author: ylgrgyq
 *
 * public class PercolationStats {
 *   public PercolationStats(int N, int T)     // perform T independent experiments on an N-by-N grid
 *   public double mean()                      // sample mean of percolation threshold
 *   public double stddev()                    // sample standard deviation of percolation threshold
 *   public double confidenceLo()              // low  endpoint of 95% confidence interval
 *   public double confidenceHi()              // high endpoint of 95% confidence interval
 *
 *   public static void main(String[] args)    // test client (described below)
 * }
 */

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int N, int T){
        checkArgument(N > 0 && T > 1);

        List<Double> xs = new ArrayList<>(T);
        for (int i = 0; i < T; i++) {
            xs.add(computePStar(N));
        }

        mean = xs.stream().reduce((a, b) -> a + b).get() / T;
        stddev = Math.pow(xs.stream().map(a -> Math.pow(a - mean, 2)).reduce((a, b) -> a + b).get() / (T - 1), 0.5);

        double delta = 1.96 * stddev / Math.pow(T, 0.5);
        confidenceLo = mean - delta;
        confidenceHi = mean + delta;
    }

    private double computePStar(int N){
        Random random = ThreadLocalRandom.current();
        Percolation p = new Percolation(N);

        int count = 0;
        while (! p.percolates()){
            int x = random.nextInt(N) + 1;
            int y = random.nextInt(N) + 1;

            if (! p.isOpen(x, y)) {
                p.open(x, y);
                ++count;
            }
        }

        return 1.0d * count / (N * N);
    }

    public double mean(){
        return mean;
    }

    public double stddev(){
        return stddev;
    }

    public double confidenceLo(){
        return confidenceLo;
    }

    public double confidenceHi(){
        return confidenceHi;
    }

    public static void main(String[] args) {
        PercolationStats s = new PercolationStats(200, 100);

        System.out.println("mean                    = " + s.mean());
        System.out.println("stddev                  = " + s.stddev());
        System.out.println("95% confidence interval = " + s.confidenceLo() + ", " + s.confidenceHi());
    }
}
