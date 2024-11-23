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
    //注意，旋转操作并不会改变节点自身引用关系，只是改变每个节点的right,left,parent的关系
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
        }else if (key < parent.key) {
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
     * 通过颜色调整和旋转，解决新插入节点导致的红红相邻问题。
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
        // 获取相关节点
        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;

        // 情况3：父节点和叔叔节点都是红色
        if (isRed(uncle)) {
            parent.color = Color.BLACK;  // 父节点变黑
            uncle.color = Color.BLACK;  // 叔叔节点变黑
            grandparent.color = Color.RED;  // 祖父节点变红 为了保证到叶子节点的黑高不变
            fixRedRed(grandparent);  // 对祖父递归修复
            return;
        }

        // 情况4：父节点红色，叔叔节点黑色，需要旋转调整
        if (parent.isLeftChild() && x.isLeftChild()) { // LL：父为祖父左子，x为父左子
        /*
        旋转前：      (G B)                     旋转后：      (P B)
                    /                                    /     \
                 (P R)                                (X R)   (G R)
                /
             (X R)
             这里需要注意的时G一定是黑色，要不然在新增节点前就不满足红黑树的特性了
        */
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (parent.isLeftChild()) { // LR：父为祖父左子，x为父右子
        /*
        左旋前：      (G B)                     左旋后：      (G B)                     右旋后：      (X B)
                    /                                    /                                    /     \
                 (P R)                                (X R)                                (P R)   (G R)
                    \                                /
                   (X R)                         (P R)

                只旋转一次，会导致当前节点与祖父节点产生红红冲突
        */
            leftRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            rightRotate(grandparent);
        } else if (!x.isLeftChild()) { // RR：父为祖父右子，x为父右子
        /*
        旋转前：      (G B)                     旋转后：        (P B)
                        \                                   /     \
                         (P R)                          (G R)   (X R)
                              \
                              (X R)

        */
            parent.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        } else { // RL：父为祖父右子，x为父左子
        /*
        右旋前：      (G B)                     右旋后：      (G B)                     左旋后：      (X B)
                        \                                    \                                   /     \
                         (P R)                                (X R)                           (G R)   (P R)
                        /                                       \
                     (X R)                                      (P R)
        */
            rightRotate(parent);
            x.color = Color.BLACK;
            grandparent.color = Color.RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 删除
     * <br>
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     *
     * @param key 键
     */
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    public boolean contains(int key) {
        return find(key) != null;
    }

    // 查找删除节点
    private Node find(int key) {
        Node p = root;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    // 查找剩余节点,也就是待删除节点的后继节点
    private Node findReplaced(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        }
        if (deleted.left == null) {
            return deleted.right;
        }
        if (deleted.right == null) {
            return deleted.left;
        }
        Node s = deleted.right;
        while (s.left != null) {
            s = s.left;
        }
        return s;
    }

    // 处理双黑 (case3、case4、case5)，处理双黑，当前节点也就是x一定是叶子节点
    private void fixDoubleBlack(Node x) {
        if (x == root) {
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        // case 3 兄弟节点是红色
        if (isRed(sibling)) {
            if (x.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }//为了让兄弟节点成为父节点，再让兄弟节点变黑，原来的父节点变红
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            fixDoubleBlack(x);
            return;
        }
        if (sibling != null) {
            // case 4 兄弟是黑色, 两个侄子也是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color = Color.RED;
                if (isRed(parent)) {
                    parent.color = Color.BLACK;
                } else {
                    fixDoubleBlack(parent);
                }
            }
            // case 5 兄弟是黑色, 侄子有红色
            else {
                // LL
                if (sibling.isLeftChild() && isRed(sibling.left)) {
                    rightRotate(parent);
                    sibling.left.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                // LR
                else if (sibling.isLeftChild() && isRed(sibling.right)) {
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                }
                // RL
                else if (!sibling.isLeftChild() && isRed(sibling.left)) {
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                // RR
                else {
                    leftRotate(parent);
                    sibling.right.color = Color.BLACK;
                    sibling.color = parent.color;
                }
                parent.color = Color.BLACK;
            }
        } else {
            // @TODO 实际也不会出现，触发双黑后，兄弟节点不会为 null
            fixDoubleBlack(parent);
        }
    }

    private void doRemove(Node deleted) {
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        // 没有孩子
        if (replaced == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root = null;
            } else {
                if (isBlack(deleted)) {
                    // 复杂调整
                    fixDoubleBlack(deleted);
                } else {
                    // 红色叶子, 无需任何处理
                }
                if (deleted.isLeftChild()) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }
        // 有一个孩子
        if (deleted.left == null || deleted.right == null) {
            // case 1 删除的是根节点
            if (deleted == root) {
                root.key = replaced.key;
                root.value = replaced.value;
                // 处理替代节点是否为单节点的情况
                if (replaced.left != null || replaced.right != null) {
                    // 如果替代节点有子节点，则重新连接到根节点
                    root.left = replaced.left;
                    root.right = replaced.right;
                    if (replaced.left != null) {
                        replaced.left.parent = root;
                    }
                    if (replaced.right != null) {
                        replaced.right.parent = root;
                    }
                } else {
                    // 如果替代节点是单节点，则直接清空左右子节点
                    root.left = root.right = null;
                }
            } else {
                if (deleted.isLeftChild()) {
                    parent.left = replaced;
                } else {
                    parent.right = replaced;
                }
                replaced.parent = parent;
                deleted.left = deleted.right = deleted.parent = null;
                if (isBlack(deleted) && isBlack(replaced)) {
                    // 复杂处理 @TODO 实际不会有这种情况 因为只有一个孩子时 被删除节点是黑色 那么剩余节点只能是红色不会触发双黑
                    //因为只有一个孩子，那么待删除节点和后继节点一定是相邻的
                    fixDoubleBlack(replaced);
                } else {
                    // case 2 删除是黑，剩下是红，只需要让replaced变成黑色，维持黑色的数量平衡
                    replaced.color = Color.BLACK;
                }
            }
            return;
        }
        // case 0 有两个孩子 => 有一个孩子 或 没有孩子
        //这时候找到的replaced一定没有左孩子。
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }

}

