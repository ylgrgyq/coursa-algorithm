package algo.lecture.elementarysort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 15/11/12.
 * Author: ylgrgyq
 */
public class QuickSort {

    public static <T extends Comparable<? super T>> void sort(T[] src){
        sort(src, 0, src.length);
    }

    public static <T extends Comparable<? super T>> void sort(T[] src, int lo, int hi){
        if (hi - 1 <= lo){
            return;
        }

        int pivot = partition(src, lo, hi);

        sort(src, lo, pivot);
        sort(src, pivot + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partition(T[] src, int lo, int hi){
        int pivot = lo + ThreadLocalRandom.current().nextInt(hi - lo - 1);

        Helper.swap(src, lo, pivot);

        int i = lo + 1;
        int j = hi - 1;
        while (true) {
            while (i <= j && Helper.less(src[i], src[lo])) {
                ++i;
            }

            while (Helper.less(src[lo], src[j])) {
                --j;
            }

            if (j <= i) {
                break;
            }

            Helper.swap(src, i++, j--);
        }

        Helper.swap(src, j, lo);

        return pivot;
    }

    public static void main(String[] args) {
        //Integer[] a = {98, 21, 45, 91, 87, 66, 53, 120, 111};
        Integer[] a = {1,2,1,1,1};

        QuickSort.sort(a);

        assert Helper.isSorted(a);

        System.out.println(Arrays.toString(a));
    }
}
