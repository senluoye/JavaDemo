import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HuffmanTree
 * @Description 哈夫曼树的实现
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-13 09:04
 */
public class HuffmanTree {

    private final List<Node> nodes;

    public HuffmanTree(int[] arr) {
        nodes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(i));
        }
    }

    /**
     * 根据数组构建一颗哈夫曼树
     *
     * @param arr 数组
     * @return Node
     */
    public Node createHuffmanTree() {
        while (nodes.size() > 1) {
            Node minA = getMinNode();
            Node minB = getMinNode();

            Node newNode = new Node(minA.value + minB.value);
            newNode.left = minA;
            newNode.right = minB;

            nodes.add(newNode);
        }

        return nodes.get(0);
    }

    /**
     * 获取列表中值最小的节点
     *
     * @return Node
     */
    private Node getMinNode() {
        Node ans = nodes.get(0);
        for (Node node : nodes) {
            ans = node.value < ans.value ? node : ans;
        }
        nodes.remove(ans);
        return ans;
    }

    public void preOrder(Node root) throws Exception {
        if (root == null) {
            throw new Exception("empty node");
        }
        root.preOrder();
    }

    /**
     * 节点
     */
    @Data
    private static class Node implements Comparable<Node> {
        // 节点权值
        private int value;

        // 字符
        private char c;

        // 左子节点
        private Node left;

        // 右子节点
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            System.out.println(value);
            if (left != null) {
                left.preOrder();
            }
            if (right != null) {
                right.preOrder();
            }
        }

        /**
         * 从小到大排序
         *
         * @param o the object to be compared.
         * @return int
         */
        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffmanTree tree = new HuffmanTree(arr);
        Node root = tree.createHuffmanTree();
        tree.preOrder(root);
    }
}
