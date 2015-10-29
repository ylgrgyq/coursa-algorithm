package algo.lecture.elementarysort;

import java.util.Arrays;

/**
 * Like Bubble sort but have less swaps and is unstable.
 *
 * Why it's unstable?
 * For example, here's a sequence B, b, a, c and B equals b.
 * After selection sort, it'll become a, b, B, c where the order of B and b has changed.
 * It's unstable because less swaps compares to bubble sort.
 *
 * Created on 15/10/29.
 * Author: ylgrgyq
 */
public class SelectionSort{

    public static <T extends Comparable<? super T>> void sort(T[] src){
        for (int i = 0; i < src.length; ++i) {
            int min = i;
            for (int j = i + 1; j < src.length; ++j){
                if (less(src[j], src[min])){
                    min = j;
                }
            }

            swap(src, i, min);
        }
    }

    private static <T extends Comparable<? super T>> boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    private static <T extends Comparable<? super T>> void swap(T[] a, int i, int j){
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <T extends Comparable<? super T>> boolean test(T[] src){
        for (int i = 1; i < src.length; i++) {
            if (less(src[i], src[i - 1])){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 87, 66, 53, 120};

        SelectionSort.sort(a);

        assert SelectionSort.test(a);

        System.out.println(Arrays.toString(a));
    }
}
