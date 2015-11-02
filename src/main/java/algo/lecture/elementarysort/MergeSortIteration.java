package algo.lecture.elementarysort;

import java.util.Arrays;

/**
 * Created on 15/11/1.
 * Author: ylgrgyq
 */
public class MergeSortIteration {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void sort(T[] a){
        T[] aux = (T[])new Comparable[a.length];

        for (int step = 1; step < a.length; step *= 2) {
            for (int cur = 0; cur < a.length - step; cur += 2 * step) {
                merge(a, aux, cur, cur + step, Math.min(cur + 2 * step, a.length - 1));
            }
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert Helper.isSorted(a, lo, mid);
        assert Helper.isSorted(a, mid, hi);

        System.arraycopy(a, lo, aux, lo, hi - lo);

        int i = lo;
        int j = mid;
        int cur = lo;
        while (i < mid && j < hi){
            if (Helper.less(aux[i], aux[j])){
                a[cur++] = aux[i++];
            } else {
                a[cur++] = aux[j++];
            }
        }

        while (i < mid){
            a[cur++] = aux[i++];
        }

        while (j < hi) {
            a[cur++] = aux[j++];
        }
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 91, 87, 66, 53, 120, 111};

        MergeSortIteration.sort(a);

        assert Helper.isSorted(a);

        System.out.println(Arrays.toString(a));
    }
}
