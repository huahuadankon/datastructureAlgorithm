package com.huahuadan.datastructure.redblacktree;

/**
 * @author liuyichen
 * @version 1.0
 * @date 2024/11/22 20:14
 * @description <h3>红黑树</h3>
 * 节点颜色： 每个节点是红色或黑色。
 * 根节点是黑色： 树的根节点必须是黑色。
 * 叶节点是黑色： 这里的“叶节点”是指红黑树的 空节点（NIL节点），它们作为占位符存在且被认为是黑色.
 * 红色节点的子节点必须是黑色（无连续红色节点）： 即，红色节点的左右子节点都不能是红色。
 * 从任一节点到其所有叶节点的路径中，黑色节点的数量必须相同： 这被称为 黑高（black height），从根到任一叶节点路径中的黑色节点数是一样的。
 */
public class RedBlackTree {
    enum Color {
        RED, BLACK;
    }

    Node root;

    static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;        // 父节点
        Color color = Color.RED;  // 颜色

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        // 是否是左孩子
        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        // 叔叔
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        // 兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    // 判断红
    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    // 判断黑
    boolean isBlack(Node node) {
//        return !isRed(node);
        return node == null || node.color == Color.BLACK;
    }

    // 右旋操作：将粉色节点 `pink` 向右旋转，黄色节点 `yellow` 成为新的子树根节点。
    private void rightRotate(Node pink) {
    /*
       初始树形：
               parent
                 |
               pink
              /    \
           yellow   C
          /    \
         A      green

       右旋后：
               parent
                 |
              yellow
             /     \
           A       pink
                 /    \
              green    C
    */

        Node parent = pink.parent; // 获取粉色节点 `pink` 的父节点
        Node yellow = pink.left;   // 获取粉色节点的左子节点 `yellow`
        Node green = yellow.right; // 获取黄色节点的右子节点 `green`

        // 更新绿色节点的父节点为粉色节点
        if (green != null) {
            green.parent = pink;
        }

        // 更新黄色节点的右子节点为粉色节点
        yellow.right = pink;

        // 更新黄色节点的父节点为粉色节点的父节点
        yellow.parent = parent;

        // 更新粉色节点的左子节点为绿色节点
        pink.left = green;

        // 更新粉色节点的父节点为黄色节点
        pink.parent = yellow;

        // 如果粉色节点是根节点，则将黄色节点更新为新的根
        if (parent == null) {
            root = yellow;
        }
        // 如果粉色节点是其父节点的左子节点
        else if (parent.left == pink) {
            parent.left = yellow; // 更新父节点的左子节点为黄色节点
        }
        // 如果粉色节点是其父节点的右子节点
        else {
            parent.right = yellow; // 更新父节点的右子节点为黄色节点
        }
    }

    // 左旋操作：将粉色节点 `pink` 向左旋转，黄色节点 `yellow` 成为新的子树根节点。
    private void leftRotate(Node pink) {
    /*
       初始树形：
               parent
                 |
               pink
              /    \
             A    yellow
                 /    \
             green     C

       左旋后：
               parent
                 |
              yellow
             /     \
          pink       C
         /   \
        A    green
    */

        Node parent = pink.parent; // 获取粉色节点 `pink` 的父节点
        Node yellow = pink.right;  // 获取粉色节点的右子节点 `yellow`
        Node green = yellow.left;  // 获取黄色节点的左子节点 `green`

        // 更新绿色节点的父节点为粉色节点
        if (green != null) {
            green.parent = pink;
        }

        // 更新黄色节点的左子节点为粉色节点
        yellow.left = pink;

        // 更新黄色节点的父节点为粉色节点的父节点
        yellow.parent = parent;

        // 更新粉色节点的右子节点为绿色节点
        pink.right = green;

        // 更新粉色节点的父节点为黄色节点
        pink.parent = yellow;

        // 如果粉色节点是根节点，则将黄色节点更新为新的根
        if (parent == null) {
            root = yellow;
        }
        // 如果粉色节点是其父节点的左子节点
        else if (parent.left == pink) {
            parent.left = yellow; // 更新父节点的左子节点为黄色节点
        }
        // 如果粉色节点是其父节点的右子节点
        else {
            parent.right = yellow; // 更新父节点的右子节点为黄色节点
        }
    }

    /**
     * 新增或更新节点到红黑树
     * 1. 如果键已存在，则更新对应的值。
     * 2. 如果键不存在，按红黑树的规则插入节点，并调整以维持红黑树性质。
     *
     * @param key   要插入或更新的键
     * @param value 要插入或更新的值
     */
    public void put(int key, Object value) {
        Node p = root;
        Node parent = null;//父节点
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {
                p.value = value;
                return;
            }
        }
        Node inserted = new Node(key, value);
        if (parent == null) {//树为空
            root = inserted;
        }
        if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }
        // 修复红红冲突
        fixRedRed(inserted);
    }

    /**
     * 修复红红冲突，确保红黑树的平衡和性质不被破坏。
     * 1. 通过颜色调整和旋转，解决新插入节点导致的红红相邻问题。
     *
     * @param x 新插入的节点
     */
    void fixRedRed(Node x) {
        // 情况1：插入的是根节点，直接将其涂黑
        if (x == root) {
            x.color = Color.BLACK;
            return;
        }
        // 情况2：父节点为黑色，无需调整，红黑树性质未被破坏
        if (isBlack(x.parent)) {
            return;
        }
        // 情况3：父节点和叔叔节点都是红色
        // 处理方式：将父节点和叔叔节点涂黑，祖父节点涂红，对祖父递归修复
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandparent.color = Color.RED;
            fixRedRed(grandparent);
            return;
        }
        // 情况4：父节点红色、叔叔节点黑色，需要旋转调整
        if (parent.isLeftChild() && x.isLeftChild()) {// LL：父为祖父左子，x为父左子
         /*
        旋转前（LL冲突）：
                  (G R)           <- 红色祖父节点
                 /
              (P R)               <- 红色父节点
             /
          (X R)                   <- 红色插入节点
         /
      (A B)                       <- 黑色左子节点 */
            parent.color = Color.BLACK;          // 父节点变黑
            grandparent.color = Color.RED;       // 祖父节点变红
            rightRotate(grandparent);      // 对祖父节点右旋
        /*
       旋转后：
                  (P B)           <- 父节点变为新的子树根
                 /     \
              (X R)   (G R)       <- 祖父节点右下沉
             /           \
          (A B)         (C B)     <- 黑色其他子节点保持不变
        */
        } else if (parent.isLeftChild()) {// LR：父为祖父左子，x为父右子
            /*
        旋转前（LR冲突）：
                  (G R)           <- 红色祖父节点
                 /
              (P R)               <- 红色父节点
                 \
                 (X R)            <- 红色插入节点
                      \
                      (D B)       <- 黑色右子节点*/
            leftRotate(parent);// 对父节点左旋，将其转化为LL冲突
        /*
        左旋后：
                  (G R)
                 /
              (X R)               <- 新的父节点
             /
          (P R)                   <- 原父节点左下沉
             \
             (D B)                <- 原黑色右子节点保持不变*/
            x.color = Color.BLACK;               // 当前节点（X）变黑
            grandparent.color = Color.RED;       // 祖父节点变红
            rightRotate(grandparent);      // 对祖父节点右旋
            /*
            最终结果：
                  (X B)           <- 新的子树根节点
                 /     \
              (P R)   (G R)       <- 原祖父节点右下沉
                 \        \
                 (D B)   (C B)    <- 黑色其他子节点保持不变
            */

        } else if (!x.isLeftChild()) {// RR：父为祖父右子，x为父右子
             /*
        旋转前（RR冲突）：
                  (G R)           <- 红色祖父节点
                     \
                      (P R)       <- 红色父节点
                           \
                           (X R)  <- 红色插入节点
                              \
                              (F B) <- 黑色右子节点
        */
            parent.color = Color.BLACK;          // 父节点变黑
            grandparent.color = Color.RED;       // 祖父节点变红
            leftRotate(grandparent);       // 对祖父节点左旋
        /*
        旋转后：
                  (P B)           <- 父节点变为新的子树根
                 /     \
              (G R)   (X R)       <- 祖父节点左下沉
                       \
                       (F B)      <- 黑色其他子节点保持不变
        */
        } else { // RL：父为祖父右子，x为父左子
        /*
        旋转前（RL冲突）：
                  (G R)           <- 红色祖父节点
                     \
                      (P R)       <- 红色父节点
                     /
                  (X R)           <- 红色插入节点
                 /
              (E B)               <- 黑色左子节点
         */
            rightRotate(parent);           // 对父节点右旋，将其转化为RR冲突
        /*
        右旋后：
                  (G R)
                     \
                      (X R)       <- 新的父节点
                         \
                         (P R)    <- 原父节点右下沉
                        /
                     (E B)        <- 黑色其他子节点保持不变
        */
            x.color = Color.BLACK;               // 当前节点（X）变黑
            grandparent.color = Color.RED;       // 祖父节点变红
            leftRotate(grandparent);       // 对祖父节点左旋
        /*
        最终结果：
                  (X B)           <- 新的子树根节点
                 /     \
              (G R)   (P R)       <- 原祖父节点左下沉
                       /
                    (E B)         <- 黑色其他子节点保持不变
        */
        }
    }
}

