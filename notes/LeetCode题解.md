* [数据结构相关问题](#数据结构相关问题)
    * [数组与字符串](#数组与字符串)
    * [队列](#)
    * [二叉树](#)
    * [查找表](#)
    * [链表](#)

# LeetCode题解 #

## 数据结构相关问题 ##

### 数组与字符串 ###

[283. 移动零](https://leetcode-cn.com/problems/move-zeroes/description/)


题目描述：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

输入: [0,1,0,3,12]

输出: [1,3,12,0,0]

说明:

1.必须在原数组上操作，不能拷贝额外的数组。

2.尽量减少操作次数。

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

题目描述：

给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

示例 1:

给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 

你不需要考虑数组中超出新长度后面的元素。

示例 2:

给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

你不需要考虑数组中超出新长度后面的元素。
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

题目描述：

给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:

给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。

示例 2:

给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
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

题目描述：

在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2

输出: 5

示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4

输出: 4

说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
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

题目描述：

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]

输出: [0,0,1,1,2,2]
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

题目描述：

给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:

返回的下标值（index1 和 index2）不是从零开始的。

你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。

示例:

输入: numbers = [2, 7, 11, 15], target = 9

输出: [1,2]

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

题目描述：

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

输入: "A man, a plan, a canal: Panama"

输出: true

示例 2:

输入: "race a car"

输出: false
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
