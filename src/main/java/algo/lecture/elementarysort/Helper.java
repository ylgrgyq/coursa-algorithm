package algo.lecture.elementarysort;

/**
 * Created on 15/10/30.
 * Author: ylgrgyq
 */
class Helper {
    static <T extends Comparable<? super T>> boolean less(T a, T b){
        return a.compareTo(b) < 0;
    }

    static <T extends Comparable<? super T>> void swap(T[] a, int i, int j){
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    static <T extends Comparable<? super T>> boolean isSorted(T[] src){
        return isSorted(src, 0, src.length);
    }

    static <T extends Comparable<? super T>> boolean isSorted(T[] src, int start, int end){
        for (int i = start + 1; i < end; i++) {
            if (less(src[i], src[i - 1])){
                return false;
            }
        }

        return true;
    }

}
