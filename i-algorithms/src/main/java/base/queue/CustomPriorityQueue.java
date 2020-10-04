package base.queue;

/**
 * 优先级队列
 *
 * @Author: Jeremy
 * @Date: 2020/9/7 11:23
 */
public class CustomPriorityQueue {
    public Node[] nodes;
    public int size;
    public int count;

    public CustomPriorityQueue(int size) {
        this.size = size;
        this.nodes = new Node[size];
        this.count = 0;
    }

    public CustomPriorityQueue(int size, Node[] nodes) {
        this.size = size;
        this.nodes = new Node[size];
        for (int i = 0; i < nodes.length; i++) {
            this.nodes[i] = nodes[i];
        }
        this.count = nodes.length;
        update();
    }

    public CustomPriorityQueue(int size, int[] nums) {
        this.size = size;
        this.nodes = new Node[size];
        for (int i = 0; i < nums.length; i++) {
            this.nodes[i] = new Node(nums[i], -nums[i]);
        }
        this.count = nodes.length;
        update();
    }

    /**
     * 增加元素至小顶堆
     *
     * @param node
     * @return
     */
    public boolean add(Node node) {
        if (count >= size) {
            return false;
        }
        nodes[count++] = node;
        update();
        return true;
    }

    /**
     * 更新优先级队列
     *
     * @return
     */
    public void update() {
        for (int i = count / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    /**
     * 更新
     *
     * @param node
     */
    public void update(Node node) {
        for (int i = 0; i < count; i++) {
            if (nodes[i].key.equals(node.key)) {
                nodes[i].property = node.property;
                break;
            }
        }
        update();
    }

    /**
     * 弹出堆顶元素
     *
     * @return
     */
    public Node poll() {
        if (isEmpty()) {
            return null;
        }
        swap(nodes, 0, --count);
        update();
        return nodes[count];
    }

    /**
     * 返回堆顶元素
     * @return
     */
    public Node top() {
        if (isEmpty()) {
            return null;
        }
        return nodes[0];
    }

    /**
     * 优先级队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * 堆化
     *
     * @param i
     */
    public void heapify(int i) {
        while (true) {
            int minPos = i;
            if (2 * i + 1 < count && nodes[2 * i + 1].property < nodes[i].property) minPos = 2 * i + 1;
            if (2 * i + 2 < count && nodes[2 * i + 2].property < nodes[minPos].property) minPos = 2 * i + 2;
            if (minPos == i) {
                break;
            }
            swap(nodes, i, minPos);
            i = minPos;
        }
    }

    /**
     * 交换
     *
     * @param nodes
     * @param i
     * @param j
     */
    public void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print("(" + nodes[i].key + " " + nodes[i].property + ")" + "->");
        }
        System.out.println();
    }

    public static class Node {
        private Integer key;
        private Integer property;

        public Node(Integer key, Integer property) {
            this.key = key;
            this.property = property;
        }

        public Integer getKey() {
            return key;
        }

        public Integer getProperty() {
            return property;
        }
    }

}