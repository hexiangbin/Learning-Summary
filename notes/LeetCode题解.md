* [数据结构相关问题](#数据结构相关问题)
    * [数组与字符串](#数组与字符串)
    * [队列](#)
    * [二叉树](#)
    * [查找表](#)
    * [链表](#链表)

# LeetCode题解 #

## 数据结构相关问题 ##

### 数组与字符串 ###

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
