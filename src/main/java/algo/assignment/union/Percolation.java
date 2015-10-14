package algo.assignment.union;

import algo.lecture.union.QuickUnionPathCompression;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created on 15/9/24.
 * Author: ylgrgyq
 *
 * public class Percolation {
 *     public Percolation(int N)               // create N-by-N grid, with all sites blocked
 *     public void open(int i, int j)          // open site (row i, column j) if it is not open already
 *     public boolean isOpen(int i, int j)     // is site (row i, column j) open?
 *     public boolean isFull(int i, int j)     // is site (row i, column j) full?
 *     public boolean percolates()             // does the system percolate?

 *     public static void main(String[] args)  // test client (optional)
 * }
 */
public class Percolation {
    private final int N;

    private final QuickUnionPathCompression qupc;

    private final byte[] opened;

    public Percolation(int N) {
        checkArgument(N > 0);

        this.N = N;
        opened = new byte[N * N + 2];

        qupc = new QuickUnionPathCompression(N * N + 2);
        for (int i = toFlatIndex(1, 1); i <= toFlatIndex(1, N); i++) {
            qupc.union(0, i);
        }

        int lastNodeIndex = toFlatIndex(N + 1, 1);
        for (int i = toFlatIndex(N, 1); i <= toFlatIndex(N, N); i++) {
            qupc.union(i, lastNodeIndex);
        }
    }

    public void print(){
        qupc.print();
    }

    private int toFlatIndex(int i, int j) {
        return (i - 1) * N + j;
    }

    private Optional<Integer> regulate(int i, int j) {
        if (i > 0 && i <= N && j > 0 && j <= N) {
            return Optional.of(toFlatIndex(i, j));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> up(int i, int j) {
        return regulate(i - 1, j);
    }

    private Optional<Integer> down(int i, int j) {
        return regulate(i + 1, j);
    }

    private Optional<Integer> left(int i, int j) {
        return regulate(i, j - 1);
    }

    private Optional<Integer> right(int i, int j) {
        return regulate(i, j + 1);
    }

    public void open(int i, int j) {
        final int index = toFlatIndex(i, j);

        opened[index] = 1;

        List<Optional<Integer>> list = new ArrayList<>();
        list.add(up(i, j));
        list.add(down(i, j));
        list.add(left(i, j));
        list.add(right(i, j));

        list.stream()
                .filter(integer -> integer.isPresent() && isOpen(integer.get()))
                .forEach(integer -> qupc.union(index, integer.get()));
    }

    public boolean isOpen(int index) {
        checkArgument(index > 0 && index <= toFlatIndex(N, N));

        return opened[index] != 0;
    }

    public boolean isOpen(int i, int j) {
        checkArgument(i > 0 && i <= N);
        checkArgument(j > 0 && j <= N);

        return isOpen(toFlatIndex(i, j));
    }

    public boolean isFull(int i, int j) {
        checkArgument(i > 0 && i <= N);
        checkArgument(j > 0 && j <= N);

        return qupc.connected(0, toFlatIndex(i, j));
    }

    public boolean percolates() {
        return qupc.connected(0, toFlatIndex(N + 1, 1));
    }

    public static void main(String[] args) {
        Random r = new Random();

        int N = 200;
        Percolation p = new Percolation(N);

        int count = 0;
        while (! p.percolates()){
            int x = r.nextInt(N) + 1;
            int y = r.nextInt(N) + 1;

            if (! p.isOpen(x, y)) {
                p.open(x, y);
                ++count;
            }
        }

        System.out.println("open site:" + count + " p*:" + 1.0d * count / (N * N));
    }
}
