package algo.lecture.elementarysort;

import java.util.Arrays;

/**
 * Created on 15/10/30.
 * Author: ylgrgyq
 */
public class InsertionSort {

    public static <T extends Comparable<? super T>> void sort(T[] src){
        for (int i = 1; i < src.length; ++i) {
            T key = src[i];
            int j = i - 1;
            while (j >= 0 && Helper.less(key, src[j])){
                src[j + 1] = src[j];
                --j;
            }
            src[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {98, 21, 45, 91, 87, 66, 53, 120};

        InsertionSort.sort(a);

        assert Helper.isSorted(a);

        System.out.println(Arrays.toString(a));
    }
}
