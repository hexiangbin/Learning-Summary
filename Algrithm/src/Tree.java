public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }



    public static void main(String[] args) {
        Integer[] data = {-2147483648};

        TreeNode root = formatTree(data);

        System.out.println(new Tree().isValidBST(root));
    }

    private static TreeNode formatTree(Integer[] data) {
        TreeNode[] nodes = new TreeNode[data.length];
        for(int i=0; i<data.length; i++){
            nodes[i] = data[i] == null ? null : new TreeNode(data[i]);
        }
        createTreeAsLevel(nodes);

        return nodes[0];
    }

    private static void createTreeAsLevel(TreeNode[] nodes){
        int n = nodes.length;
        int flag = 0;//按层构造时一个值为null，则它之后的孩子序号应该相应减2flag
        for(int i=0; 2*i+1 < n-1; i++){
            if(nodes[i] != null){
                nodes[i].left = nodes[2*i+1-2*flag];
                nodes[i].right = 2*i+2 == n ? null : nodes[2*i+2-2*flag];
            }else{
                flag++;
            }
        }
        System.out.println();
    }

    private boolean valid = true;
    private Integer pre = null;
    public boolean isValidBST(TreeNode root) {
        inOrder(root);

        return valid;
    }

    // 利用中序遍历本身有序的特点
    public void inOrder(TreeNode root) {
        if (!valid || root == null) {
            return;
        }

        inOrder(root.left);

        if (valid) {
            // 如果当前还是有效的继续判断
            valid = pre == null ? true : root.val > pre;
            pre = root.val;
        } else {
            // 如果已经失效直接返回
            return;
        }

        inOrder(root.right);
    }



}
