package org.ronak.ds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class IterativeTreeTraversalTest {
    TreeNode<Integer> root;

    @BeforeEach
    void setup() {
        this.root = createValidBST();
    }

    @Test
    void testInOrder() {
        IterativeTreeTraversal<Integer> a = new IterativeTreeTraversal<>();
        List<Integer> result = a.inOrder(root);
        List<Integer> expectedResult = List.of(1, 2, 3, 4, 5, 6, 7);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testPreOrder() {
        IterativeTreeTraversal<Integer> a = new IterativeTreeTraversal<>();
        List<Integer> result = a.preOrder(root);
        List<Integer> expectedResult = List.of(4, 2, 1, 3, 6, 5, 7);

        Assertions.assertEquals(expectedResult, result);
    }


    @Test
    void testPostOrder() {
        IterativeTreeTraversal<Integer> a = new IterativeTreeTraversal<>();
        List<Integer> result = a.postOrder(root);
        List<Integer> expectedResult = List.of(1, 3, 2, 5, 7, 6, 4);

        Assertions.assertEquals(expectedResult, result);
    }

    private TreeNode<Integer> createValidBST() {
        TreeNode<Integer> leaf1 = new TreeNode<>(1, null, null);
        TreeNode<Integer> leaf3 = new TreeNode<>(3, null, null);
        TreeNode<Integer> node2 = new TreeNode<>(2, leaf1, leaf3);

        TreeNode<Integer> leaf5 = new TreeNode<>(5, null, null);
        TreeNode<Integer> leaf7 = new TreeNode<>(7, null, null);
        TreeNode<Integer> node6 = new TreeNode<>(6, leaf5, leaf7);

        return new TreeNode<>(4, node2, node6);
    }
}