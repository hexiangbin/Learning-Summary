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
* 题号顺序参照牛客网OJ顺序

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
# 15-反转链表

[NowCoder](https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个链表，反转链表后，输出新链表的表头。
```java
public class Solution {
    public ListNode ReverseList(ListNode head) {
		if(head==null||head.next==null) return head;
        ListNode rehead=ReverseList(head.next);
        head.next.next=head;
        head.next=null;
        return rehead;
    }
}
```
# 16-合并两个排序的链表

[NowCoder](https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
```java
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1==null) return list2;
        if(list2==null) return list1;
        if(list1.val<list2.val){
            list1.next=Merge(list1.next,list2);
            return list1;
        }else{
            list2.next=Merge(list1,list2.next);
            return list2;
        }
    }
}
```
# 17-合并两个排序的链表

[NowCoder](https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
```java
public class Solution {
    public boolean HasSubtree(TreeNode root1,TreeNode root2){
		 boolean flag=false;
		//当Tree1和Tree2都不为零的时候，才进行比较。否则直接返回false
		 if(root1!=null && root2!=null){
			//如果找到了对应Tree2的根节点的点
			 if(root1.val==root2.val){
				//以这个根节点为为起点判断是否包含Tree2
				flag=check(root1,root2); 
			 }
			//如果找不到，那么就再去root的左儿子当作起点
			 if(!flag)flag=HasSubtree(root1.left, root2);
			//如果还找不到，那么就再去root的右儿子当作起点
			 if(!flag)flag=HasSubtree(root1.right, root2);
		 }
		 return flag;
	 }
	 public boolean check(TreeNode node1,TreeNode node2){
		 //如果Tree2已经遍历完了都能对应的上，返回true
		 if(node2==null)return true;
		 //如果Tree2还没有遍历完，Tree1却遍历完了。返回false
		 if(node1==null)return false;
		 //如果其中有一个点没有对应上，返回false
		 if(node1.val!=node2.val)return false;
		 //如果根节点对应的上，那么就分别去子节点里面匹配
		 return check(node1.left,node2.left)&&check(node1.right,node2.right);
	 }
}
```
# 18-二叉树的镜像

[NowCoder](https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
操作给定的二叉树，将其变换为源二叉树的镜像。

输入描述:二叉树的镜像定义：源二叉树 

    	    8
    	   /  \
    	  6   10
    	 / \  / \
    	5  7 9 11
    	镜像二叉树
    	    8
    	   /  \
    	  10   6
    	 / \  / \
    	11 9 7  5
```java
public class Solution {
    public void Mirror(TreeNode root) {
        if(root==null)return;
		if(root.left==null && root.right==null)return;
		TreeNode temp;
		temp=root.left;
		root.left=root.right;
		root.right=temp;
		if(root.left!=null)Mirror(root.left);
		if(root.right!=null)Mirror(root.right);
    }
}
```
# 18-顺时针打印矩阵

[NowCoder](https://www.nowcoder.com/practice/9b4c81a02cd34f76be2659fa0d54342a?tpId=13&tqId=11172&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
```java
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix){
		int rows=matrix.length;
		int cols=matrix[0].length;
		if(rows==0 || cols==0)return null;
		ArrayList<Integer> res=new ArrayList<Integer>();
		int start=0;
		while(rows>2*start&& cols>2*start){
			int endX=cols-start-1;
			int endY=rows-start-1;
			 //从左到右打印一行
			for(int i=start;i<=endX;i++){
				res.add(matrix[start][i]);
			}
			//从上到下打印一行
			if(start<endY){
				for(int i=start+1;i<=endY;i++){
					res.add(matrix[i][endX]);
				}
			}
			//从右到左打印一行
			if (start < endX && start < endY) {
				for (int i=endX-1; i>=start; i--) {
					res.add(matrix[endY][i]);
				}
			}
		    //从下到上打印一行
			if (start<endX && start<endY-1) {
				for (int i=endY-1; i>=start+1; i--) {
				     res.add(matrix[i][start]);
				}
			}
			start++;
		}
		return res;
	}
}
```
# 19-包含min函数的栈

[NowCoder](https://www.nowcoder.com/practice/4c776177d2c04c2494f2555c9fcc1e49?tpId=13&tqId=11173&tPage=1&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
```java
import java.util.Stack;

public class Solution {
	private static Stack<Integer> stack=new Stack<>();
	private static Stack<Integer> minStack=new Stack<>();
	
	public static void push(Integer i){
		stack.push(i);
		if(minStack.size()==0 || i<minStack.peek()){
			minStack.push(i);
		}else{
			minStack.push(minStack.peek());
		}
	}
	
	public static void pop(){
		if(stack.size()>0 && minStack.size()>0){
			stack.pop();
			minStack.pop();
		}else{
			return;
		}
	}
	
	public static Integer min(){
		if(stack.size()>0 && minStack.size()>0){
			return minStack.peek();
		}else{
			return null;
		}
	}
}
```
# 20-包含min函数的栈

[NowCoder](https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
```java
public class Solution {
	public boolean IsPopOrder(int [] pushA,int [] popA){
		if(pushA.length==0 || popA.length==0 ||pushA.length!=popA.length)return false;
		Stack<Integer> stack=new Stack<Integer>();
		int index=0;
		for(int i=0;i<pushA.length;i++){
			stack.push(pushA[i]);
			while(!stack.isEmpty()&&stack.peek()==popA[index]){
				stack.pop();
				index++;
			}
		}
		return stack.isEmpty();
	}
}
```
# 21-栈的压入、弹出序列

[NowCoder](https://www.nowcoder.com/practice/d77d11405cc7470d82554cb392585106?tpId=13&tqId=11174&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
```java
public class Solution {
	public boolean IsPopOrder(int [] pushA,int [] popA){
		if(pushA.length==0 || popA.length==0 ||pushA.length!=popA.length)return false;
		Stack<Integer> stack=new Stack<Integer>();
		int index=0;
		for(int i=0;i<pushA.length;i++){
			stack.push(pushA[i]);
			while(!stack.isEmpty()&&stack.peek()==popA[index]){
				stack.pop();
				index++;
			}
		}
		return stack.isEmpty();
	}
}
```
# 22-从上往下打印二叉树

[NowCoder](https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13&tqId=11175&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
从上往下打印出二叉树的每个节点，同层节点从左至右打印。
```java
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root){
		ArrayList<Integer> list=new ArrayList<Integer>();
        if(root==null)return list;
		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()){
			TreeNode node=queue.poll();
			if(node.left!=null)queue.offer(node.left);
			if(node.right!=null)queue.offer(node.right);
			list.add(node.val);
		}
		return list;
	}
}
```
# 23-二叉搜索树的后序遍历序列

[NowCoder](https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13&tqId=11176&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
```java
public class Solution {
	public static boolean VerifySquenceOfBST(int [] sequence){
		if(sequence.length==0)return false;
		int length=sequence.length;
		int root=sequence[length-1];
		int i=0;
		for(;i<length-1;i++){
			if(sequence[i]>root){
				break;
			}
		}
		int j=i;
		for(;j<length-1;j++){
			if(sequence[j]<root)return false;
		}
		boolean left=true;
		if(i>0){
			int[] leftTree=Arrays.copyOfRange(sequence, 0,i);//前闭后开
			left=VerifySquenceOfBST(leftTree);
		}
		boolean right=true;
		if(i<length-1){
			int[] rightTree=Arrays.copyOfRange(sequence,i,length-1);
			right=VerifySquenceOfBST(rightTree);
		}
		return left&&right;
	}
}
```
# 24-二叉树中和为某一值的路径

[NowCoder](https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
```java
public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target){
		ArrayList<ArrayList<Integer>> paths=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> path=new ArrayList<Integer>();
		if(root==null)return paths;
		FindPath(paths,path,root,target);
		return paths;
	}
	public void FindPath(ArrayList<ArrayList<Integer>> paths,ArrayList<Integer> path,TreeNode root,int target){
		path.add(root.val);
		if(root.left==null && root.right==null&&target==root.val){
			paths.add(path);
			return;
		}
		ArrayList<Integer> path2=new ArrayList<Integer>();
		path2.addAll(path);
		if(root.left!=null)FindPath(paths, path, root.left, target-root.val);
		if(root.right!=null)FindPath(paths, path2, root.right, target-root.val);
		
	}
}
```
# 25-复杂链表的复制

[NowCoder](https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tqId=11178&tPage=2&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking)

## 题目描述
输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
<div align="center"> <img src="../pictures/961875_1469289666488_886555C4C4726220976FEF4D3A32FFCD.png"/> </div><br>

```java
/*
*解题思路：
*1、遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
*2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
*3、拆分链表，将链表拆分为原链表和复制后的链表
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null) {
            return null;
        }
        RandomListNode currentNode = pHead;
        //1、复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
        while(currentNode != null){
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            currentNode.next = cloneNode;
            cloneNode.next = nextNode;
            currentNode = nextNode;
        }
         
        currentNode = pHead;
        //2、重新遍历链表，复制老结点的随机指针给新结点，如A1.random = A.random.next;
        while(currentNode != null) {
            currentNode.next.random = currentNode.random==null?null:currentNode.random.next;
            currentNode = currentNode.next.next;
        }
         
        //3、拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = pHead;
        RandomListNode pCloneHead = pHead.next;
        while(currentNode != null) {
            RandomListNode cloneNode = currentNode.next;
            currentNode.next = cloneNode.next;
            cloneNode.next = cloneNode.next==null?null:cloneNode.next.next;
            currentNode = currentNode.next;
        }
         
        return pCloneHead;
    }
}
```
