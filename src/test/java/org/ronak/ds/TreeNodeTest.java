package org.ronak.ds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {

    // Helper method to create a tree with unique values
    private static TreeNode<Integer> createTreeWithDepth(int depth) {
        if (depth <= 0) return null;
        TreeNode<Integer> root = new TreeNode<>(1, null, null);
        if (depth > 1) {
            root.setLeft(new TreeNode<>(2, null, null));
            root.setRight(new TreeNode<>(3, null, null));
            if (depth > 2) {
                root.getLeft().setLeft(new TreeNode<>(4, null, null));
                root.getLeft().setRight(new TreeNode<>(5, null, null));
                root.getRight().setLeft(new TreeNode<>(6, null, null));
                root.getRight().setRight(new TreeNode<>(7, null, null));
            }
        }
        return root;
    }

    @Nested
    @DisplayName("bfSearch Tests")
    class BfSearchTests {

        @ParameterizedTest(name = "Search for {0} in tree, expected result: {1}")
        @MethodSource("searchTestCases")
        void testBfSearch(int valueToSearch, boolean expectedResult) {
            TreeNode<Integer> root = createTreeWithDepth(3);
            assert root != null;
            assertEquals(expectedResult, root.bfSearch(root, valueToSearch));
        }

        static Stream<Arguments> searchTestCases() {
            return Stream.of(
                    Arguments.of(1, true),   // Root
                    Arguments.of(3, true),   // Right child of root
                    Arguments.of(7, true),   // Last node
                    Arguments.of(8, false),  // Non-existent value
                    Arguments.of(0, false)   // Another non-existent value
            );
        }

        @Test
        @DisplayName("bfSearch with null root should return false")
        void testBfSearchWithNullRoot() {
            assertFalse(new TreeNode<>(0, null, null).bfSearch(null, 1));
        }
    }

    @Nested
    @DisplayName("traverseZigZag Tests")
    class TraverseZigZagTests {

        @Test
        @DisplayName("ZigZag traversal of sample tree")
        void testTraverseZigZag() {
            TreeNode<Integer> root = createTreeWithDepth(3);
            assert root != null;
            List<List<Integer>> result = root.traverseZigZag(root);

            assertEquals(3, result.size());
            assertEquals(List.of(1), result.get(0));
            assertEquals(List.of(3, 2), result.get(1));
            assertEquals(List.of(4, 5, 6, 7), result.get(2));
        }

        @Test
        @DisplayName("ZigZag traversal of null tree should return empty list")
        void testTraverseZigZagWithNullRoot() {
            TreeNode<Integer> root = null;
            List<List<Integer>> result = new TreeNode<>(0, null, null).traverseZigZag(root);
            assertTrue(result.isEmpty());
        }

        @ParameterizedTest(name = "ZigZag traversal of tree with depth {0}")
        @MethodSource("zigzagTestCases")
        void testTraverseZigZagWithDifferentDepths(int depth, List<List<Integer>> expected) {
            TreeNode<Integer> root = createTreeWithDepth(depth);
            assert root != null;
            List<List<Integer>> result = root.traverseZigZag(root);
            assertEquals(expected, result);
        }

        static Stream<Arguments> zigzagTestCases() {
            return Stream.of(
                    Arguments.of(1, List.of(List.of(1))),
                    Arguments.of(2, List.of(List.of(1), List.of(3, 2))),
                    Arguments.of(3, List.of(List.of(1), List.of(3, 2), List.of(4, 5, 6, 7)))
            );
        }
    }
}