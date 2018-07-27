* [1.二维数组中的查找](#1-二维数组中的查找)
* [2.替换空格](#2-替换空格)
* [3.从尾到头打印链表](#3-从尾到头打印链表)
* [4.重建二叉树](#4-重建二叉树)
* [5.用两个栈实现队列](#5-用两个栈实现队列)
* [6.旋转数组的最小数字](#6-旋转数组的最小数字)
* [7.斐波那契数列](#7-斐波那契数列)
* [8.跳台阶](#8-跳台阶)
* [9.变态跳台阶](#9-变态跳台阶)
* [10.矩形覆盖](#10-矩形覆盖)
* [11.二进制中1的个数](#11-二进制中1的个数)
* [12.数值的整数次方](#12-数值的整数次方)
* [13.调整数组顺序使奇数位于偶数前面](#13-调整数组顺序使奇数位于偶数前面)
* [14.链表中倒数第k个结点](#14-链表中倒数第k个结点)
* [6.旋转数组的最小数字](#6-旋转数组的最小数字)
* [6.旋转数组的最小数字](#6-旋转数组的最小数字)
* [6.旋转数组的最小数字](#6-旋转数组的最小数字)

# 1-二维数组中的查找

[NowCoder](https://www.nowcoder.com/practice/abc3fe2ce8e146608e868a70efebf62e?tpId=13&tqId=11154&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking)

## 题目描述

在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
```java
public class Solution {
    public boolean Find(int target, int [][] array) {
		int row=array.length-1;
		int col=0;//从数组左下角出发
		while(row>=0 && col<array[0].length){
			if(array[row][col]<target){
				col++;
			}else if(array[row][col]>target){
				row--;
			}else{
				return true;
			}
		}
		return false;
    }
}
```
# 2-替换空格

[NowCoder](https://www.nowcoder.com/practice/4060ac7e3e404ad1a894ef3e17650423?tpId=13&tqId=11155&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
    	String string=str.toString();
    	char[] c=string.toCharArray();
    	StringBuffer sb=new StringBuffer();
    	for(int i=0;i<c.length;i++){
    		if(c[i]==' '){
    			sb.append("%20");
    		}else{
    			sb.append(c[i]);
    		}
    	}
		return sb.toString();
    }
}
```
# 3-从尾到头打印链表

[NowCoder](https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
```java
public class Solution {
    ArrayList<Integer> arrayList=new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode!=null){
            printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
}  
```
# 4-重建二叉树

[NowCoder](https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
```java
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
   		TreeNode root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    private TreeNode reConstructBinaryTree(int[] pre,int startPre,int endPre,int[] in ,int startIn,int endIn){
        if(startPre>endPre||startIn>endIn)return null;//递归结束条件：没有元素
        TreeNode root=new TreeNode(pre[startPre]);
        for(int i=startIn;i<=endIn;i++){
            if(in[i]==root.val){
                //根据root元素把pre和in分成左右两部分，左边是root的左子树，右边是root的右子树，继续递归求解还原整个二叉树
                //in[startin,i-1]左,i根,in[i+1,endin]右
                //startpre根,pre[startpre+1,startpre+i-startin]左,pre[startpre+i-startin+1,endpre]右
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in ,startIn,i-1);
                root.right=reConstructBinaryTree(pre,startPre+i-startIn+1,endPre,in ,i+1,endIn);
            }
        }
        return root;
    }
   
}
```
# 5-用两个栈实现队列

[NowCoder](https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=13&tqId=11158&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
```java
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        int a;
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                a=stack1.pop();
                stack2.push(a);
                
            }
        }
    	a=stack2.pop();
     
        return a;
    }
}
```
# 6-旋转数组的最小数字

[NowCoder](https://www.nowcoder.com/practice/9f3231a991af4f55b95579b44b7a01ba?tpId=13&tqId=11159&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
```java
public class Solution {
    public int minNumberInRotateArray(int [] array) {
    	if(array.length==0)return 0;
        int bottom=0;
        int top=array.length-1;
        
        while(bottom<top){
        int mid=bottom+(top-bottom)/2;
        	if(array[mid]>array[top]){
            	bottom=mid+1;
        	}else if(array[mid]==array[top]){
            	top--;
       		}else{
            	top=mid;
        	}
        }
        return array[bottom];
    }
}
```
# 7-斐波那契数列

[NowCoder](https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=13&tqId=11160&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述

大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
```java
public class Solution {
    public int Fibonacci(int n) {
		if(n<0||n>39)return -1;
        if(n<=1)return n;
        int[] arr=new int[n+1];
        arr[0]=0;
        arr[1]=1;
        for(int i=2;i<n+1;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr[n];
    }
}
```
# 8-跳台阶

[NowCoder](https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=13&tqId=11161&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
```java
public class Solution {
    public int JumpFloor(int target) {
		if(target==1)return 1;
        else if(target==2)return 2;
        else if(target<0)return 0;
        else return JumpFloor(target-1)+JumpFloor(target-2);
    }
}
```
# 9-变态跳台阶

[NowCoder](https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&tqId=11162&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
```java
public class Solution {
    public int JumpFloorII(int target) {
        if(target==1)return 1;
		if(target==2)return 2;
		int[] arr=new int[target];
        arr[0]=1;
        arr[1]=2;
		for(int i=2;i<arr.length;i++){
			arr[i]=2*arr[i-1];
		}
		return arr[target-1];
    }
}
```
# 10-矩形覆盖

[NowCoder](https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=11163&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
我们可以用2x1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2x1的小矩形无重叠地覆盖一个2xn的大矩形，总共有多少种方法？
```java
public class Solution {
    public int RectCover(int target) {
        if(target==0)return 0;
		if(target==1)return 1;
		if(target==2)return 2;
		int[] arr=new int[target];
        arr[0]=1;
        arr[1]=2;
		for(int i=2;i<arr.length;i++){
			arr[i]=arr[i-1]+arr[i-2];
		}
		return arr[target-1];
    }
}
```
# 11-二进制中1的个数

[NowCoder](https://www.nowcoder.com/practice/8ee967e43c2c4ec193b040ea7fbb10b8?tpId=13&tqId=11164&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
```java
public class Solution {
    public int NumberOf1(int n) {
		int count=0;
        while(n!=0){
            count++;
            n=n&(n-1);
        }
        return count;
    }
}
```
# 12-数值的整数次方

[NowCoder](https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00?tpId=13&tqId=11165&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
```java
public class Solution {
    public double Power(double base, int exponent) {
        Double res=1.0;
        if(exponent>=0){
        for(int i=0;i<exponent;i++){
            res=res*base;
        }
        return res;
        }else{
            for(int i=0;i<-exponent;i++){
            res=res*base;
        }
        	return 1.0/res;
        }
  }
}
```
# 13-调整数组顺序使奇数位于偶数前面

[NowCoder](https://www.nowcoder.com/practice/beb5aa231adc45b2a5dcc5b62c93f593?tpId=13&tqId=11166&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
```java
import java.util.ArrayList;
public class Solution {
   public void reOrderArray(int [] array) {
      int odd=0;
       for(int val:array){
           if(val%2==1)odd++;
       }
       int[] res=array.clone();
       int i=0,j=odd;
       for(int val:res){
           if(val%2==1){
               array[i++]=val;
           }else{
               array[j++]=val;
           }
       }
    }
}
```
# 14-链表中倒数第k个结点

[NowCoder](https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&tqId=11167&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个链表，输出该链表中倒数第k个结点。
```java
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
		ListNode p1=head;
        ListNode p2=head;
        if(head==null||k<=0) return null;
        for(int i=1;i<k;i++){
            if(p1.next!=null){
                p1=p1.next;
            }
            else {
                return null;}
        }
        while(p1.next!=null){
            p1=p1.next;
            p2=p2.next;
        }
        return p2;
    }
}
```
