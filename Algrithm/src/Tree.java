public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
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

    public static void main(String[] args) {
        Integer[] data = {1,2,2,3,3,null,null,4,4};
        TreeNode[] nodes = new TreeNode[data.length];
        for(int i=0; i<data.length; i++){
            nodes[i] = data[i]==null ? null : new TreeNode(data[i].intValue());
        }
        createTreeAsLevel(nodes);
        TreeNode root = nodes[0];

        System.out.println(new Tree().isBalanced(root));
    }

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

        return Math.abs(left - right) < 2 ? Math.abs(left - right) + 1 : -1;
    }



}
