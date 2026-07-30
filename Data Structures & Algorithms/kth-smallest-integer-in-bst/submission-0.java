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
    int count = 0;
    int result = -1;
    public int kthSmallest(TreeNode root, int k) {
        kthSmallestValue(root, k);
        return result;
    }

    public void kthSmallestValue(TreeNode root, int k){
        if(root == null) return;
     
        kthSmallestValue(root.left, k);
        count++;

        if(count == k){
            result = root.val;
        }

        kthSmallestValue(root.right, k);
    }
}
