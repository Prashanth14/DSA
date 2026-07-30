/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    int res = 0;
    public int goodNodes(TreeNode root) {
       return dfs(root, Integer.MIN_VALUE);
    }

    public int dfs(TreeNode root, int max){
         if(root == null) return res;
        
       if(root.val >= max){
        res++;
        max = root.val;
       }
       dfs(root.left, max);
       dfs(root.right, max);
       return res;
    }
}
