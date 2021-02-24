package com.example.testmaven;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * @author heshufan
 * @date 2021/2/24
 */

public class Codec {

    public static void main(String[] args){
        Codec codec = new Codec();
        TreeNode node = codec.deserialize("1,2,3,null,null,4,null,null,5");
        System.out.println(codec.serialize(node));
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer buffer = new StringBuffer();
        serializeHelper(root, buffer);
        return buffer.toString();
    }

    private void serializeHelper(TreeNode root, StringBuffer buffer) {
        if (root == null) {
            buffer.append("null,");
            return;
        }
        buffer.append(root.val + ",");
        serializeHelper(root.left, buffer);
        serializeHelper(root.right, buffer);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        return deserializeHelper(datas, 0);
    }

    private TreeNode deserializeHelper(String[] datas, int i) {
        if (i >= datas.length || "null".equals(datas[i])) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(datas[i]));
        node.left = deserializeHelper(datas, 2 * i + 1);
        node.right = deserializeHelper(datas, 2 * (i + 1));
        return node;
    }
}
