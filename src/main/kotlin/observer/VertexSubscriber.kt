package org.example.observer

import org.example.model.Vertex

interface VertexSubscriber : Subscriber {
    /**
     * This method adds a display for the specified vertex
     * @param vertex [Vertex] object represents the vertex whose display is to be added
     */
    fun addVertex(vertex: Vertex)

    /**
     * This method removes display for the specified vertex
     * @param vertex [Vertex] object represents the vertex whose display is to be removed
     */
    fun removeVertex(vertex: Vertex)
}