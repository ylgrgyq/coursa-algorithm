package algo.lecture.union;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 15/9/22.
 * Author: ylgrgyq
 */
public class QuickUnionPathCompressionTest {
    @Test
    public void testQuickUnionPathCompression(){
        QuickUnionPathCompression p = new QuickUnionPathCompression(10);

        p.union(1, 6);
        p.union(9, 2);
        p.union(0, 8);
        p.union(5, 4);
        p.union(1, 9);
        p.union(2, 3);
        p.union(5, 7);
        p.union(0, 5);
        p.union(2, 5);

        p.print();
    }

}
