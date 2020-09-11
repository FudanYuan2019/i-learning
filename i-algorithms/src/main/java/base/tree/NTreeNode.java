package base.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jeremy
 * @Date: 2020/9/11 15:08
 */
class NTreeNode {
    public int val;
    public List<NTreeNode> children;

    public NTreeNode() {
    }

    public NTreeNode(int _val) {
        this.val = _val;
        this.children = new ArrayList<>();
    }

    public NTreeNode(int _val, List<NTreeNode> _children) {
        this.val = _val;
        this.children = _children;
    }
};