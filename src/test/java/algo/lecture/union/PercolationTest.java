package algo.lecture.union;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 15/9/24.
 * Author: ylgrgyq
 */
public class PercolationTest {
    @Test
    public void testPercolation(){
        Percolation p = new Percolation(3);

        p.open(2, 2);
        p.open(2, 3);
        p.open(1, 1);
        p.open(1, 2);

        System.out.println(p.isFull(2, 3));

        p.print();
    }

}
