package algo.lecture.elementarysort;

import java.util.Arrays;

/**
 * Created on 15/11/1.
 * Author: ylgrgyq
 */
public class MergeSort {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> void sort(T[] a){
        T[] aux = (T[])new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        // we have sorted results in aux for every recursion
        // so we should exchange a and aux in the very first time we calling sort
        sort(a, aux, 0, a.length);
    }

    private static <T extends Comparable<? super T>> void sort(T[] aux, T[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            assert a[i] == aux[i];
        }

        if (hi <= lo + 1){
            return;
        }

        int mid = lo + (hi - lo) / 2;
        // sort a from lo to mid and sort a from mid to hi
        sort(a, aux, lo, mid);
        sort(a, aux, mid, hi);
        // then merge left part of a and right part of a into aux
        // so the final sorted result is in aux
        // and we switch a and aux in every recursion
        merge(aux, a, lo, hi, mid);
    }

    private static <T extends Comparable<? super T>> void merge(T[] aux, T[] a, int lo, int hi, int mid) {
        assert Helper.isSorted(a, lo, mid);
        assert Helper.isSorted(a, mid, hi);

        int i = lo;
        int j = mid;
        for (int cur = lo; cur < hi; cur++) {
            if (i >= mid){
                aux[cur] = a[j++];
            } else if (j >= hi){
                aux[cur] = a[i++];
            } else if (Helper.less(a[i], a[j])){
                aux[cur] = a[i++];
            } else {
                aux[cur] = a[j++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 91, 87, 66, 53, 120, 111};

        MergeSort.sort(a);

        assert Helper.isSorted(a);

        System.out.println(Arrays.toString(a));
    }
}
