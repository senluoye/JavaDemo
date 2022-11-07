/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-21 20:53
 */
public class HeapSort {
    // Global variable that records the length of an array;
    static int heapLen;

    /**
     * Swap the two elements of an array
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Build Max Heap
     * @param arr
     */
    private static void buildMaxHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    /**
     * Adjust it to the maximum heap
     * @param arr
     * @param i
     */
    private static void heapify(int[] arr, int i) {
        // 左子节点
        int left = 2 * i + 1;
        // 右子节点
        int right = 2 * i + 2;

        // 找到左右子节点最大的节点
        int largest = i;
        if (right < heapLen && arr[right] > arr[largest]) {
            largest = right;
        }
        if (left < heapLen && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果最大的节点不是父节点，则交换
        if (largest != i) {
            swap(arr, largest, i);
            // 继续往下调整
            heapify(arr, largest);
        }
    }

    /**
     * Heap Sort
     * @param arr
     * @return
     */
    public static int[] heapSort(int[] arr) {
        // index at the end of the heap
        heapLen = arr.length;
        // build MaxHeap
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            // Move the top of the heap to the tail of the heap in turn
            swap(arr, 0, i);
            heapLen -= 1;
            heapify(arr, 0);
        }
        return arr;
    }

}
