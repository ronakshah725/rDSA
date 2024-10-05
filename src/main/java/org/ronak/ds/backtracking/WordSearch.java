package org.ronak.ds.backtracking;

public class WordSearch {

    private final int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // No change needed here
    private char[][] grid; // Added to store the grid

    public WordSearch(char[][] board) {
        this.grid = board; // Initialize the grid in the constructor
    }

    /**
     * Find if the word is present in the grid, where each word[i] is 4-directional
     * neighbour of word[i - 1], for  0 <= i <= n
     *
     * @param word target word to search in grid
     * @return true if the word exists in the grid, false otherwise
     */
    public boolean wordSearch(String word) { // Changed to public
        int m = grid.length;
        if (m == 0) return false; // Handle empty grid

        int n = grid[0].length; // Safe to access since we checked m > 0

        char[] wordChars = word.toCharArray();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtrackSearch(wordChars, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrackSearch(char[] wordChars, int wordIdx, int row, int col) {
        if (wordIdx == wordChars.length) {
            return true; // Found the entire word
        }

        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || grid[row][col] != wordChars[wordIdx]) {
            return false; // Out of bounds or already visited or character mismatch
        }
        char temp = grid[row][col];
        grid[row][col] = '#'; // Mark as visited
        boolean found = false;

        // Explore all directions
        for (int[] direction : directions) {
            if(backtrackSearch(wordChars, wordIdx + 1, row + direction[0], col + direction[1])){
                found = true;
                break;
            }
        }

        grid[row][col] = temp;
        return found; // No valid path found
    }
}