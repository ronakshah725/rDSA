package org.ronak.ds;

import com.sun.source.tree.Tree;

import java.util.*;

public class IterativeTreeTraversal<T> {
    /**
     * Inorder Traversal: Left, root, Right
     */
    List<T> inOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> currNode = root;

        while (true) {
            if(currNode != null){
                stack.push(currNode);
                currNode = currNode.left;
            }else {
                if(stack.isEmpty()) break;
                currNode = stack.pop();
                result.add(currNode.val);
                currNode = currNode.right;
            }
        }
        return result;
    }

    /**
     * Preorder Traversal: root, Left, Right
     */
    List<T> preOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode<T> curr = stack.pop();
            result.add(curr.val);
            if(curr.right != null) {
                stack.push(curr.right);
            }
            if(curr.left != null){
                stack.push(curr.left);
            }
        }
        return result;
    }

    /**
     * Postorder Traversal: Left, Right, root
     */
    List<T> postOrder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();

        if (root == null) return result;

        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        TreeNode<T> current = root;
        TreeNode<T> lastVisited = null;

        while(!stack.isEmpty() || current != null ){
            // Go till the left most of current
            if(current != null){
                stack.push(current);
                current = current.left;

            }
            // No left subtree left to process, we process the right node
            else{
                TreeNode<T> peek = stack.peek();
                // There is a non-null right node to process

                if(peek.right != null && peek.right != lastVisited ){
                    current = peek.right;
                }else {
                    // If no right node to process OR the right node is the same as the lastVisited,
                    // we are done processing the right subtree.
                    result.add(peek.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return result;
    }
}