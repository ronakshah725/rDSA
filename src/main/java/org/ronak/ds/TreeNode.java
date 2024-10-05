package org.ronak.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
@AllArgsConstructor
public class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    public boolean bfSearch(TreeNode<T> root, T val) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        List<T> qList = new LinkedList<>();
        qList.add(null);

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> head = queue.poll();

            if (head.val.equals(val)) {
                return true;
            }
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
        return false;
    }

    public List<List<T>> traverseZigZag(TreeNode<T> root) {
        List<List<T>> levels = new ArrayList<>();

        if (root == null) {
            return levels;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        boolean toRight = true;

        while (!queue.isEmpty()) {

            LinkedList<T> currentLevel = new LinkedList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode<T> head = queue.poll();
                if (head == null) continue;

                if (toRight) {
                    currentLevel.addLast(head.val);
                } else {
                    currentLevel.addFirst(head.val);
                }

                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
            }
            levels.add(currentLevel);
            toRight = !toRight;
        }

        return levels;
    }
}