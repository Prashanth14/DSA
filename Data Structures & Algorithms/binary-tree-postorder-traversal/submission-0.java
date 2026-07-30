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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrdTrav(root, result);
        return result;
    }

    public void postOrdTrav(TreeNode root, List<Integer> result){
        if(root == null){
            return;
        }
        postOrdTrav(root.left, result);
        postOrdTrav(root.right, result);
        result.add(root.val); 
    }
}