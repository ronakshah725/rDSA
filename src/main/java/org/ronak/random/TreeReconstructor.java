package org.ronak.random;

import java.util.*;

// Class representing a tree node with a character value and a list of children
class Node {
    char value; // Value of the node
    List<Node> children; // List of child nodes

    // Constructor to initialize the node with a value
    public Node(char value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

public class TreeReconstructor {
    // Method to create a tree from a given input string
    public Node createTree(String input) {
        Map<Character, Node> nodeMap = new HashMap<>(); // Map to store nodes by their character values
        String[] pairs = input.split(","); // Split the input string by commas to get parent-child pairs
        Set<Character> childSet = new HashSet<>(); // Set to track all child nodes

        // Iterate through each parent-child pair
        for (String pair : pairs) {
            String[] parts = pair.split(":"); // Split the pair by colon to separate parent and child
            if(parts.length != 2 || parts[0].isEmpty()){
                continue;
            }

            char parentValue = parts[0].charAt(0); // Get the parent value
            char childValue = parts[1].charAt(0); // Get the child value

            // Get or create the parent node
            Node parentNode = nodeMap.computeIfAbsent(parentValue, Node::new);
            // Get or create the child node
            Node childNode = nodeMap.computeIfAbsent(childValue, Node::new);

            // Add the child node to the parent node's list of children
            parentNode.children.add(childNode);

            // Track the child node in the set
            childSet.add(childValue);
        }

        // Determine the root node: the root is the node that is never a child
        for (char key : nodeMap.keySet()) {
            if (!childSet.contains(key)) {
                return nodeMap.get(key);
            }
        }

        return null; // If no root is found (which shouldn't happen if input is valid)
    }

    public static void main(String[] args) {
        TreeReconstructor tr = new TreeReconstructor();
        // Create the tree from the input string
        Node root = tr.createTree("D:T,Z:I,D:Q,I:P,B:Z,:B,D:R,B:D");
        // Print the constructed tree
        tr.printTree(root, 0);
    }

    // Method to print the tree structure
    public void printTree(Node node, int level) {
        if (node == null) {
            return; // If the node is null, return
        }

        // Indentation to show the level of the node in the tree
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        // Print the value of the current node
        System.out.println(node.value);

        // Recursively print each child of the current node
        for (Node child : node.children) {
            printTree(child, level + 1);
        }
    }
}
