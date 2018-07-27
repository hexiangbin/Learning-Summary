<div>
    <table border="0">
	  <tr>
	    <th>一</th>
	    <th>二</th>
            <th>三</th>
	    <th>四</th>
	    <th>五</th>
	    <th>六</th>
	    <th>七</th>
	    <th>八</th>
	    <th>九</th>
	  </tr>
	  <tr>
	    <td>算法</td>
	    <td>Java</td>
	    <td>操作系统</td>
	    <td>数据库</td>
	    <td>计算机网络</td>
	    <td>开发框架</td>
	    <td></td>
	    <td></td>
	    <td></td>
	  </tr>
    </table>
</div>

## 算法 :pencil2:

> [Leetcode 题解](https://github.com/hexiangbin/Learning-Summary/blob/master/notes/LeetCode%E9%A2%98%E8%A7%A3.md)

对目前所做的题目总结，不定时更新。

> [剑指 Offer 题解](https://github.com/hexiangbin/Learning-Summary/blob/master/notes/%E5%89%91%E6%8C%87offer%E9%A2%98%E8%A7%A3.md)

依照原书第二版顺序。




## LeetCode题解 ##

### 数组问题 ###
	283. 移动零
	给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

	示例:

	输入: [0,1,0,3,12]
	输出: [1,3,12,0,0]
	说明:

	1.必须在原数组上操作，不能拷贝额外的数组。
	2.尽量减少操作次数。
    
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
	
	
[我的github地址](https://github.com/hexiangbin/algorithm)
