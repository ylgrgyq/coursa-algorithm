package algo.lecture.analysis;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 15/9/28.
 * Author: ylgrgyq
 */
public class ThreeSumSort {

    public static void solveThreeSum(int[] src) {
        Arrays.sort(src);

        List<Triple<Integer, Integer,Integer>> ret = new ArrayList<>();
        for (int i = 0; i < src.length - 1; i++) {
            for (int j = i + 1; j < src.length - 1; j++) {
                int delta = src[i] + src[j];

                if (delta != 0) {
                    int searchResult = BinarySearch.search(src, -delta, j + 1, src.length);
                    if (searchResult != -1) {
                        ret.add(new ImmutableTriple<>(src[i], src[j], src[searchResult]));
                    }
                }
            }
        }

        System.out.println(ret);
    }


    public static void main(String[] args) {
        int[] data = {30, -40, -20, -10, 40, 0, 10, 5};

        ThreeSumSort.solveThreeSum(data);
    }
}
