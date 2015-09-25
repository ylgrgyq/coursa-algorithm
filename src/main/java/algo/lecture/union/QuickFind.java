package algo.lecture.union;

import com.google.common.base.Preconditions;

public class QuickFind {
    private int[] ids;

    public QuickFind(int N){
        Preconditions.checkArgument(N > 0);

        ids = new int[N];

        for (int i = 0; i < N; i++) {
            ids[i] = i;
        }
    }

    public boolean connected(int a, int b){
        Preconditions.checkArgument(a < ids.length && a >= 0);
        Preconditions.checkArgument(b < ids.length && b >= 0);

        return ids[a] == ids[b];
    }

    public void union(int a, int b) {
        Preconditions.checkArgument(a < ids.length && a >= 0);
        Preconditions.checkArgument(b < ids.length && b >= 0);

        int aid = ids[a];
        int bid = ids[b];

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == aid) {
                ids[i] = bid;
            }
        }
    }

    public void print() {
        for (int i : ids){
            System.out.println(i);
        }
    }
}
