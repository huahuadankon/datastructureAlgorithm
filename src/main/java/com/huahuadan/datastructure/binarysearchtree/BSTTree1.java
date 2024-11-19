package com.huahuadan.datastructure.binarysearchtree;

import com.huahuadan.datastructure.binarytree.TreeNode;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/19 19:03
 * @description 二叉搜索树
 */
public class BSTTree1 {
    BSTNode root; // 根节点

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * <h3>查找关键字对应的值</h3>
     *
     * @param key 关键字
     * @return 关键字对应的值
     */
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * <h3>查找最小关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public Object min() {
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     * <h3>查找最大关键字对应值</h3>
     *
     * @return 关键字对应的值
     */
    public Object max() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     * <h3>存储关键字和对应值</h3>
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(int key, Object value) {
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            parent = node;
            if (key < node.key) {
                node = node.left;
            }else if (key > node.key) {
                node = node.right;
            }else {
                // 找到key 更新
                node.value = value;
                return;
            }
        }
        //没有找到，新增
        if(parent == null) {//相当于树为空一次都没有更新
            root = new BSTNode(key, value);
            return;
        }
        if(key< parent.key){
            parent.left = new BSTNode(key, value);
        }else {
            parent.right = new BSTNode(key, value);
        }
    }


    //递归实现
    private BSTNode doPut(BSTNode node, int key, Object value) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else if (node.key < key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }
    /*public void put(int key, Object value) {
        root = doPut(root, key, value);
    }*/
    /**
     * <h3>查找关键字的前任值</h3>
     *
     * @param key 关键字
     * @return 前任值
     */
    public Object predecessor(int key) {
        BSTNode node = root;
        BSTNode ancestorFromLeft = null;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            }else if (key > node.key) {
                ancestorFromLeft = node; //向右走，说明是自左而来
                node = node.right;
            }else {
                break;
            }
        }
        //没有对应的节点
        if(node == null) {
            return null;
        }
        //如果有左子树，前驱节点是左子树的最大值
        if(node.left!=null) {
            return max(node.left);
        }
        // 没有左子树，则前驱节点是最近的一个自左而来的祖先节点
        return ancestorFromLeft!=null?ancestorFromLeft.value:null;

    }

    /**
     * <h3>查找关键字的后任值</h3>
     *
     * @param key 关键字
     * @return 后任值
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                break;
            }
        }
        // 没找到节点
        if (p == null) {
            return null;
        }
        // 找到节点 情况1：节点有右子树，此时后任就是右子树的最小值
        if (p.right != null) {
            return min(p.right);
        }
        // 找到节点 情况2：节点没有右子树，若离它最近的、自右而来的祖先就是后任
        return ancestorFromRight != null ?
                ancestorFromRight.value : null;
    }





}
