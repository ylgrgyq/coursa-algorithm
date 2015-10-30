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
                if (Helper.less(src[j], src[min])){
                    min = j;
                }
            }

            Helper.swap(src, i, min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 87, 66, 53, 120};

        SelectionSort.sort(a);

        assert Helper.test(a);

        System.out.println(Arrays.toString(a));
    }
}
