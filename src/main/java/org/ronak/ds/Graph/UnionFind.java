package org.ronak.ds.Graph;

public class UnionFind {

    // Goal: using adjacency list, create union find data structure
    // and implement union and find operation.

    // Hint: use -1 for root.

    private int[] root;

    public UnionFind(int n) {
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
    }

    public int quickFind(int i) {
        return root[i];
    }

    // For the union, find the parent of both the element and
    // set the parent of one to the root of other.

    public void union(int p, int q) {
        int rootX = quickFind(p);
        int rootY = quickFind(q);

        if (rootX!= rootY) {
            for(int i = 0; i < root.length; i++){
                if(root[i] == rootX) root[i] = rootY;
            }
        }

    }

    public boolean connected(int x, int y){
        return quickFind(x) == quickFind(y);
    }

}
