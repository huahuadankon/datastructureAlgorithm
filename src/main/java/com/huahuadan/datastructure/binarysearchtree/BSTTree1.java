package com.huahuadan.datastructure.binarysearchtree;

import com.huahuadan.datastructure.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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



    public Object remove(int key) {
        // 寻找被删除节点及其父节点
        BSTNode node = root;
        BSTNode parent = null;
        while (node != null) {
            if (key < node.key) {
                parent = node;
                node = node.left;
            } else if (key > node.key) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }
        // 如果没有找到节点，返回 null
        if (node == null) {
            return null;
        }

        // 情况 1：被删除节点没有左孩子或右孩子
        if (node.left == null) {
            shift(parent, node, node.right);
        } else if (node.right == null) {
            shift(parent, node, node.left);
        } else {
            // 情况 2：被删除节点有左右孩子
            // 找到后继节点（右子树的最左节点）
            BSTNode successor = node.right;
            BSTNode sParent = node;
            while (successor.left != null) {
                sParent = successor;
                successor = successor.left;
            }

            // 如果后继节点不是直接相邻
            if (sParent != node) {
                shift(sParent, successor, successor.right);
                successor.right = node.right;
            }

            // 替换被删除节点
            shift(parent, node, successor);
            successor.left = node.left;
        }

        // 返回删除节点的值
        return node.value;
    }

    /**
     * 托孤算法
     * @param parent 待删除节点的父亲
     * @param deleted 删除的节点
     * @param child 删除节点的孩子
     */
    public void shift(BSTNode parent,BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /*// 递归删除节点方法，递归删除最大的好处就是不用去维护待删除节点，子节点，和父节点的复杂逻辑关系
    在递归过程中，每次调用返回的是调整后的子树根节点。即使后继节点不相邻，递归调用会正确删除后继节点并返回调整后的子树，
    最终由上一层递归自动连接起来。
    deleteNode的返回值就是删除后剩下的树根节点
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null; // 树为空或未找到节点
        }

        if (key < root.val) {
            // 递归到左子树
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            // 递归到右子树
            root.right = deleteNode(root.right, key);
        } else {
            // 找到需要删除的节点
            if (root.left == null) {
                // 无左子树，返回右子树
                return root.right;
            } else if (root.right == null) {
                // 无右子树，返回左子树
                return root.left;
            }

            // 有左右子树，找到后继节点（右子树的最小节点）
            TreeNode successor = findMin(root.right);
            // 用后继节点的值替代当前节点
            root.val = successor.val;
            // 删除后继节点
            root.right = deleteNode(root.right, successor.val);
        }
        return root; // 返回更新后的子树根节点
    }*/
    //范围查找
    public List<Object> less(int key) { // key=6
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    // 找 > key 的所有 value
    public List<Object> greater(int key) {
        /*ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key > key) {
                    result.add(pop.value);
                }
                p = pop.right;
            }
        }
        return result;*/

        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    // 找 >= key1 且 <= key2 的所有值
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }





}
