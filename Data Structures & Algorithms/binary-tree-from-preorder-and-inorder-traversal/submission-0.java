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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preEnd = preorder.length-1, inEnd = inorder.length-1;
        Map<Integer, Integer> map = new HashMap<>();
      
        for(int i = 0; i <= inEnd; i++){
            map.put(inorder[i], i);
        }

        return buildTreeFromPreInorder(preorder, 0, preEnd, inorder, 0, inEnd, map);
    }

    public TreeNode buildTreeFromPreInorder(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer>map){
          if(inStart > inEnd || preStart > preEnd) return null;

          TreeNode root = new TreeNode(preorder[preStart]);

          int inRoot = map.get(root.val);
          int numsLeft = inRoot - inStart;

          root.left = buildTreeFromPreInorder(preorder, preStart+1, preStart + numsLeft, inorder, inStart, inRoot-1, map);
          root.right = buildTreeFromPreInorder(preorder, preStart+numsLeft+1, preEnd, inorder, inRoot+1, inEnd, map);

          return root;
    }
}
