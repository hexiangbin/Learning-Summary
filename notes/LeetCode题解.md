* [数据结构相关问题](#数据结构相关问题)
    * [数组](#数组)
    * [队列](#)
    * [字符串](#)
    * [查找表](#)
    * [链表](#)

# LeetCode题解 #

## 数据结构相关问题 ##

### 数组 ###

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
