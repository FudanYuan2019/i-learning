package base.tree;

import util.PrintUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 *
 * @Author: Jeremy
 * @Date: 2020/9/13 13:51
 */
public class Trie {
    private TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode(ch));
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char ch : chars) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (char ch : chars) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;
    }

    private static class TrieNode {
        private char ch;
        private Map<Integer, TrieNode> children;
        private boolean isEnd;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isEnd = false;
        }

        public TrieNode(char ch) {
            this.ch = ch;
            this.children = new HashMap<>();
            this.isEnd = false;
        }

        public boolean containsKey(char ch) {
            return children.get(ch - 'a') != null;
        }

        public TrieNode get(char ch) {
            return children.get(ch - 'a');
        }

        public void put(char ch, TrieNode node) {
            children.put(ch - 'a', node);
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        boolean res = trie.search("apple");   // 返回 true
        PrintUtil.print(res);

        res = trie.search("app");     // 返回 false
        PrintUtil.print(res);

        res = trie.startsWith("app"); // 返回 true
        PrintUtil.print(res);

        trie.insert("app");
        res = trie.search("app");     // 返回 true
        PrintUtil.print(res);
    }
}
