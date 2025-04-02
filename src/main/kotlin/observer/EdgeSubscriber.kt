package org.example.observer

import org.example.model.Edge

interface EdgeSubscriber : Subscriber {
    /**
     * This method displays the edges between vertices in the graph
     * @param edges List of [Edge] representing the edges to be shown
     */
    fun showEdges(edges: List<Edge>)
}