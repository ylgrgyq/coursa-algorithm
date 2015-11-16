package algo.lecture.elementarysort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 15/11/12.
 * Author: ylgrgyq
 */
public class QuickSort {
    // use insertion sort when unsorted elements is under CUTOFF
    private static final int CUTOFF = 10;

    public static <T extends Comparable<? super T>> void sort(T[] src){
        sort(src, 0, src.length);
    }

    public static <T extends Comparable<? super T>> void sort(T[] src, int lo, int hi){
        if (hi - 1 <= lo + CUTOFF){
            InsertionSort.sort(src, lo, hi);
            return;
        }

        int pivot = partition(src, lo, hi);

        sort(src, lo, pivot);
        sort(src, pivot + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partition(T[] src, int lo, int hi){
        // another way to choose pivot is choosing Median-of-3 that is choose one of lo, hi, lo + (hi - lo) / 2 as
        // pivot randomly
        int pivot = lo + ThreadLocalRandom.current().nextInt(hi - lo - 1);

        Helper.swap(src, lo, pivot);

        int i = lo + 1;
        int j = hi - 1;
        while (true) {
            // stop when src[lo] equals to src[i] (so does src[lo] equals to src[j]) because we don't want to
            // put all items equal to pivot item on one side. For example, there's a sequence [A A A A A] to be sort.
            // if we don't stop on equal item we got 4 compares and 1 swap in the first recursion. then, 3 compares and 1
            // swap in the second recursion. then  2 compares and 1 swap. then 1 compares and 1 swap. It goes quadratic and
            // is 1/2 N^2 compares.
            // But if we stop on equal item we got a more balanced sequence in each recursion. 4 compares and 3 swap in the
            // first recursion. then 2 compares and 4 swaps in the second recursion. That's all. It's NlgN compares.
            // The difference will go bigger as N increases.
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

        return j;
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 91, 87, 66, 53, 120, 111, 2, 23, 44, 1123, 87, 45, 56, 123, 98, 93, 45, 21,8};
//        Integer[] a = {1,2,1,1,1};

        QuickSort.sort(a);

        assert Helper.isSorted(a);

        System.out.println(Arrays.toString(a));
    }
}
