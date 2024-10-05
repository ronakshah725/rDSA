package org.ronak.ds.Graph;

import lombok.Getter;

import java.util.Iterator;

/**
 * Graph interface defining basic operations for a graph.
 *
 * @param <T> the type of elements in the graph
 */
public interface Graph<T> {

    /**
     * Class representing an edge in the graph.
     *
     * @param <T> the type of elements in the graph
     */
    record Edge<T>(T source, T destination) {}

    boolean isDirected();


    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add
     */
    void addVertex(T vertex);

    /**
     * Adds an edge to the graph.
     *
     * @param source the source vertex of the edge
     * @param destination the destination vertex of the edge
     */
    void addEdge(T source, T destination);

    /**
     * Returns an iterator over the vertices in the graph.
     *
     * @return an iterator over the vertices in the graph
     */
    Iterator<T> vertexIterator();

    /**
     * Returns an iterator over the edges in the graph.
     *
     * @return an iterator over the edges in the graph
     */
    Iterator<Edge<T>> edgeIterator();
}

