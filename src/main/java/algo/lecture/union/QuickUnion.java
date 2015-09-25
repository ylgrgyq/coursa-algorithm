package algo.lecture.union;

import com.google.common.base.Preconditions;

public class QuickUnion {
    private int[] ids;

    public QuickUnion(int n) {
        ids = new int[n];

        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    private int root(int a) {
        int root = a;

        while (ids[root] != root) {
            root = ids[root];
        }

        return root;
    }

    public boolean connected(int a, int b) {
        Preconditions.checkArgument(a < ids.length && a >= 0);
        Preconditions.checkArgument(b < ids.length && b >= 0);

        return root(a) == root(b);
    }

    public void union(int a, int b) {
        Preconditions.checkArgument(a < ids.length && a >= 0);
        Preconditions.checkArgument(b < ids.length && b >= 0);

        int ra = root(a);
        int rb = root(b);

        ids[ra] = rb;
    }

    public void print() {
        for (int i : ids){
            System.out.println(i);
        }
    }
}
