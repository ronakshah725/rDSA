package org.ronak.ds;

import java.util.LinkedList;
import java.util.Queue;

public class LandWater {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private final int[][] region;

    public LandWater(int[][] region) {
        this.region = region;
    }

    public int getLargestRegion() {
        if (region.length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[region.length][region[0].length];
        int largestRegion = 0;

        for (int row = 0; row < region.length; row++) {
            for (int col = 0; col < region[0].length; col++) {
                if (region[row][col] == 1 && !visited[row][col]) {
                    System.out.println("Exploring new region at (" + row + ", " + col + ")");
                    int regionLength = bfs(row, col, visited);
                    System.out.println("Region length: " + regionLength);
                    System.out.println();
                    largestRegion = Math.max(largestRegion, regionLength);
                }
            }
        }

        return largestRegion;
    }

    private int bfs(int row, int col, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col});
        visited[row][col] = true;
        int regionLength = 1;

        while (!queue.isEmpty()) {
            System.out.println("Queue: " + printQueue(queue));
            int[] current = queue.poll();
            System.out.println("Visiting cell at (" + current[0] + ", " + current[1] + ")");
            printRegion(visited);

            for (int[] direction : DIRECTIONS) {
                int newRow = current[0] + direction[0];
                int newCol = current[1] + direction[1];

                if (isValid(newRow, newCol, visited)) {
                    System.out.println("Adding neighbor at (" + newRow + ", " + newCol + ") to queue");
                    queue.offer(new int[] {newRow, newCol});
                    visited[newRow][newCol] = true;
                    regionLength++;
                }
            }
        }

        return regionLength;
    }

    private String printQueue(Queue<int[]> queue) {
        StringBuilder sb = new StringBuilder();
        for (int[] cell : queue) {
            sb.append("(").append(cell[0]).append(", ").append(cell[1]).append(") ");
        }
        return sb.toString();
    }

    private void printRegion(boolean[][] visited) {
        for (int row = 0; row < region.length; row++) {
            for (int col = 0; col < region[0].length; col++) {
                if (visited[row][col]) {
                    System.out.print("X ");
                } else if (region[row][col] == 1) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isValid(int row, int col, boolean[][] visited) {
        return !isOutOfBounds(row, col) && region[row][col] == 1 && !visited[row][col];
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || col < 0 || row >= region.length || col >= region[0].length;
    }

    public static void main(String[] args) {
        int[][] region = {
                {1, 1, 0, 0},
                {0, 0, 1, 1},
                {0, 0, 0, 0},
                {1, 1, 1, 1}
        };

        LandWater landWater = new LandWater(region);
        int largestRegion = landWater.getLargestRegion();
        System.out.println("Largest region: " + largestRegion);
    }
}