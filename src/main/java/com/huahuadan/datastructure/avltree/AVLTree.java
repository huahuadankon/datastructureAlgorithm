package com.huahuadan.datastructure.avltree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/22 19:05
 * @description AVL 平衡二叉搜索树的实现
 */
public class AVLTree {
    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1; // 高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    //求节点的高度
    public int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }
    //更新节点的高度(删除，新增，旋转)
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }
    // 平衡因子 (balance factor) = 左子树高度-右子树高度  1 -1 0
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }
    // 参数：要旋转的节点, 返回值：新的根节点
    private AVLNode rightRotate(AVLNode red) {
    /*
            red                             yellow
           /   \         右旋             /     \
      yellow   C   --------->         A      red
       /   \                               /   \
      A     B                             B     C
    */
        AVLNode yellow = red.left; // yellow 是 red 的左子节点
        AVLNode green = yellow.right; // green 是 yellow 的右子节点
        yellow.right = red; // yellow 变为新的根节点，red 成为 yellow 的右子节点
        red.left = green; // green 变为 red 的左子节点
        updateHeight(red); // 更新 red 节点的高度
        updateHeight(yellow); // 更新 yellow 节点的高度
        return yellow; // 返回新的根节点 yellow
    }

    // 参数：要旋转的节点, 返回值：新的根节点
    private AVLNode leftRotate(AVLNode red) {
    /*
          red                             yellow
         /   \          左旋             /     \
        A    yellow  --------->       red      C
            /   \                  /   \
           B     C                A     B
    */
        AVLNode yellow = red.right; // yellow 是 red 的右子节点
        AVLNode green = yellow.left; // green 是 yellow 的左子节点
        yellow.left = red; // yellow 变为新的根节点，red 成为 yellow 的左子节点
        red.right = green; // green 变为 red 的右子节点
        updateHeight(red); // 更新 red 节点的高度
        updateHeight(yellow); // 更新 yellow 节点的高度
        return yellow; // 返回新的根节点 yellow
    }

    // 先左旋左子树, 再右旋根节点
    private AVLNode leftRightRotate(AVLNode node) {
    /*
         node                           node                          yellow
        /   \         左旋左子树       /   \       右旋根节点        /     \
     red     C    ------------->   yellow   C  ------------->    red     node
    /   \                          /   \                         /  \    /   \
   A    yellow                   red    B                      A    B  C     C
        /   \                   /   \
       B     D                 A     B
    */
        node.left = leftRotate(node.left); // 对左子树进行左旋
        return rightRotate(node); // 对根节点进行右旋
    }

    // 先右旋右子树, 再左旋根节点
    private AVLNode rightLeftRotate(AVLNode node) {
    /*
         node                           node                          yellow
        /   \         右旋右子树       /   \       左旋根节点        /     \
       A    red     ------------->   A   yellow  ------------->   node   red
           /   \                         /   \                  /   \   /   \
       yellow  C                       B     red               A    B  D     C
       /   \                                 /   \
      B     D                               D     C
    */
        node.right = rightRotate(node.right); // 对右子树进行右旋
        return leftRotate(node); // 对根节点进行左旋
    }


    // 检查节点是否失衡, 重新平衡代码
    private AVLNode balance(AVLNode node) {
        if (node == null){
            return null;
        }
        //计算平衡因子
        int bf = bf(node);
        if(bf > 1 && bf(node.left)>=0){ //LL
            return rightRotate(node);
        }else if (bf > 1 && bf(node.left)<0){ // LR
            return leftRightRotate(node);
        }else if(bf < -1 && bf(node.right)<=0){ //RR
            return leftRotate(node);
        }else if (bf < -1 && bf(node.right)>0){ //RL
            return rightLeftRotate(node);
        }
        return node;
    }
    AVLNode root;
    //新增节点
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        //没有找到节点，新建一个
        if (node == null) {
            return new AVLNode(key, value);
        }
        //找到，直接更新
        if(node.key == key){
            node.value = value;
            return node;
        }
        //递归查找
        if(key < node.key){
            node.left = doPut(node.left, key, value);
        }else{
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    /**
     * 删除AVL树中指定键值的节点，并保持树的平衡性。
     *
     * @param node 当前处理的节点
     * @param key  要删除的键值
     * @return 删除后新的子树根节点
     */
    private AVLNode doRemove(AVLNode node, int key) {
        // 如果当前节点为空，直接返回 null（没有找到该键值）
        if (node == null) {
            return null;
        }

        // 如果键值小于当前节点的键值，在左子树中递归删除
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        }
        // 如果键值大于当前节点的键值，在右子树中递归删除
        else if (key > node.key) {
            node.right = doRemove(node.right, key);
        }
        // 找到要删除的节点
        else {
            // 1. 如果该节点是叶子节点（无子节点），直接删除，返回 null
            if (node.left == null && node.right == null) {
                return null;
            }
            // 2. 如果该节点只有右子树，返回右子树作为新的子树根节点
            else if (node.left == null) {
                node = node.right;
            }
            // 3. 如果该节点只有左子树，返回左子树作为新的子树根节点
            else if (node.right == null) {
                node = node.left;
            }
            // 4. 如果该节点同时有左子树和右子树
            else {
                // 找到该节点右子树中的中序后继节点（右子树中键值最小的节点）
                AVLNode successor = node.right;
                while (successor.left != null) {
                    successor = successor.left;
                }

                // 用后继节点替换当前节点的位置
                successor.right = doRemove(node.right, successor.key); // 删除后继节点
                successor.left = node.left; // 将当前节点的左子树赋值给后继节点
                node = successor; // 用后继节点替代当前节点
            }
        }

        // 更新当前节点的高度，保证AVL树的高度正确
        updateHeight(node);

        // 平衡当前节点的子树，保持AVL树的平衡性
        return balance(node);
    }






}
