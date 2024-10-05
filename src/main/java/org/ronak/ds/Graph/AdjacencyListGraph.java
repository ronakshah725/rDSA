package org.ronak.ds.Graph;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class AdjacencyListGraph<T> implements Graph<T> {
    private final Map<T, List<T>> adjacencyList = new HashMap<>();
    private final boolean directed;


    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(T source, T destination) {
        adjacencyList.get(source).add(destination);

        if(!directed){
            adjacencyList.get(destination).add(source);
        }
    }

    @Override
    public Iterator<T> vertexIterator() {
        return adjacencyList.keySet().iterator();
    }

    @Override
    public Iterator<Edge<T>> edgeIterator() {
        List<Edge<T>> edges = new ArrayList<>();
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            T source = entry.getKey();
            for (T destination : entry.getValue()) {
                edges.add(new Edge<>(source, destination));
            }
        }
        return edges.iterator();
    }
}
