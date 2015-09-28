package algo.lecture.analysis;

import static com.google.common.base.Preconditions.*;

/**
 * Created on 15/9/28.
 * Author: ylgrgyq
 */
public class BinarySearch {
    public static int search(int[] src, int target){
        return search(src, target, 0, src.length);
    }

    public static int search(int[] src, int target, int start, int end){
        checkArgument(start >= 0 && start < src.length);
        checkArgument(end > 0 && end <= src.length);
        checkArgument(start < end);

        int lo = start;
        int hi = end - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if (src[mid] > target){
                hi = mid - 1;
            } else if (src[mid] < target){
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] data = {5, 6, 9, 100, 101};

        System.out.println(BinarySearch.search(data, 101));
    }
}
