package algo.lecture.union;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 15/9/22.
 * Author: ylgrgyq
 */
public class QuickUnionWeightedTest {
    @Test
    public void testQuickUnionWeighted(){
        QuickUnionWeighted w = new QuickUnionWeighted(10);

        w.union(1, 6);
        w.union(9, 2);
        w.union(0, 8);
        w.union(5, 4);
        w.union(1, 9);
        w.union(2, 3);
        w.union(5, 7);
        w.union(0, 5);
        w.union(2, 5);

        w.print();
    }

}
