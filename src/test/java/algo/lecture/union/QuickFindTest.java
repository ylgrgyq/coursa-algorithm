package algo.lecture.union;

import static org.junit.Assert.*;

/**
 * Created on 15/9/22.
 * Author: ylgrgyq
 */
public class QuickFindTest {
    @org.junit.Test
    public void testUnion() throws Exception {
        QuickFind u = new QuickFind(10);

        u.union(5, 1);
        u.union(4, 5);
        u.union(5, 9);
        u.union(7, 9);
        u.union(5, 8);
        u.union(1, 0);

        u.print();

        assertTrue(u.connected(0, 8));
    }
}
