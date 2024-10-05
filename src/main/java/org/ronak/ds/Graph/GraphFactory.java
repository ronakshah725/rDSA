//package org.ronak.ds.Graph;
//
///**
// * Factory class to create different types of graphs.
// */
//public class GraphFactory {
//    public enum GraphType {
//        EDGE_LIST,
//        ADJACENCY_LIST,
//        ADJACENCY_MATRIX
//    }
//
//    /**
//     * Creates a graph of the specified type.
//     *
//     * @param type the type of the graph
//     * @param <T>  the type of elements in the graph
//     * @return a graph of the specified type
//     */
//    public static <T> Graph<T> createGraph(GraphType type) {
//        return switch (type) {
//            case EDGE_LIST -> new EdgeListGraph<>();
//            case ADJACENCY_LIST -> new AdjacencyListGraph<>();
//            case ADJACENCY_MATRIX -> new AdjacencyMatrixGraph<>();
//            default -> throw new IllegalArgumentException("Unknown graph type: " + type);
//        };
//    }
//
//    /**
//     * Creates a Adjaceny List Graph
//     *
//     * @param <T>  the type of elements in the graph
//     * @return a graph of the specified type
//     */
//    public static <T> Graph<T> createGraph() {
//        return new AdjacencyListGraph<>();
//    }
//}
