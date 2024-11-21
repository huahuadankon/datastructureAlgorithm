package com.huahuadan.algorithm.binarysearchtree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/21 20:26
 * @description 查找二叉搜索树最近的公共祖先
 */
public class LeetCode235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果根节点为空，直接返回 null
        if (root == null) {
            return null;
        }

        // 如果 p 和 q 分布在 root 两侧，或者其中一个等于 root，则 root 就是最近公共祖先
        if ((p.val <= root.val && q.val >= root.val) || (p.val >= root.val && q.val <= root.val)) {
            return root;
        }

        // 如果 p 和 q 都在左子树，递归查找左子树
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // 如果 p 和 q 都在右子树，递归查找右子树
        return lowestCommonAncestor(root.right, p, q);
    }
    //非递归实现
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (ancestor.val > p.val && ancestor.val > q.val ||
                ancestor.val < p.val && ancestor.val < q.val) {
            if (ancestor.val > p.val) {
                ancestor = ancestor.left;
            } else {
                ancestor = ancestor.right;
            }
        }
        return ancestor;
    }

}
