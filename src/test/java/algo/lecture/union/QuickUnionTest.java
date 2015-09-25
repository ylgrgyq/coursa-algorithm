package algo.lecture.union;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickUnionTest {
    @Test
    public void testQuickUnion(){
        QuickUnion u = new QuickUnion(10);

        u.union(1, 6);
        u.union(9, 2);
        u.union(6, 3);
        u.union(2, 3);
        u.union(1, 5);

        assertTrue(u.connected(1, 3));

        u.print();
    }
}
