/**
 * @ClassName Dessert
 * @Description 计数排序
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-21 21:12
 */
public class CountingSort {
    /**
     * Gets the maximum and minimum values in the array
     *
     * @param arr
     * @return
     */
    private static int[] getMinAndMax(int[] arr) {
        int maxValue = arr[0];
        int minValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            } else if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }
        return new int[] { minValue, maxValue };
    }

    /**
     * Counting Sort
     * @param arr
     * @return
     */
    public static int[] countingSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        // 计数数组的长度为最大值-最小值+1
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        int[] countArr = new int[maxValue - minValue + 1]; // 计数数组
        int[] result = new int[arr.length]; // 结果数组

        // 计算每个数字出现的次数
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - minValue] += 1;
        }
        // 主要是为了保证稳定性
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        // 从后向前遍历，某个数组的位置就是 countArr[arr[i] - minValue] - 1
        for (int i = arr.length - 1; i >= 0; i--) {
            int idx = countArr[arr[i] - minValue] - 1;
            result[idx] = arr[i];
            countArr[arr[i] - minValue] -= 1;
        }
        return result;
    }

}
