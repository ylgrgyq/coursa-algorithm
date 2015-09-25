package algo.lecture.union;

/**
 * Created on 15/9/22.
 * Author: ylgrgyq
 */
public class QuickUnionWeighted {
    private int[] ids;
    private int[] sz;

    public QuickUnionWeighted(int N){
        ids = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int a){
        int root = a;

        while (ids[root] != root){
            root = ids[root];
        }

        return root;
    }

    public void union(int a, int b){
        int ra = root(a);
        int rb = root(b);

        int sza = sz[ra];
        int szb = sz[rb];

        if (sza >= szb){
            ids[rb] = ra;
            sz[ra] = sz[ra] + sz[rb];
        } else {
            ids[ra] = rb;
            sz[rb] = sz[ra] + sz[rb];
        }
    }

    public boolean connected(int a, int b) {
        return root(a) == root(b);
    }

    public void print() {
        for (int i : ids){
            System.out.println(i);
        }
    }
}
