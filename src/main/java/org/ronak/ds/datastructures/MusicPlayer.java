package org.ronak.ds.datastructures;

import java.util.*;
/***
 * 
 * Design a music player like Spotify with the following functionality: 
 * - add_song (song_title:string) Adds song to catalog and generates an id starting with 1 
 * - play_song (song_id:int, user_id:int) Tracks a song played and by a given user 
 * - print_summary () Prints song names and how many unique listens it received sorted in descending order by unique listens 
 * - last_three_played_songs (user_id: int) Takes a userId and prints their last 3 played songs 
 * - Update print_summary method to only print k songs.
 * 
 *  * ensure all methods run better than O(N log N)
 * 
 * Track: 
 * catalog
 * uniqueListens per song
 * SongIds ordered by DESC uniqueListens (LIMIT k)
 * songs played by user ordered by recently played
 
 */



 /**
 * A music player system that tracks song plays and user history efficiently.
 * Core operations are optimized to run in better than O(n log n) time.
 */
class MusicPlayer {
    // Core data structures
    private final PriorityQueue<Song> allSongs;  // MaxHeap of all songs
    private final Map<Integer, Song> songMap;    // Quick song lookup by ID
    private final Map<Integer, Set<Integer>> userListens;  // Track unique listens per song
    private final Map<Integer, Deque<Integer>> userHistory;  // Recent plays per user
    
    // Counters and configuration
    private int nextSongId;
    private static final int HISTORY_LIMIT = 3;  // Number of recent songs to track per user
    
    /**
     * Represents a song in the music system with its metadata and play statistics.
     */
    private static class Song {
        final int id;
        final String title;
        public int uniqueListens;
        
        Song(int id, String title) {
            this.id = id;
            this.title = title;
            this.uniqueListens = 0;
        }

        int getUniqueListens(){
            return uniqueListens;
        }
        
    }
    
    public MusicPlayer() {
        this.allSongs = new PriorityQueue<>(
            Comparator.comparingInt(Song::getUniqueListens).reversed()
        );
        this.songMap = new HashMap<>();
        this.userListens = new HashMap<>();
        this.userHistory = new HashMap<>();
        this.nextSongId = 1;
    }
    
    /**
     * Adds a new song to the catalog.
     * Time Complexity: O(log n) - heap insertion
     * Space Complexity: O(1) - constant space per song
     */
    public int addSong(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be null or empty");
        }
        
        int songId = nextSongId++;
        Song song = new Song(songId, title);
        songMap.put(songId, song);
        allSongs.offer(song);
        userListens.put(songId, new HashSet<>());
        return songId;
    }
    
    /**
     * Records a song play for a user and updates necessary tracking data.
     * Time Complexity: O(log n) - heap operations for updating song position
     * Space Complexity: O(1) - constant space for tracking
     */
    public void playSong(int songId, int userId) {
        if (!songMap.containsKey(songId)) {
            throw new IllegalArgumentException("Invalid song ID: " + songId);
        }
        
        Song song = songMap.get(songId);
        
        // Update unique listens if this is first time user played this song
        if (userListens.get(songId).add(userId)) {
            allSongs.remove(song);  // O(log n)
            song.uniqueListens++;
            allSongs.offer(song);   // O(log n)
        }
        
        // Update user's play history
        userHistory.computeIfAbsent(userId, k -> new ArrayDeque<>());
        Deque<Integer> history = userHistory.get(userId);
        history.addFirst(songId);
        if (history.size() > HISTORY_LIMIT) {
            history.removeLast();
        }
    }
    
    /**
     * Prints the k most played songs sorted by unique listen count.
     * Time Complexity: O(k log n) - extracting k elements from heap
     * Space Complexity: O(k) - temporary storage for top k songs
     */
    public List<String> printSummary(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        
        List<String> result = new ArrayList<>();
        PriorityQueue<Song> tempHeap = new PriorityQueue<>(allSongs);
        
        int count = Math.min(k, tempHeap.size());
        for (int i = 0; i < count; i++) {
            Song song = tempHeap.poll();
            result.add(String.format("%s: %d unique listens", 
                      song.title, song.uniqueListens));
        }
        return result;
    }
    
    /**
     * Returns the last three songs played by a specific user.
     * Time Complexity: O(1) - direct deque access
     * Space Complexity: O(1) - returns fixed size list
     */
    public List<String> lastThreePlayedSongs(int userId) {
        List<String> result = new ArrayList<>();
        Deque<Integer> history = userHistory.getOrDefault(userId, new ArrayDeque<>());
        
        for (int songId : history) {
            result.add(songMap.get(songId).title);
        }
        return result;
    }
}

/**
 * Test cases to verify MusicPlayer functionality
 */
class MusicPlayerTest {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        
        // Test 1: Basic song addition and playing
        int song1 = player.addSong("Shape of You");
        int song2 = player.addSong("Blinding Lights");
        int song3 = player.addSong("Dance Monkey");
        
        // Test 2: Multiple users playing songs
        player.playSong(song1, 1);  // User 1 plays song1
        player.playSong(song1, 2);  // User 2 plays song1
        player.playSong(song2, 1);  // User 1 plays song2
        player.playSong(song3, 3);  // User 3 plays song3
        
        // Test 3: Verify unique listen counts
        System.out.println("Top songs summary:");
        List<String> summary = player.printSummary(3);
        for (String entry : summary) {
            System.out.println(entry);
        }
        
        // Test 4: Verify user history
        player.playSong(song2, 1);
        player.playSong(song3, 1);
        System.out.println("\nUser 1's last three songs:");
        List<String> history = player.lastThreePlayedSongs(1);
        for (String song : history) {
            System.out.println(song);
        }
        
        // Test 5: Edge cases
        try {
            player.addSong("");  // Should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught expected error: " + e.getMessage());
        }
        
        try {
            player.playSong(999, 1);  // Invalid song ID
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
 
