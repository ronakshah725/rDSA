package org.ronak.ds.datastructures;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

/***
 * Questions for Rippling
 * 1. Implementation of a key value store, which followed into 
 * high-level implementation of transactions with commit and rollback. 
 * The question then goes deeper with implementation of nested transactions.
 *
 * 2. You are given a continuous stream of integers as input. Design an algorithm to process each integer and maintain the 
 * last k integers in memory. At any given point, 
 * your task is to find and output the smallest difference pair among the last k integers.
 *
 * Example: arr = [3,8,-10,23,19,-4,-14,27], k = 3, 
 * Ans = 10, since K = 3, last 3 elements [-4, -14, 27], abs min diff among these is 10.
 *
 * arr = [3,8,-10,23,19,-4, -14, 27] k = 4
 *
 * 3. Design a music player like Spotify with the following functionality: 
 * - add_song (song_title:string) Adds song to catalog and generates an id starting with 1 
 * - play_song (song_id:int, user_id:int) Tracks a song played and by a given user 
 * - print_summary () Prints song names and how many unique listens it received sorted in descending order by unique listens 
 * - last_three_played_songs (user_id: int) Takes a userId and prints their last 3 played songs 
 * - Update print_summary method to only print k songs.
 *
 * ensure all methods run better than O(N log N)
 *
 * 4. merge k sorted arrays
 *
 *
 * 6. https://leetcode.com/discuss/interview-question/483660/google-phone-currency-conversion
 * Question Paramenters: array of currency conversion rates. E.g. ['USD', 'GBP', 0.77] which means 1 USD is equal to 0.77 GBP an array containing a 'from' currency and a 'to' currency Given the above parameters, find the conversion rate that maps to the 'from' currency to the 'to' currency. Your return value should be a number. Example: You are given the following parameters: Rates: ['USD', 'JPY', 110] ['US', 'AUD', 1.45] ['JPY', 'GBP', 0.0070] To/From currency ['GBP', 'AUD'] Find the rate for the 'To/From' curency. In this case, the correct result is 1.89.
 *
 * 7. Implement a spreadsheet (coding) 
 *
 * 8. Bus Route question ( available in leetcode)
 *
 * 9.  List of synonyms were given and need to find out the number of distinct queries from a vector of strings
 *
 * 10. Find out the sum of all elements in a square size of K from a matrix
 *
 * 11. Game of life: https://leetcode.com/problems/game-of-life/description/
 *
 *
 * 12. Design a bank account class where we can withdraw and deposit 
 * Follow up example: Ok, how would you represent a bank a multiple classes - can we transfer money from account to account as well?
 *
 * 13. Given a list of employee object records with performance review scores and associated organization hierarchy, find the employee/manager with the highest average score.
 */

class MinDiffStream {
    private Map<Integer, Integer> treeMap = new TreeMap<>();
    private Deque<Integer> queue = new ArrayDeque<>();


    public static void main(String[] args) {
    }
}