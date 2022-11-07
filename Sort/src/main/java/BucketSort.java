import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Dessert
 * @Description 桶排序
 * <p>计数排序的升级版，将某个元素映射到某个桶里，在桶内继续递归使用桶排序或其他排序算法</p>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-21 21:21
 */
public class BucketSort {
    /**
     * Gets the maximum and minimum values in the array
     * @param arr
     * @return
     */
    private static int[] getMinAndMax(List<Integer> arr) {
        int maxValue = arr.get(0);
        int minValue = arr.get(0);
        for (int i : arr) {
            if (i > maxValue) {
                maxValue = i;
            } else if (i < minValue) {
                minValue = i;
            }
        }
        return new int[] { minValue, maxValue };
    }

    /**
     * Bucket Sort
     * @param arr
     * @return
     */
    public static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
        if (arr.size() < 2 || bucketSize == 0) {
            return arr;
        }
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        int bucket_cnt = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucket_cnt; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int element : arr) {
            int idx = (element - minValue) / bucketSize;
            buckets.get(idx).add(element);
        }
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i).size() > 1) {
                buckets.set(i, bucketSort(buckets.get(i), bucketSize / 2));
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            result.addAll(bucket);
        }
        return result;
    }

}
