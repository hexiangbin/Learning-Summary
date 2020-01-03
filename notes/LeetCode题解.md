* [数据结构相关问题](#数据结构相关问题)
    * [数组字符串](#数组)
    * [字符串](#字符串)
    * [链表](#链表)
    * [栈](#栈)
    * [队列](#队列)
    * [优先队列](#优先队列)
    * [树](#树)
    
* [常见算法](#常见算法)
    * [二分查找](#二分查找)
    * [递归](#递归)
    * [回溯](#回溯)
    * [深度优先](#深度优先)
    * [广度优先](#广度优先)
    * [动态规划](#动态规划)
        * [矩阵类](#矩阵类)
        * [序列类](#序列类)
        * [字符串匹配类](#字符串匹配类)
# LeetCode题解 #

## 数据结构相关问题 ##

### 数组 ###

[283. 移动零](https://leetcode-cn.com/problems/move-zeroes/description/)

题目描述：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:输入: [0,1,0,3,12] 输出: [1,3,12,0,0]

说明:1.必须在原数组上操作，不能拷贝额外的数组。2.尽量减少操作次数。
```java
class Solution {
    public static void moveZeroes(int[] nums) {
        int p=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0)continue;
            swap(nums,p++,i);
        }
    }
    public  static void swap(int[] nums,int i,int j){
        int temp;
        temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

}
```

[26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/description/)

题目描述：给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:给定数组 nums = [1,1,2], 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。你不需要考虑数组中超出新长度后面的元素。

示例 2:给定 nums = [0,0,1,1,1,2,2,3,3,4],函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。你不需要考虑数组中超出新长度后面的元素。
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1)
            return nums.length;
        int len=0;
        for(int i=0;i<nums.length;i++){
            if(i+1<nums.length&&nums[i]!=nums[i+1]){
                //与后一个数字不相等
                nums[len++]=nums[i];
            }
        }
        //还剩最后一个元素没有判断，不管什么情况下（与前一个元素相等或者不等），最后一个元素肯定在新的数组里面
        nums[len++]=nums[nums.length-1];
        return len;
    }
}
```      
[27. 移除元素](https://leetcode-cn.com/problems/remove-element/description/)

题目描述：给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:给定 nums = [3,2,2,3], val = 3,函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。

示例 2:给定 nums = [0,1,2,2,3,0,4,2], val = 2,函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int len=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[len++]=nums[i];
            }
        }
        return len;
    }
}
```
[215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/)

题目描述：在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:输入: [3,2,1,5,6,4] 和 k = 2,输出: 5

示例 2:输入: [3,2,3,1,2,4,5,5,6] 和 k = 4,输出: 4

说明:你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,k-1);//第k大的数，下标为k-1
    }
    public int findKthLargest(int []nums,int left,int right,int k){
        if(left==right)
            return nums[left];
        int p=partition(nums,left,right);
        if(p==k)
            return nums[k];
        else if (p>k)//待查找的数字在p左边
            return findKthLargest(nums,left,p-1,k);
        else //p<k
            return findKthLargest(nums,p+1,right,k);
    }
    //swap
    public  void swap(int[] nums,int i,int j){
        int temp;
        temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    //partition
    public int partition(int[] arr,int left,int right){
        if(right-left>15) {
            int random = (int) Math.random() * (right - left + 1) + left;
            swap(arr, left, random);
        }
        int base=arr[left];
        int lt=left+1; //[l+1...lt) > p ; [lt..i) < p
        for(int i=left+1;i<=right;i++ ){
            if(arr[i]>base){
                swap(arr,i,lt++);
            }
        }
        swap(arr,left,lt-1);
        return lt-1;
    }
}
```
[75. 分类颜色](https://leetcode-cn.com/problems/sort-colors/description/)

题目描述：给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。注意:不能使用代码库中的排序函数来解决这道题。

示例:输入: [2,0,2,1,1,0] 输出: [0,0,1,1,2,2]
```java
class Solution {
    public void sortColors(int[] nums) {
        sortColors(nums,0,nums.length-1,1);//k=1
    }
    public void sortColors(int[] nums,int left,int right,int k){
        partiton(nums,left,right,k);
    }
    public  void swap(int[] nums,int i,int j){
        int temp;
        temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    //三路快排，基于1排序，1左边为0，右边为2，一次排序出结果
    public void partiton(int[] nums,int left,int right,int k){
        int lt=left-1;//[l+1...lt]<k
        int gt=right+1;//[gt.....r]>k
        int i=left;//[lt+1...i)=k
        while(i<gt){
            if(nums[i]<k){
                swap(nums,i++,++lt);
            }else if(nums[i]>k){
                swap(nums,i,--gt);
            }else{
                i++;
            }
        }
    }
}
```
[167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/description/)

题目描述：给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:返回的下标值（index1 和 index2）不是从零开始的。你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

示例:输入: numbers = [2, 7, 11, 15], target = 9,输出: [1,2]

解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        assert (numbers.length>=2);
        int left=0,right=numbers.length-1;//对撞指针
        int[] res=new int[2];
        while(left<right){
            if(numbers[left]+numbers[right]==target) {
                res[0]=left+1;//返回的下标值（index1 和 index2）不是从零开始的，所以+1
                res[1]=right+1;
                return res;
            }else if(numbers[left]+numbers[right]<target){
                left++;
            }else{
                right--;
            }
        }
        throw new IllegalStateException("no answer");
    }
}
```

[4. 寻找两个有序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/submissions/)

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

```java
class Solution {
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int[] nums = merge(nums1, nums2);
        
    //     int length = nums.length;
        
    //     if(length % 2 == 1) {
    //         // 奇数
    //         return nums[length/2];
    //     } else{
    //         return (double)(nums[length/2] + nums[length/2 -1]) / 2;
    //     }
        
    // }
    
    // private int[] merge(int[] nums1, int[] nums2) {
    //     int l1 = nums1.length;
    //     int l2 = nums2.length;
    //     int[] res = new int[l1+l2];
        
    //     if(l1 == 0) {
    //         return nums2;
    //     }
        
    //     if(l2 == 0) {
    //         return nums1;
    //     }
        
    //     int current1 = 0;
    //     int current2 = 0;
    //     int currentResult = 0;
        
    //     while(current1 < l1 && current2 < l2) {
    //         if(nums1[current1] < nums2[current2]) {
    //             res[currentResult++] = nums1[current1++];
    //         } else{
    //             res[currentResult++] = nums2[current2++];
    //         }
    //     }
        
    //     if(current1 == l1) {
    //         while(current2 < l2) {
    //             res[currentResult++] = nums2[current2++];
    //         }
    //     }
        
    //     if(current2 == l2) {
    //         while(current1 < l1) {
    //             res[currentResult++] = nums1[current1++];
    //         }
    //     }
        
    //     return res;
    // }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] num = new int[nums1.length + nums2.length];
        for (int i = 0; i < nums1.length; i++) {
            num[i] = nums1[i];
        }
        for (int i = nums1.length; i < nums1.length + nums2.length; i++) {
            num[i] = nums2[i-nums1.length];
        }
        if (num.length % 2 == 1) {
            return findK(num, 0, num.length - 1, num.length / 2);
        } else {
            return (findK(num, 0, num.length - 1, num.length / 2)
                    + findK(num, 0, num.length - 1, num.length / 2 - 1)) / 2.0;
        }
    }

    private static int findK(int[] num, int left, int right, int k) {
        if (left > right) {
            return -1;
        }

        int partition = partition(num, left, right);

        if (partition == k) {
            return num[partition];
        } else if (partition > k) {
            return findK(num, left, partition-1, k);
        } else {
            return findK(num, partition+1, right, k);
        }
    }

    private static int partition(int[] num, int left, int right) {
        int pivot = num[left];
        int gt = left;

        for (int i = left + 1; i <= right; i++) {
            if (num[i] > pivot) {
                swap(num, i, ++gt);
            }
        }

        swap(num, left, gt);

        return gt;
    }

    private static void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
```

[56. 合并区间](https://leetcode-cn.com/problems/merge-intervals/submissions/)

给出一个区间的集合，请合并所有重叠的区间。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();

        int[] pre = intervals[0];
        int[] cur = pre;

        for (int i = 1; i < intervals.length; i++) {
            cur = intervals[i];

            if (cur[0] <= pre[1]) {
                // 需要合并
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                // 不需要合并
                result.add(pre);
                pre = cur;
            }
        }

        result.add(pre);

        return result.toArray(new int[result.size()][]);
    }
}
```

[435. 无重叠区间](https://leetcode-cn.com/problems/non-overlapping-intervals/)

给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 贪婪法

        // 按起始时间排序
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        int end = Integer.MIN_VALUE;
        int count = 0;

        for (int[] interval : intervals) {
            if (interval[0] >= end) {
                // 没有重叠
                end = interval[1];
            } else {
                // 有重叠，选取截止时间靠前的
                end = Math.min(end, interval[1]);
                // 需要删除的个数+1
                count++;
            }
        }

        return count;
    }
}
```

### 字符串 ###

[125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome/description/)

题目描述：给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:输入: "A man, a plan, a canal: Panama",输出: true

示例 2:输入: "race a car",输出: false
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
   public boolean isPalindrome(String s) {
        if(s.isEmpty())
            return true;
       //将字符串进行匹配，保留数字和字母
        String regEx="[^A-Za-z0-9]";
        Pattern p=Pattern.compile(regEx);
        Matcher m=p.matcher(s);
        String input=m.replaceAll("").trim().toLowerCase();
       //转化成char数组
        char[] arr=input.toCharArray();
        if(arr.length==1)
            return true;
       //对撞指针
        int left=0,right=arr.length-1;
        while(left<right){
            if(arr[left]!=arr[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
}
```
[344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/description/)

题目描述：请编写一个函数，其功能是将输入的字符串反转过来。

示例：输入：s = "hello",返回："olleh"
```java
class Solution {
    public String reverseString(String s) {
        char[] arr=s.toCharArray();
        int left=0,right=arr.length-1;
        while(left<right){
            swap(arr,left,right);
            left++;
            right--;
        }
        return String.valueOf(arr);
    }
    public void swap(char[] arr,int left,int right){
        char temp;
        temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }
}
```
[11. 盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/description/)

题目描述：给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。
<div align="center"> <img src="../pictures/question_11.jpg"/> </div><br>
图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:输入: [1,8,6,2,5,4,8,3,7] 输出: 49
```java
class Solution {
    /*
    最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，
    我们需要考虑更长的两条线段之间的区域。如果我们试图将指向较长线段的指针向内侧移动，
    矩形区域的面积将受限于较短的线段而不会获得任何增加。
    但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
    因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小
     */
    public int maxArea(int[] height) {
        int left=0,right=height.length-1;
        int maxArea=0;
        while(left<right){
            maxArea=Math.max(maxArea,(right-left)*Math.min(height[left],height[right]));
            if(height[left]<height[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxArea;
    }
}
```
[209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/description/)

题目描述：给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

示例: 输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2,解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        assert (s>0 && nums!=null);
        //滑动窗口
        int left=0,right=-1;//nums[left....right]为滑动窗口
        int sum=0;
        int len=nums.length+1;
        while(left<nums.length){
            if(right+1<nums.length && sum<s)
                sum+=nums[++right];
            else //sum>=s
                sum-=nums[left++];
            
            if(sum>=s)
                len=Math.min(len,right-left+1);
        }
        if(len==nums.length+1)//无解
            return 0;
        return len;
    }
}
```
[3. 无重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/)

题目描述：给定一个字符串，找出不含有重复字符的最长子串的长度。

示例：给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] fre=new int[256];//记录窗口中出现的字符
        int left=0,right=-1;//滑动窗口
        int len=0;
        while(left<s.length()){
            if(right+1<s.length() && fre[s.charAt(right+1)]==0)
                fre[s.charAt(++right)]++;
            else
                fre[s.charAt(left++)]--;
            len=Math.max(len,right-left+1);
        }
        return len;
    }
}
```
### 链表
[25. K 个一组翻转链表](https://leetcode-cn.com/problems/reverse-nodes-in-k-group/)

给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
```java
class Solution {
    // 解题图解参考：
    // https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
    public ListNode reverseKGroup(ListNode head, int k) {
        // 定义一个虚拟头节点 dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // prev 表示翻转的k链表的前节点，end表示k链表的结束节点
        ListNode prev = dummy;
        ListNode end = dummy;
        
        while(end.next != null) {
            for(int i = 0 ; i < k ; i++) {
                if(end == null) {
                    break;
                }
                end = end.next;
            }
            if(end == null) {
                // 说明后面不满足k个节点，跳出循环
                break;
            }
        
            // 这次翻转链表的开始节点
            ListNode start = prev.next;
            // 下次要翻转链表的开始节点
            ListNode next = end.next;
            // 断开这一段链表，开始翻转
            end.next = null;
            // prev.next = 翻转后的头节点
            prev.next = reverse(start);
            // start翻转前是头节点，翻转后是尾节点，所以翻转后start.next=下次要翻转链表的开始节点
            start.next = next;
            
            // 开始重置prev和end,都为翻转后链表的尾节点（注意，翻转后，这里的start已经是链表的尾节点）
            prev = start;
            end = start;
        }
        
        return dummy.next;
    }
    
    // 翻转链表，返回翻转后的头节点
    private ListNode reverse(ListNode root) {
        if(root == null) {
            return root;
        }
        ListNode prev = null;
        ListNode cur = root;
        ListNode next;
        
        while(cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }
}
```

[23. 合并K个排序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/submissions/)

合并 k 个排序链表，返回合并后的排序链表

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length-1);
    }

    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;

        return merge(mergeKLists(lists, start, mid), mergeKLists(lists, mid + 1, end));
    }

    public ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        ListNode virtualHead = new ListNode(Integer.MIN_VALUE);
        ListNode temp = virtualHead;

        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                temp.next = node1;
                node1 = node1.next;
            } else {
                temp.next = node2;
                node2 = node2.next;
            }
            temp = temp.next;
        }

        temp.next = node1 == null ? node2 : node1;

        return virtualHead.next;
    }
}
```

### 栈
[20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

```java
class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                // 是右开放符号
                if(!stack.isEmpty() && stack.peek() == map.get(c)) {
                    // 匹配时消除字符
                    stack.pop();
                } else {
                    // stack为空，或者不匹配时推入字符
                    stack.push(c);
                }
            } else {
                // 不是右开放符号，推入字符
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
}
```
[739. 每日温度](https://leetcode-cn.com/problems/daily-temperatures/submissions/)

根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
        // 存的是数组里对应的索引
        Stack<Integer> stack = new Stack<>();
        
        int[] result = new int[T.length];
        
        for(int i = 0; i < T.length; i++) {
            while(!stack.isEmpty() && T[stack.peek()] < T[i]) {
                // 气温升高
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        
        return result;
    }
}
```
### 队列
[239. 滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。


示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 


你能在线性时间复杂度内解决此题吗？

```java
class Solution {
    // https://leetcode-cn.com/problems/sliding-window-maximum/solution/shuang-xiang-dui-lie-jie-jue-hua-dong-chuang-kou-2/
    // 双端队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        
        LinkedList<Integer> deque = new LinkedList<>();

        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // 把队列中小于当前值的，从后往前依次去除
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }

            // 把当前值加入队尾
            deque.addLast(i);

            // 队首元素是否还在滑动窗口内，注意边界判断是 <= i - k
            if (deque.peekFirst() <= i - k) {
                // 已经离开K窗口范围
                deque.removeFirst();
            }


            if(i  - k + 1 >= 0) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

}
```

### 优先队列
[347. 前 K 个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/)

给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // key = num, value = 出现次数
        Map<Integer, Integer> count = new HashMap<>();

        for(int i : nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }

        // 定义比较规则
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> count.get(x) - count.get(y));

        for(int key : count.keySet()) {
            queue.offer(key);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        LinkedList<Integer> result = new LinkedList<>();

        while(!queue.isEmpty()) {
            result.addFirst(queue.poll());
        }

        return result;
    }
}
```

### 树
[144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/submissions/)

给定一个二叉树，返回它的 前序 遍历。

```java
class Solution {
    private static List<Integer> result;

    public List<Integer> preorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        
        preOrder(root);

        return result;
    }

    // 递归
    public void preOrder(TreeNode node) {
        if(node != null) {
            result.add(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 非递归
    public void preOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;

        while(currentNode != null || !stack.isEmpty()) {
            while(currentNode != null) {
                // 打印根节点
                result.add(currentNode.val);
                // 一直沿着左子树深度遍历
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            // 到这一步，说明当前栈顶元素的左子树为空，因为把当前节点置为当前栈顶元素的右子树
            currentNode = stack.pop().right;
        }
    }
}
```

[94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/submissions/)

给定一个二叉树，返回它的中序 遍历。
```java
class Solution {
    private List<Integer> result;
    
    public List<Integer> inorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        inorder(root);

        return result;
    }
    
    // 递归
    public void inorder(TreeNode root){
        if(root != null){
            inorder(root.left);
            result.add(root.val);
            inorder(root.right); 
        }
    }

    // 非递归
    public void inorder(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;

        while(currentNode != null || !stack.isEmpty()) {
            while(currentNode != null) {
                // 一直沿着左子树深度遍历
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            
            // 到这一步，说明当前栈顶元素的左子树为空，打印当前栈顶元素
            currentNode = stack.pop();
            result.add(currentNode.val);
            // 置为当前栈顶元素右节点
            currentNode = currentNode.right;
        }
    }
}
```

[145. 二叉树的后序遍历](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

给定一个二叉树，返回它的 后序 遍历
```java
class Solution {
    private static List<Integer> result;
    public List<Integer> postorderTraversal(TreeNode root) {
        result = new ArrayList<>();
        if(root == null) {
            return result;
        }

        postOrder(root);

        return result;
    }

    // 递归
    public void postOrder(TreeNode node) {
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            result.add(node.val);
        }
    }

    // 非递归
    public void postOrder(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> output = new Stack<>();

        stack.push(node);

        TreeNode currentNode;

        while(!stack.isEmpty()) {
            currentNode = stack.pop();
            output.push(currentNode.val);

            if(currentNode.left != null) {
                stack.push(currentNode.left);
            }

            if(currentNode.right != null) {
                stack.push(currentNode.right);
            }
        }

        while(!output.isEmpty()) {
            result.add(output.pop());
        }
    }
}
```

[230. 二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

```java
class Solution {
    private int res;
    private int k;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        // 中序遍历
        inorder(root);
        return res;
    }
    
    private void inorder(TreeNode root){
        if(root == null){
            return;
        }
        
        inorder(root.left);
        if(--k == 0) {
            res = root.val;
        }
        inorder(root.right);
    }
}
```

[110. 平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/submissions/)

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }

    // dfs遍历
    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        if (left == -1) {
            // 剪枝，避免多余计算
            return -1;
        }
        int right = depth(root.right);
        if (right == -1) {
            // 剪枝，避免多余计算
            return -1;
        }

        // 如果左右子树平衡，返回左右子树中最大的深度+1
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
```

## 常见算法 ##

### 二分查找 ###
[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {findLowBound(nums, 0, nums.length-1, target), findHighBound(nums, 0, nums.length-1, target)};
        return result;
    }

    private int findLowBound(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                // 相等的情况下
                if (mid == 0 || nums[mid-1] < target) {
                    // 是数组中的第一位，或者前一位小于target
                    return mid;
                } else {
                    // 否则上边界在左半区间
                    end = mid - 1;
                }
            }
        }

        return -1;
    }

    private int findHighBound(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        while(start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                // 相等的情况下
                if (mid == nums.length-1 || nums[mid+1] > target) {
                    // 是数组中的最后一位，或者后一位大于target
                    return mid;
                } else {
                    // 否则下边界在右半区间
                    start = mid + 1;
                }
            }
        }

        return -1;
    }
}
```

[33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/submissions/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

```java
class Solution {
       public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        int left = 0;
        int right = length - 1;


        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= nums[left]) {
                // 左有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

}
```
### 递归 ###

[91. 解码方法](https://leetcode-cn.com/problems/decode-ways/)

一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:

输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2:

输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。

```java
class Solution {
    public int numDecodings(String s) {
        if(s == null) {
            return 0;
        }

        return decode(s, 0);
    }

    public int decode(String s, int index) {
        if(index == s.length()) {
            return 1;
        }

        if(s.charAt(index) == '0') {
            // 如果当前数字是0，无法翻译,即无法单个翻译，也无法两个翻译
            return 0;
        }

        // 只翻译一个数字 1~9
        int d1 = decode(s, index + 1);
        // 翻译两个数字 10~26
        int d2 = 0;

        if(index + 1 < s.length()) {
            char c1 = s.charAt(index);
            char c2 = s.charAt(index + 1);
            // 两个数字范围 10 ～ 26 ,才可以翻译
            if(c1 == '1' || (c1 == '2' && c2 <= '6')) {
                d2 = decode(s, index + 2);
            }
        }

        return d1 + d2;
    }
}
```

[687. 最长同值路径](https://leetcode-cn.com/problems/longest-univalue-path/submissions/)

给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

```java
class Solution {
    int res = 0;
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return res;
    }
    // 最关键的是定义出递归函数，并了解该递归函数的意义
    // 定义递归函数，表示该节点左右子树中，最长的同值路径
    public int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = helper(node.left);
        int right = helper(node.right);
        
        if(node.left != null && node.left.val == node.val) {
            // 和左子树相等，左子树路径+1
            left++;
        } else {
            // 不相等置为0
            left = 0;
        }

        
        if(node.right != null && node.right.val == node.val) {
            // 和右子树相等，右子树路径+1
            right++;
        } else {
            // 不相等置为0
            right = 0;
        }

        res = Math.max(res, left + right);

        return Math.max(left, right);
    }
}
```

[124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)

给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

```java
class Solution {
    private int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return max;
    }

    public int maxPath(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = Math.max(maxPath(node.left), 0);
        int right = Math.max(maxPath(node.right), 0);

        // 计算包括该节点在内的最大路径和，与max比较
        max = Math.max(max, left + right + node.val);

        // 返回以该节点出发的左子树或者右子树的最大值
        return node.val + Math.max(left, right);
    }
}
```

### 回溯

[39. 组合总和](https://leetcode-cn.com/problems/combination-sum/submissions/)

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。
解集不能包含重复的组合。 

```java
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        
        backTrace(candidates, target, 0, new ArrayList<>());

        return result;
    }

    public void backTrace(int[] candidates, int target, int index, List<Integer> temp) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(temp));
        }

        for(int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            backTrace(candidates, target - candidates[i], i, temp);
            temp.remove(temp.size()-1);
        }
    }
}
```

[51.N皇后](https://leetcode-cn.com/problems/n-queens/)

n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

```java
class Solution {
    // 棋子的位置
    int[] queen;
    // 列的位置
    int[] cols;
    // 正对角线 (row - col + n) = 0 ~ n-1
    int[] diagnols1;
    // 负对角线 (row + col) = 0 ~ n-1
    int[] diagnols2;
    // 结果
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        queen = new int[n];
        cols = new int[n];
        diagnols1 = new int[2 * n];
        diagnols2 = new int[2 * n];

        backTrace(n, 0);

        return result;
    }

    public void backTrace(int n, int row) {
        if(row == n) {
            // 找到一种摆放方式
            List<String> temp = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < n; j++) {
                    if(j == queen[i]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                temp.add(sb.toString());
            }
            result.add(temp);

            return;
        }

        for(int col = 0; col < n; col++) {
            if(!underAttack(n, row, col)) {
                placeQueen(n, row, col);

                // 继续放下一行
                backTrace(n, row+1);

                removeQueen(n, row, col);
            }
        }
    }

    // 放置棋子
    public void placeQueen(int n, int row, int col) {
        queen[row] = col;
        cols[col] = 1;
        diagnols1[row - col + n] = 1;
        diagnols2[row + col] = 1;
    }

    // 移除棋子
    public void removeQueen(int n, int row, int col) {
        cols[col] = 0;
        diagnols1[row - col + n] = 0;
        diagnols2[row + col] = 0;
    }

    // 检查，列，正对角线，负对角线是否处于攻击位置
    public boolean underAttack(int n, int row, int col) {
        return cols[col] == 1 || diagnols1[row - col + n] == 1 || diagnols2[row + col] == 1;
    }
}
```

### 深度优先

[98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/submissions/)

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        // 中序遍历
        if(root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;

        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            if(pre != null && pre.val >= cur.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }

        return true;
    }
}
```

[129. 求根到叶子节点数字之和](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)

给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

```java
class Solution {
    List<Integer> list = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }

        dfs(root, 0);

        int result = 0;

        for(Integer i : list) {
            result += i;
        }

        return result;
    }

    private void dfs(TreeNode node, Integer i) {
        i = i * 10 + node.val;

        if(node.left == null && node.right == null) {
            list.add(i);
            return;
        }
        
        if(node.left != null) {
            dfs(node.left, i);
        }

        if(node.right != null) {
            dfs(node.right, i);
        }
    }
}
```

[130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/submissions/)

给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X

X O O X

X X O X

X O X X

运行你的函数后，矩阵变为：

X X X X

X X X X

X X X X

X O X X

```java
class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int row = board.length;
        int col = board[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                boolean edge = i == 0 || i == row - 1 || j == 0 || j == col - 1;

                if (edge && board[i][j] == 'O') {
                    dfs(board, i, j, row, col);
                }
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    public void dfs(char[][] board, int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col) {
            // 超过边界
            return;
        }
        if (board[i][j] == 'X' || board[i][j] == '#') {
            // 是X或者已经访问过
            return;
        }

        board[i][j] = '#';

        dfs(board, i-1, j, row, col);
        dfs(board, i+1, j, row, col);
        dfs(board, i, j-1, row, col);
        dfs(board, i, j+1, row, col);
    }
}
```

### 广度优先

[102. 二叉树的层次遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/submissions/)

给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        
        List<Integer> temp = new ArrayList<>();

        queue.offer(root);
        int size = queue.size();

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            temp.add(cur.val);
            size--;

            if (cur.left != null) {
                queue.offer(cur.left);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            }

            if (size == 0) {
                result.add(new ArrayList<>(temp));
                temp.clear();
                size = queue.size();
            }
        }

        return result;
    }
}
```

### 动态规划

#### 矩阵类

[62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？

```java
class Solution {
    public int uniquePaths(int m, int n) {
        // dp[i][j] 表示从起点到(i,j) 一共有多少条路径
        // 所以可以推出 dp[i][j] = dp[i-1][j]（向右） + dp[i][j-1]（向下）

        int[][] dp = new int[m][n];

        // 初始化, i=0 || j=0 都只有一条路径
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
```

[63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // dp[i][j] 依然表示从起点到（i,j）有几种路径
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        // 初始化，对于j=0如果有一个障碍，后面的都为0
        for (int i = 1; i < m; ++i) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        }

        // 同理，对于i=0如果有一个障碍，后面也都为0
        for (int i = 1; i < n; ++i) {
            dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : dp[0][i - 1];
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
```

[64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/submissions/)

给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

```java
class Solution {
    public int minPathSum(int[][] grid) {
        // dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1])
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];

        // 初始化
        for (int i = 1; i < row; i++) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }

        for (int i = 1; i < col; i++) {
            dp[0][i] = grid[0][i] + dp[0][i-1];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[row-1][col-1];
    }
}
```

[221. 最大正方形](https://leetcode-cn.com/problems/maximal-square/)

在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int max = 0;

        // flag(i,j) 表示的是由 1 组成的最大正方形的边长；
        // 关键公式:flag(i, j)=min(flag(i−1, j), flag(i−1, j−1), flag(i, j−1))+1
        int[][] flag = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    flag[i][j] = matrix[i][j] == '1' ? 1 : 0;
                    max = Math.max(max, flag[i][j]);
                    continue;
                }
                if (matrix[i][j] == '1') {
                    flag[i][j] = Math.min(Math.min(flag[i - 1][j], flag[i - 1][j - 1]), flag[i][j - 1]) + 1;
                    max = Math.max(max, flag[i][j]);
                }
            }
        }

        return max * max;
    }
}
```

[120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // dp[i][j] 表示最后一行元素到[i][j]的最小路径和
        // dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
        // 初始化i=n-1时 dp[i][j] = triangle[i][j];
        int[][] dp = new int[n][n];

        for (int j = 0; j < n; j++) {
            List<Integer> list = triangle.get(n-1);
            dp[n-1][j] = list.get(j);
        }

        for(int i = n-2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for(int j = 0; j < i+1; j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + list.get(j);
            }
        }

        return dp[0][0];
    }
}
```

#### 序列类

[300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

给定一个无序的整数数组，找到其中最长上升子序列的长度。

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i] 表示从0～i最长的子序列长度
        // dp[i] = Math.max(dp[j]+1, dp[i]) j < i
        // 其中dp[j]需要小于dp[i]

        int n = nums.length;

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);   
                }
            }
            // 返回结果是所有dp[i]中最大的那个，但不一定是dp[n-1]
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
```

[198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/submissions/)

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

```java
class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        
        // dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i])
        int[] dp = new int[length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }

        return dp[length-1];
    }
}
```

#### 字符串匹配类

[5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：

输入: "cbbd"
输出: "bb"

```java
class Solution {
    public String longestPalindrome(String s) {
        // 参考题解，动态规划 https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
        
        if (s == null || s.length() == 0) {
            return s;
        }

        // 动态规划
        // 定义状态 dp[i][j] 表示 i~j 之间的子串是否是回文串
        // 定义状态转移方程 dp[i][j] = s[i]==s[j] && (j -i < 3 || dp[i+1][j-1])
        // 关键在这里：[i + 1, j - 1] 一定至少有 2 个元素才有判断的必要
        // 因为如果 [i + 1, j - 1] 只有一个元素，不用判断，一定是回文串
        // 如果 [i + 1, j - 1] 表示的区间为空，不用判断，也一定是回文串
        // [i + 1, j - 1] 一定至少有 2 个元素 等价于 (j - 1) - (i + 1) >= 1，即 j - i >=  3

        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int max = 0;
        String result = s.substring(0,1);

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    int length = j - i + 1;
                    if (length > max) {
                        max = length;
                        result = s.substring(i,j+1);
                    }
                }
            }
        }

        return result;

    }

    // public String longestPalindrome(String s) {
    //     if (s == null || s.length() < 1) return "";
    //     int start = 0, end = 0;
    //     for (int i = 0; i < s.length(); i++) {
    //         int len1 = expandAroundCenter(s, i, i);
    //         int len2 = expandAroundCenter(s, i, i + 1);
    //         int len = Math.max(len1, len2);
    //         if (len > end - start) {
    //             start = i - (len - 1) / 2;
    //             end = i + len / 2;
    //         }
    //     }
    //     return s.substring(start, end + 1);
    // }

    // private int expandAroundCenter(String s, int left, int right) {
    //     int L = left, R = right;
    //     while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
    //         L--;
    //         R++;
    //     }
    //     return R - L - 1;
    // }

}
```

[1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)

给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。

若这两个字符串没有公共子序列，则返回 0。

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 参考 https://blog.csdn.net/kexuanxiu1163/article/details/103257938
        // 我们要求解 str1(0,…m) 和 str2(0,…n) 的最长公共子序列，如果这是最终要求解的问题，那么它的子问题是什么呢？
        // 其实是 str1(0,…m-1) 和 str2(0,…n-1)，以及 str1(0,…m-1) 和 str2(0,…n)，还有 str1(0,…m) 和 str2(0,…n-1)，
        // 如果要找它们之间的关系，那我们需要思考一个问题，这些子问题怎么变成最终要求解的问题，当前的问题考虑当前字符是否相等，很直接的一个发现就是，
        // 如果 str1(m)==str2(n)，那么我们就可以将子问题中的 str1(0,…m-1) 和 str2(0,…n-1) 后面添加两个相同字符递进成当前问题；
        // 如果不相等，我们就需要考虑在三个子问题中选择一个较大值了。

        // dp[i][j] 表示 str1(0,…i) 和 str2(0,…j) 的最长公共子序列长度

        // 如果 str1(i) != str2(j):
        // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
        // 如果 str1(i) == str2(j):
        // dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] + 1)
        // 因为 dp[i - 1][j - 1] + 1 >= dp[i - 1][j] && dp[i - 1][j - 1] + 1 >= dp[i][j - 1]
        // 所以第二项可以化简：
        // 如果 str1(i) == str2(j):
        // dp[i][j] = dp[i - 1][j - 1] + 1

        // dp[i][j] = str1.charAt(i-1) == str2.charAt(j-1) ? dp[i-1][j-1] + 1 : Max(dp[i-1][j], dp[i][j-1])        

        int n1 = text1.length();
        int n2 = text2.length();

        // 通常来说字符相关的问题可以把状态数组多开一格用来存放空串匹配的情况，这道题空串的情况答案都是 0，使用 Java 语言也不需要考虑初始化
        int[][] dp = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j =1; j <= n2; j++) {
                dp[i][j] = text1.charAt(i-1) == text2.charAt(j-1) ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[n1][n2];
    }
}
```

[72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/submissions/)

给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符

```java
class Solution {
    public int minDistance(String word1, String word2) {
        // 参考 https://blog.csdn.net/kexuanxiu1163/article/details/103257938
        // 当我们在比较 str1(m) 和 str2(n) 的时候也会有两种结果，即 相等 或 不相等，如果说是 相等，那其实我们就不需要考虑这两个字符，问题就直接变成了子问题 str1(0…m-1) 通过多少 cost 变成 str2(0…n-1)，如果说 不相等，那我们就可以执行题目给定的三种变换策略:

        // 将问题中的 str1 末尾字符 str1(m) 删除，因此只需要考虑子问题 str1(0…m-1)，str2(0…n)

        // 将问题中的 str1 末尾字符 str1(m) 替换 成 str2(n)，这里我们就只需要考虑子问题 str1(0…m-1)，str2(0…n-1)

        // 将问题中的 str1 末尾 添加 一个字符 str2(n)，添加后 str1(m+1) 必定等于 str2(n)，所以，我们就只需要考虑子问题 str1(0…m)，str2(0…n-1)
        // dp[i][j] = word1.charAt(i) == word2.charAt(j) ? dp[i-1][j-1] : Math.min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1;
    
        int n1 = word1.length();
        int n2 = word2.length();

        int[][] dp = new int[n1+1][n2+1];

        // 这里有一个初始化，就是当一个字符串是空串的时候，转化只能通过添加元素或是删除元素来达成，那这里状态数组中存的值其实是和非空字符串的字符数量保持一致。
        for (int i = 0; i <= n1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i <= n2; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                }
            }
        }

        return dp[n1][n2];
    }
}
```

(44. 通配符匹配)[https://leetcode-cn.com/problems/wildcard-matching/submissions/]

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

```java
class Solution {
    public boolean isMatch(String s, String p) {
        // 参考 https://blog.csdn.net/kexuanxiu1163/article/details/103257938
        // pattern(m) == str(n):问题拆解成看子问题 pattern(0...m-1) 和 str(0...n-1) 是否匹配
        // pattern(m) == ?:问题拆解成看子问题 pattern(0...m-1) 和 str(0...n-1) 是否匹配
        // pattern(m) == *:pattern(m) == *:可以匹配空串、以及任意多个字符
        // 当 * 匹配空串时：问题拆解成看子问题 pattern(0…m-1) 和 str(0…n) 是否匹配
        // 当 * 匹配任意字符时：问题拆解成看子问题 pattern(0…m) 和 str(0…n-1) 是否匹配
        // 这里解释一下，匹配任意多个字符意味着之前的子问题也可以使用当前的，也就是用 pattern(m) 来进行匹配

        // 所以得出递推公式
        // pattern(i) == str(j) || pattern(j) == ? : dp[i][j] = dp[i-1][j-1]
        // pattern(m) == * : dp[i][j] = dp[i-1][j] || dp[i][j-1]

        // 初始化要注意 如果patten(i=0)为空，只有str为空才匹配 dp[0][0] = true
        // 如果str(j=0)为空，只有patten所有字符都是*才匹配（因为*可以匹配空串），一旦有一个不是*，后面的都不会匹配
        
        int n1 = p.length();
        int n2 = s.length();

        boolean[][] dp = new boolean[n1+1][n2+1];
        dp[0][0] = true;

        for(int i = 1; i <= n1; i++) {
            if (p.charAt(i-1) == '*') {
                dp[i][0] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(i-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }

        return dp[n1][n2];
    }
}
```