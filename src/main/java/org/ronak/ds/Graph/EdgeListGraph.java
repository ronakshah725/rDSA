package org.ronak.ds.Graph;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EdgeListGraph<T> implements Graph<T> {
    private final List<T> vertices = new ArrayList<>();
    private final List<Edge<T>> edges = new ArrayList<>();
    private final boolean directed;

    public EdgeListGraph(boolean directed) {
        this.directed = directed;
    }

    @Override
    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    @Override
    public void addEdge(T source, T destination) {
        edges.add(new Edge<>(source, destination));
        if (!directed) {
            edges.add(new Edge<>(destination, source));
        }
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public Iterator<T> vertexIterator() {
        return vertices.iterator();
    }

    @Override
    public Iterator<Edge<T>> edgeIterator() {
        return edges.iterator();
    }
}