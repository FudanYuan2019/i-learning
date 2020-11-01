package base.array;

import util.PrintUtil;

import java.util.*;

/**
 * LeetCode 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在 平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * <p>
 * 注意: 允许出现重复元素。
 * <p>
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * <p>
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * <p>
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * <p>
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * <p>
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 *
 * @Author: Jeremy
 * @Date: 2020/11/1 15:17
 */
public class RandomizedCollection {

    private Map<Integer, Set<Integer>> idx;
    private List<Integer> nums;

    public static void main(String[] args) {
        RandomizedCollection randomizedCollection = new RandomizedCollection();
        boolean res = randomizedCollection.insert(1);
        PrintUtil.print(res);

        res = randomizedCollection.insert(1);
        PrintUtil.print(res);

        res = randomizedCollection.insert(2);
        PrintUtil.print(res);

        int num = randomizedCollection.getRandom();
        PrintUtil.print(num);

        res = randomizedCollection.remove(1);
        PrintUtil.print(res);

        num = randomizedCollection.getRandom();
        PrintUtil.print(num);

    }

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        this.idx = new HashMap<>();
        this.nums = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        Set<Integer> set = idx.getOrDefault(val, new HashSet<>());
        if (set.isEmpty()) {
            return false;
        }
        Iterator<Integer> iterator = set.iterator();
        int index = iterator.next();
        int last = nums.get(nums.size() - 1);
        nums.set(index, last);
        idx.get(val).remove(index);
        idx.get(last).remove(nums.size() - 1);
        if (index < nums.size() - 1) {
            idx.get(last).add(index);
        }
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        return nums.get((int) (Math.random() * nums.size()));
    }
}
