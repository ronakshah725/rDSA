package org.ronak.ds.backtracking;

import org.junit.jupiter.api.Test;
import org.ronak.ds.backtracking.WordSearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordSearchTest {

    @Test
    public void testWordExists() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        WordSearch wordSearch = new WordSearch(board);
        assertTrue(wordSearch.wordSearch("ABCCED")); // Should return true
        assertTrue(wordSearch.wordSearch("SEE"));    // Should return true
        assertFalse(wordSearch.wordSearch("ABCB"));   // Should return false
    }

    @Test
    public void testEmptyBoard() {
        char[][] board = {};
        WordSearch wordSearch = new WordSearch(board);
        assertFalse(wordSearch.wordSearch("ANYWORD")); // Should return false
    }

    @Test
    public void testSingleCharacterBoard() {
        char[][] board = {{'A'}};
        WordSearch wordSearch = new WordSearch(board);
        assertTrue(wordSearch.wordSearch("A"));  // Should return true
        assertFalse(wordSearch.wordSearch("B")); // Should return false
    }

    @Test
    public void testWordWithBacktracking() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        WordSearch wordSearch = new WordSearch(board);
        assertTrue(wordSearch.wordSearch("ABCCED")); // Should return true
        assertTrue(wordSearch.wordSearch("SFC"));    // Should return true
        assertFalse(wordSearch.wordSearch("SFA"));   // Should return false
    }

    @Test
    public void testLongWordNotInBoard() {
        char[][] board = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        };
        WordSearch wordSearch = new WordSearch(board);
        assertFalse(wordSearch.wordSearch("ABCDEFGHIJ")); // Should return false
    }

    @Test
    public void testWordWithDiagonalMovement() {
        char[][] board = {
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        };
        WordSearch wordSearch = new WordSearch(board);
        assertFalse(wordSearch.wordSearch("AEI")); // Should return false (diagonal not allowed)
    }
}
