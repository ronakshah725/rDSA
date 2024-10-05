package org.ronak.ds.Graph;

/**
 * UnionFind data structure implementation.
 */
public class UnionFind {

    // Instance variables
    private int[] root;

    /**
     * Constructor to initialize UnionFind with 'n' elements.
     *
     * @param n number of elements
     */
    public UnionFind(int n) {
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
    }

    /**
     * Constructor to initialize UnionFind with 'm' elements in a 2D matrix.
     *
     * @param m number of rows
     * @param n number of columns (not used)
     */
    public UnionFind(int m, int n) {
        root = new int[m];
        for (int i = 0; i < m; i++) {
            root[i] = i;
        }
    }

    /**
     * Main method for testing.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        uf.print();

        uf.quickUnion(0, 1);
        uf.quickUnion(4, 3);
        uf.print();

        uf.quickUnion(3, 8);
        uf.print();
        uf.quickUnion(6, 5);
        uf.print();
        uf.quickUnion(9, 4);
        uf.print();

        System.out.println(uf.connected(1, 3));
        System.out.println(uf.connected(4, 9));

        uf.quickUnion(2, 5);

        uf.print();
    }

    /**
     * Prints the current state of the UnionFind.
     */
    private void print() {
        for (int i = 0; i < root.length; i++) {
            System.out.print(root[i] + " ");
        }
        System.out.println();
    }

    /**
     * Finds the root of an element.
     *
     * @param p element to find root for
     * @return root of the element
     */
    public int quickFind(int p) {
        if (p != root[p]) {
            root[p] = quickFind(root[p]);
        }
        return root[p];
    }

    /**
     * Unions two elements.
     *
     * @param p first element
     * @param q second element
     */
    public void union(int p, int q) {
        int rootX = quickFind(p);
        int rootY = quickFind(q);

        if (rootX != rootY) {
            for (int i = 0; i < root.length; i++) {
                if (root[i] == rootX) root[i] = rootY;
            }
        }
    }

    /**
     * Unions two elements using quick union.
     *
     * @param p first element
     * @param q second element
     */
    public void quickUnion(int p, int q) {
        int rootX = quickFind(p);
        int rootY = quickFind(q);

        if (rootX != rootY) {
            root[rootX] = rootY;
        }
    }

    /**
     * Checks if two elements are connected.
     *
     * @param x first element
     * @param y second element
     * @return true if elements are connected, false otherwise
     */
    public boolean connected(int x, int y) {
        return quickFind(x) == quickFind(y);
    }
}