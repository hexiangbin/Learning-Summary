import java.util.Arrays;
import java.util.Random;

/**
 * 排序算法总结
 *
 * @author HeXiangBin
 * @date 2019-11-04
 */
public class Sort {
    public static void main(String[] args) {
        Random random = new Random();
        int[] nums = new int[20];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(1000);
        }

//        bubbleSort(nums);
//        selectSort(nums);
//        insertSort(nums);
//        quickSort(nums);
//        mergeSort(nums);
          heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void bubbleSort(int[] nums) {
        for (int end = nums.length-1; end > 0; end--) {
            boolean isSort = true;
            for (int j = 0; j < end; j++) {
                // 对[0, end)区间进行排序
                if (nums[j] > nums[j+1]) {
                    swap(nums, j, j+1);
                    isSort = false;
                }
            }
            if (isSort) {
                // 如果某段区间已经有序，则后序的所有子区间也有序，不用再进行排序
                break;
            }
        }
    }

    public static void selectSort(int[] nums) {
        for (int start = 0; start < nums.length; start++) {
            int min = start;
            for (int i = start + 1; i < nums.length; i++) {
                // 选择[start, nums.length]内最小数的索引
                if (nums[i] < nums[min]) {
                    min = i;
                }
            }
            swap(nums, start, min);
        }
    }

    public static void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 当前要插入的数
            int base = nums[i];
            int j;
            for (j = i - 1; j >= 0 && nums[j] > base; j--) {
                // 所有大于base的数全部往后移动一位
                nums[j + 1] = nums[j];
            }
            // 插入base
            nums[j+1] = base;
        }
    }

    public static void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length-1);
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 随机选一个数作为基数
        swap(nums, left, left + (int) (Math.random() * (right - left + 1)));

        // 三路快排，partition返回区间范围,[left, range[0]) < base, (rang[1], right) > base
        int[] range = partition(nums, left, right);
        quickSort(nums, left, range[0]-1);
        quickSort(nums, range[1]+1, right);
    }

    public static int[] partition(int[] nums, int left, int right) {
        // 基准值
        int base = nums[left];
        // 定义区间[left, less] < base [more, right] > base
        int less = left;
        int more = right + 1;
        // 当前判断的值
        int cur = left + 1;
        while (cur < more) {
            if (nums[cur] > base) {
                // 注意，这里不是cur++, 因为交换后的值还需要进行判断
                swap(nums, cur, --more);
            } else if (nums[cur] < base) {
                swap(nums, cur++, ++less);
            } else {
                cur++;
            }
        }
        // less之前有过less++,所以这里直接交换left和less
        swap(nums, left, less);

        return new int[]{less, more-1};
    }

    public static void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        mergeSort(nums, 0, nums.length-1);
    }

    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(nums, left, middle);
        mergeSort(nums, middle + 1, right);
        merge(nums, left, middle, right);
    }

    public static void merge(int[] nums, int left, int middle, int right) {
        // 临时数组，用来储存合并后的有序数组
        int[] temp = new int[right - left + 1];
        // 临时数组的指针
        int cur = 0;
        // 定义左右区间的两个指针
        int p1 = left;
        int p2 = middle + 1;
        while (p1 <= middle && p2 <= right) {
            // 按序合并
            temp[cur++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= middle) {
            // 合并剩余数据
            temp[cur++] = nums[p1++];
        }
        while (p2 <= right) {
            // 合并剩余数据
            temp[cur++] = nums[p2++];
        }
        // 赋值原数组
        for (int i = 0; i < temp.length; i++) {
            nums[left++] = temp[i];
        }
    }

    public static void heapSort(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            // 将0-end区间的数组进行建堆，建完堆后排在堆首的就是最大数字
            buildHeap(nums, i);
            // 交换最大数字到队尾
            swap(nums, 0, i);
        }
    }

    public static void buildHeap(int[] nums, int end) {
        for (int i = end; i >= 0; i--) {
            // 从后往前开始调整推,调整完后i的子树满足最大堆特性
            adjustHeap(nums, i, end);
        }
    }

    public static void adjustHeap(int[] nums, int current, int end) {
        if (current <= end) {
            int left = current * 2 + 1;
            int right = current * 2 + 2;

            // 假设当前节点就是最大值
            int max = current;

            if (left <= end && nums[left] > nums[max]) {
                //如果比当前根元素要大，记录它的位置
                max = left;
            }

            if (right <= end && nums[right] > nums[max]) {
                //如果比当前根元素要大，记录它的位置
                max = right;
            }

            if (max != current) {
                // 说明当前节点不符合最大堆特性，需要调整
                swap(nums, current, max);
                // 交换后的节点有可能不符合最大堆特性，还需要继续调整
                adjustHeap(nums, max, end);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
